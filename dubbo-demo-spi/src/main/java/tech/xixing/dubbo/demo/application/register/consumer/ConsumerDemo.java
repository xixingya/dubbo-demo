package tech.xixing.dubbo.demo.application.register.consumer;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import tech.xixing.dubbo.demo.DemoService;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class ConsumerDemo {


    private static final String REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    public static void main(String[] args) {
        runWithBootstrap();
    }

    private static void runWithBootstrap() {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        reference.setInterface(DemoService.class);
        reference.setGeneric("true");

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        RegistryConfig registryConfig = new RegistryConfig(REGISTRY_URL);
        // 强制应用级接口订阅
        System.setProperty("dubbo.application.service-discovery.migration", "FORCE_APPLICATION");
        bootstrap
                .application(new ApplicationConfig("dubbo-demo-api-consumer"))
                .registry(registryConfig)
                .protocol(new ProtocolConfig(CommonConstants.DUBBO, -1))
                .reference(reference)
                .start();

        DemoService demoService = bootstrap.getCache().get(reference);
        String message = demoService.sayHello("dubbo");
        System.out.println(message);

        // generic invoke
        GenericService genericService = (GenericService) demoService;
        Object genericInvokeResult = genericService.$invoke(
                "sayHello", new String[] {String.class.getName()}, new Object[] {"dubbo generic invoke"});
        System.out.println(genericInvokeResult.toString());
    }
}
