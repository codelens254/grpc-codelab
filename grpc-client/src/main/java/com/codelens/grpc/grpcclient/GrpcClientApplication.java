package com.codelens.grpc.grpcclient;

import com.codelens.grpc.GreetingService;
import com.codelens.grpc.GreetingsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class GrpcClientApplication {

    public static void main(String[] args) {

        final ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:9800")
                .usePlaintext()
                .build();

        // sync ... blocking request ...
        GreetingsServiceGrpc.GreetingsServiceBlockingStub stub = GreetingsServiceGrpc.newBlockingStub(channel);

        GreetingService.HelloRequest request = GreetingService.HelloRequest
                .newBuilder()
                .setName("Codelens")
                .build();

        GreetingService.HelloResponse response = stub.greeting(request);
        String greetingResponse = response.getGreeting();
        log.info(greetingResponse);

        channel.shutdownNow();


        SpringApplication.run(GrpcClientApplication.class, args);
    }

}
