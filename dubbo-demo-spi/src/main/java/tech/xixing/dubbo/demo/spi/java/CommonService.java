package tech.xixing.dubbo.demo.spi.java;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author liuzhifei
 * @since 1.0
 */
@Adaptive
public class CommonService implements Service {
    
    @Override
    public void sayHello(URL url) {
        ExtensionLoader<Service> extensionLoader = ExtensionLoader.getExtensionLoader(Service.class);
        if (url.getParameter("service").equals("common2")) {
            Service common2 = extensionLoader.getExtension("common2");
            common2.sayHello(url);
        } else {
            Service common3 = extensionLoader.getExtension("common3");
            common3.sayHello(url);
        }
        System.out.println("hello CommonService");
    }
}
