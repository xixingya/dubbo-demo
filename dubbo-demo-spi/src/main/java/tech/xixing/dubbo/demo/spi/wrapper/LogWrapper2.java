package tech.xixing.dubbo.demo.spi.wrapper;

import tech.xixing.dubbo.demo.spi.java.Log;
import org.apache.dubbo.common.URL;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class LogWrapper2 implements Log{
    private Log logger;

    public LogWrapper2(Log log) {
        this.logger = log;
    }

    @Override
    public void log(String log) {
        System.out.println("fdsfsdfdsfs");
        logger.log(log+"wrapper");
        System.out.println("aaa");
    }

    @Override
    public void logAdaptive(URL url, String log) {
        logger.log(log+"wrapper");
    }
}
