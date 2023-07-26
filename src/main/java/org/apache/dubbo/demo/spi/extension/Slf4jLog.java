package org.apache.dubbo.demo.spi.extension;

import org.apache.dubbo.common.extension.Adaptive;

/**
 * @author liuzhifei
 * @since 1.0
 */
@Adaptive
public class Slf4jLog implements Log {
    @Override
    public void log(String log) {
        System.out.println("Slf4jLog: " + log);
    }
}
