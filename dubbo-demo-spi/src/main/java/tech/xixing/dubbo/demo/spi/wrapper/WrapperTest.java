package tech.xixing.dubbo.demo.spi.wrapper;

import tech.xixing.dubbo.demo.spi.java.Log;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class WrapperTest {

    public static void main(String[] args) {
        ExtensionLoader<Log> extensionLoader = ExtensionLoader.getExtensionLoader(Log.class);
        Log logback = extensionLoader.getExtension("logback");
        logback.log("hello world");
        Log log4j = extensionLoader.getExtension("log4j");
        log4j.log("hello world");
    }
}
