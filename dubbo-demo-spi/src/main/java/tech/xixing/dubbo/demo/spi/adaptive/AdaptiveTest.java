package tech.xixing.dubbo.demo.spi.adaptive;

import tech.xixing.dubbo.demo.spi.java.Log;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class AdaptiveTest {

    public static void main(String[] args) {
        ExtensionLoader<Log> extensionLoader = ExtensionLoader.getExtensionLoader(Log.class);
        Log adaptiveExtension = extensionLoader.getAdaptiveExtension();
        URL url = URL.valueOf("dubbo://127.0.0.1:20880?log=logback");
        adaptiveExtension.logAdaptive(url, "hello world");
        url = URL.valueOf("dubbo://127.0.0.1:20880?log=log4j");
        adaptiveExtension.logAdaptive(url, "hello world");
    }
}
