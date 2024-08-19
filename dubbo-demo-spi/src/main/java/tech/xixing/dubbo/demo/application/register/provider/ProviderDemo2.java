package tech.xixing.dubbo.demo.application.register.provider;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import tech.xixing.dubbo.demo.DemoService;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class ProviderDemo2 {

    private static final String REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    public static void main(String[] args) {
        startWithBootstrap();
    }

    private static void startWithBootstrap() {
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());
        // 上报metadata到远程
        System.setProperty("dubbo.metadata-report.report-metadata", "true");

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        RegistryConfig registryConfig = new RegistryConfig(REGISTRY_URL);
        registryConfig.setRegisterMode("instance");
        // 注意看zk的路径，在xixingya路径下面了。
        registryConfig.setGroup("xixingya");
        bootstrap
                .application(new ApplicationConfig("dubbo-demo-api-provider2"))
                .registry(registryConfig)
                .protocol(new ProtocolConfig(CommonConstants.DUBBO, -1))
                .service(service)
                .start()
                .await();
    }
}
