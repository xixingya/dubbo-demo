package tech.xixing.dubbo.demo.spi.java;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;

/**
 * @author liuzhifei
 * @since 1.0
 */
@Adaptive
public class CommonService implements Service{

    @Override
    public void sayHello(URL url) {
        System.out.println("hello CommonService");
    }
}
