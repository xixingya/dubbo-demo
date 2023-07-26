package org.apache.dubbo.demo.spi.extension;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class MyLogAdaptive implements LogAdaptive {

    private Log log;

    private Log2 log2;

    public void setSlf4jLog(Log log) {
        this.log = log;
    }

    public void setSlf4jLog2(Log2 log2) {
        this.log2 = log2;
    }

    public Log getLog() {
        return log;
    }

    public Log2 getLog2() {
        return log2;
    }

    @Override
    public void logAdaptive(String logs) {
        System.out.println("MyLogAdaptive: " + logs);
    }
}
