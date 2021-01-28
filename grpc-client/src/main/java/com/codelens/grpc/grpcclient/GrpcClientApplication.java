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

        // we use a channel to connect to an endpoint ...
        final ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:9800")
                .usePlaintext()
                .build();

        // its for the client to determine whether to block the call or otherwise... (Clients decide whether to use Sync or Async call ...)
        GreetingsServiceGrpc.GreetingsServiceBlockingStub stub = GreetingsServiceGrpc.newBlockingStub(channel);

        GreetingService.HelloRequest request = GreetingService.HelloRequest
                .newBuilder()
                .setName("Eric")
                .build();

        // make the call using the stub ...
        GreetingService.HelloResponse response = stub.greeting(request);
        String greetingResponse = response.getGreeting();
        log.info(greetingResponse);

        // shutdown the channel ... (must be done before stopping the process) ...
        channel.shutdownNow();

        SpringApplication.run(GrpcClientApplication.class, args);
    }

}
