package tech.xixing.dubbo.demo.timer;


import com.alibaba.fastjson2.util.DateUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class TimeWheelTimer implements Timer {


    private Map<Integer, List<TimerTask>> timerWheelMap = new HashMap<>();

    private Thread workerThread;

    private Worker worker = new Worker();

    private static final AtomicIntegerFieldUpdater<TimeWheelTimer> WORKER_STATE_UPDATER =
            AtomicIntegerFieldUpdater.newUpdater(TimeWheelTimer.class, "workerState");


    private static final int WORKER_STATE_INIT = 0;
    private static final int WORKER_STATE_STARTED = 1;
    private static final int WORKER_STATE_SHUTDOWN = 2;

    /**
     * 0 - init, 1 - started, 2 - shut down
     */
    private volatile int workerState;

    public TimeWheelTimer() {
        workerThread = Executors.defaultThreadFactory().newThread(worker);
    }

    private void start() {
        switch (WORKER_STATE_UPDATER.get(this)) {
            case WORKER_STATE_INIT:
                if (WORKER_STATE_UPDATER.compareAndSet(this, WORKER_STATE_INIT, WORKER_STATE_STARTED)) {
                    workerThread.start();
                }
                break;
            case WORKER_STATE_STARTED:
                break;
            case WORKER_STATE_SHUTDOWN:
                throw new IllegalStateException("cannot be started once stopped");
            default:
                throw new Error("Invalid WorkerState");
        }

    }

    /**
     * 秒级别的，只能按秒来
     *
     * @param timerTask  任务
     * @param timeSecond 0-59 即，为了简单只调度最近一分钟的任务。即第多少秒执行。
     */
    @Override
    public void submitTimeTask(TimerTask timerTask, int timeSecond) {
        int second = timeSecond % 60;
        List<TimerTask> timerTasks = timerWheelMap.computeIfAbsent(second, key -> new LinkedList<>());
        timerTasks.add(timerTask);
        start();
    }

    @Override
    public void stop() {
        WORKER_STATE_UPDATER.set(this, WORKER_STATE_SHUTDOWN);
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            do {
                long time = System.currentTimeMillis() / 1000;
                int currentIndex = (int) (time % 60);
                List<TimerTask> timerTasks = timerWheelMap.get(currentIndex);
                if (timerTasks != null && !timerTasks.isEmpty()) {
                    for (TimerTask timerTask : timerTasks) {
                        try {
                            timerTask.run();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    timerWheelMap.put(currentIndex, null);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } while (WORKER_STATE_UPDATER.get(TimeWheelTimer.this) == WORKER_STATE_STARTED);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeWheelTimer timeWheelTimer = new TimeWheelTimer();
        timeWheelTimer.submitTimeTask(() -> {
            System.out.println("第10秒执行");
            System.out.println("当前时间"+ DateUtils.format(System.currentTimeMillis()));
        },10);

        timeWheelTimer.submitTimeTask(() -> {
            System.out.println("第5秒执行");
            System.out.println("当前时间"+ DateUtils.format(System.currentTimeMillis()));
        },5);
        System.out.println("开始计时");
        
        Thread.sleep(200000);
    }


}
