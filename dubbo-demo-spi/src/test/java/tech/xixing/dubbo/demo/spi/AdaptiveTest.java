package tech.xixing.dubbo.demo.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.jupiter.api.Test;
import tech.xixing.dubbo.demo.spi.java.CommonService;
import tech.xixing.dubbo.demo.spi.java.Log;
import tech.xixing.dubbo.demo.spi.java.Service;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class AdaptiveTest {
    
    @Test
    public void test() {
        ExtensionLoader<Log> extensionLoader = ExtensionLoader.getExtensionLoader(Log.class);
        Log adaptiveExtension = extensionLoader.getAdaptiveExtension();
        URL url = URL.valueOf("dubbo://127.0.0.1:20880?log=logback");
        adaptiveExtension.logAdaptive(url, "hello world");
        url = URL.valueOf("dubbo://127.0.0.1:20880?log=log4j");
        adaptiveExtension.logAdaptive(url, "hello world");
    }

    @Test
    public void test2() {
        ExtensionLoader<Service> extensionLoader = ExtensionLoader.getExtensionLoader(Service.class);
        // 获取到的是commonService
        Service adaptiveExtension = extensionLoader.getAdaptiveExtension();
        URL url = URL.valueOf("dubbo://127.0.0.1:20880?service=common3");
        adaptiveExtension.sayHello(url);
    }
}
