package tech.xixing.dubbo.demo.spi.java;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;

/**
 * @author liuzhifei
 * @since 1.0
 */
@Activate(group = "common", order = 6)
public class Log4j implements Log {

    @Override
    public void log(String log) {
        System.out.println("log4j print " + log + " this is " + this);
    }

    @Override
    public void logAdaptive(URL url, String log) {
        System.out.println("logAdaptive log4j print " + log + " this is " + this);
    }
}
