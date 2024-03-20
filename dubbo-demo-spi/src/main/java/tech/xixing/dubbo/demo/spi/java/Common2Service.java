package tech.xixing.dubbo.demo.spi.java;

import org.apache.dubbo.common.URL;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class Common2Service implements Service {
    @Override
    public void sayHello(URL url) {
        System.out.println("hello Common2Service");
    }
}
