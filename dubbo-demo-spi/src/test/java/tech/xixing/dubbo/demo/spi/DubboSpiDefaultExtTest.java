package tech.xixing.dubbo.demo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.junit.jupiter.api.Test;
import tech.xixing.dubbo.demo.spi.java.Log;

/**
 * @author liuzhifei
 * @since 1.0
 */

class DubboSpiDefaultExtTest {

    @Test
    void testDefaultExt(){
        for (int i = 0; i < 3; i++) {
            ExtensionLoader<Log> extensionLoader = ExtensionLoader.getExtensionLoader(Log.class);
            extensionLoader.getDefaultExtension().log("hello world");
        }
    }


}
