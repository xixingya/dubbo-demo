package tech.xixing.dubbo.demo;

import java.util.concurrent.CompletableFuture;

/**
 * @author liuzhifei
 * @since 1.0
 */
public interface DemoService {

    String sayHello(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}
