package org.apache.dubbo.demo.spi.extension;

import org.apache.dubbo.common.extension.Adaptive;

/**
 * @author liuzhifei
 * @since 1.0
 */
@Adaptive
public class Slf4jLog2 implements Log2 {
    @Override
    public void log2(String log) {
        System.out.println("Slf4jLog2: " + log);
    }
}
