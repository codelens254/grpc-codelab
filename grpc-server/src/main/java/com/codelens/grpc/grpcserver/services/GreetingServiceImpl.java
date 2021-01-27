package com.codelens.grpc.grpcserver.services;

import com.codelens.grpc.GreetingService;
import com.codelens.grpc.GreetingsServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreetingServiceImpl extends GreetingsServiceGrpc.GreetingsServiceImplBase {

    @Override
    public void greeting(GreetingService.HelloRequest request, StreamObserver<GreetingService.HelloResponse> responseObserver) {
        log.info(String.format("Request: %s", request));

        // You must use a builder to construct a new Protobuffer object ...
        GreetingService.HelloResponse response = GreetingService.HelloResponse
                .newBuilder()
                .setGreeting(String.format("Hello there, %s", request.getName()))
                .build();

        // Use responseObserver to send a single response back ...
        responseObserver.onNext(response);

        // When done, you must call onCompleted ...
        responseObserver.onCompleted();
    }
}
