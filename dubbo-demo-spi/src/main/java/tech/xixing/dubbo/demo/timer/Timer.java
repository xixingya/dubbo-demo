package tech.xixing.dubbo.demo.timer;

import java.util.concurrent.TimeUnit;

/**
 * @author liuzhifei
 * @since 1.0
 */
public interface Timer {
    
    void submitTimeTask(TimerTask timerTask, int timeSecond);
    
    void stop();
}
