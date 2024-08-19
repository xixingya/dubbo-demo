package tech.xixing.dubbo.demo;

import org.apache.dubbo.common.stream.StreamObserver;

/**
 * @author liuzhifei
 * @since 1.0
 */
public interface StreamService {

    StreamObserver<String> sayHelloStream(StreamObserver<String> response);

    void sayHelloServerStream(String request, StreamObserver<String> response);
    
}
