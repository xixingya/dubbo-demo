package tech.xixing.dubbo.demo.spi.java;

import java.util.ServiceLoader;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class TestSpi {
    public static void main(String[] args) {
        // ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
        for (int i = 0; i < 3; i++) {
            ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
            for (Log next : serviceLoader) {
                next.log("hello world");
            }
        }

    }
}
