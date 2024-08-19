package tech.xixing.dubbo.demo.stream;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import tech.xixing.dubbo.demo.DemoService;
import tech.xixing.dubbo.demo.StreamService;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class StreamConsumerDemo {

    private static final String REGISTRY_URL = "zookeeper://127.0.0.1:2181";

    public static void main(String[] args) {
        runWithBootstrap();
    }

    private static void runWithBootstrap() {
        ReferenceConfig<StreamService> reference = new ReferenceConfig<>();
        reference.setInterface(StreamService.class);
        reference.setGeneric("true");

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        RegistryConfig registryConfig = new RegistryConfig(REGISTRY_URL);
        // 强制应用级接口订阅
        System.setProperty("dubbo.application.service-discovery.migration", "FORCE_APPLICATION");
        System.setProperty("dubbo.metadata-report.report-metadata", "true");
        System.setProperty("dubbo.application.serialize-check-status", "DISABLE");
        bootstrap
                .application(new ApplicationConfig("dubbo-demo-api-consumer-stream"))
                .registry(registryConfig)
                .protocol(new ProtocolConfig(CommonConstants.TRIPLE, -1))
                .reference(reference)
                .start();

        StreamService service = bootstrap.getCache().get(reference);
        service.sayHelloServerStream("aaa", new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("get data" + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
