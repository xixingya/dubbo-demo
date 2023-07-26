package org.apache.dubbo.demo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.demo.spi.extension.LogAdaptive;
import org.apache.dubbo.demo.spi.extension.MyLogAdaptive;
import org.apache.dubbo.rpc.model.FrameworkModel;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {
        ExtensionLoader<LogAdaptive> extensionLoader = ExtensionLoader.getExtensionLoader(LogAdaptive.class);
        MyLogAdaptive logAdaptive = (MyLogAdaptive) extensionLoader.getExtension("my");
        // because org.apache.dubbo.common.extension.inject.SpiExtensionInjector.getInstance
        // the supportExtensions is empty so return null
        // if your add another ext, for example: org.apache.dubbo.demo.spi.extension.Log4jLog
        // you will get the right answer
        // the supportExtensions is not empty so getLog is not null
        
        assert logAdaptive.getLog() == null;


        // if you have two ext, you will get the right answer:
        assert logAdaptive.getLog2() != null;

    }
}