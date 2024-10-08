package tech.xixing.dubbo.demo.stream;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import tech.xixing.dubbo.demo.DemoService;
import tech.xixing.dubbo.demo.StreamService;
import tech.xixing.dubbo.demo.application.register.provider.DemoServiceImpl;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class StreamProviderDemo {

    private static final String REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    public static void main(String[] args) {
        startWithBootstrap();
    }

    private static void startWithBootstrap() {
        ServiceConfig<StreamService> service = new ServiceConfig<>();
        service.setInterface(StreamService.class);
        service.setRef(new StreamServiceImpl());
        // 上报metadata到远程
        System.setProperty("dubbo.metadata-report.report-metadata", "true");
        System.setProperty("dubbo.application.serialize-check-status", "DISABLE");

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        RegistryConfig registryConfig = new RegistryConfig(REGISTRY_URL);
        registryConfig.setRegisterMode("instance");
        bootstrap
                .application(new ApplicationConfig("dubbo-demo-api-provider-stream"))
                .registry(registryConfig)
                .protocol(new ProtocolConfig(CommonConstants.TRIPLE, -1))
                .service(service)
                .start()
                .await();
    }
}
