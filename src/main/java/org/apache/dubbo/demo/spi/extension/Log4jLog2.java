package org.apache.dubbo.demo.spi.extension;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class Log4jLog2 implements Log2 {
    @Override
    public void log2(String log) {
        System.out.println("Log4jLog2: " + log);
    }
}
