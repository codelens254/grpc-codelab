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

        GreetingService.HelloResponse response = GreetingService.HelloResponse
                .newBuilder()
                .setGreeting("Hello "+ request.getName())
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
