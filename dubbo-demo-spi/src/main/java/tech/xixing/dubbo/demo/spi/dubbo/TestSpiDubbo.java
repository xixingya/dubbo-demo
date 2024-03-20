package tech.xixing.dubbo.demo.spi.dubbo;

import tech.xixing.dubbo.demo.spi.java.Log;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class TestSpiDubbo {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            ExtensionLoader<Log> extensionLoader = ExtensionLoader.getExtensionLoader(Log.class);
//            extensionLoader.getExtension("logback").log("hello world");
//            //extensionLoader.getExtension("log4j").log("hello world");
//            extensionLoader.getExtension("log4j").log("hello world");
            extensionLoader.getDefaultExtension().log("hello world");
            //extensionLoader.getExtension("mylog").log("hello world");
        }
    }
}
