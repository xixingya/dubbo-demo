package tech.xixing.dubbo.demo.application.register.provider;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.rpc.RpcContext;
import tech.xixing.dubbo.demo.DemoService;

import java.util.concurrent.CompletableFuture;

/**
 * @author liuzhifei
 * @since 1.0
 */
public class DemoServiceImpl implements DemoService {
    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public String sayHello(String name) {
        logger.info("Hello " + name + ", request from consumer: "
                + RpcContext.getServiceContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: "
                + RpcContext.getServiceContext().getLocalAddress();
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        return null;
    }
}
