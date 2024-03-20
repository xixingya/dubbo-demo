package tech.xixing.dubbo.demo.spi.activate;

import tech.xixing.dubbo.demo.spi.java.Log;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.List;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class ActivateTest {

    public static void main(String[] args) {
        URL url = URL.valueOf("dubbo://127.0.0.1:20880");
        ExtensionLoader<Log> extensionLoader = ExtensionLoader.getExtensionLoader(Log.class);
        List<Log> activateExtensions = extensionLoader.getActivateExtension(url, new String[]{}, "my");
        for (Log activateExtension : activateExtensions) {
            activateExtension.log("hello world");
        }
    }
}
