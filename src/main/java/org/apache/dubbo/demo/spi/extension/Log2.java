package org.apache.dubbo.demo.spi.extension;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author liuzhifei
 * @since 1.0
 */
@SPI
public interface Log2 {

    void log2(String log);
}
