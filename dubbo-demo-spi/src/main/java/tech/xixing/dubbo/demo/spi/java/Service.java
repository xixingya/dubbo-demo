package tech.xixing.dubbo.demo.spi.java;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author liuzhifei
 * @since 1.0
 */
@SPI
public interface Service {

    void sayHello(URL url);
}
