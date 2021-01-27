package com.codelens.grpc.grpcserver;

import com.codelens.grpc.grpcserver.services.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class GrpcServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Create a new server to listen on port 9800 ...
        Server server = ServerBuilder.forPort(9800)
                .addService(new GreetingServiceImpl())
                .build();

        // Start the server ...
        server.start();
        log.info("Server started ...");

        //Don't exit the main thread. Wait until server is terminated ...
        server.awaitTermination();


        SpringApplication.run(GrpcServerApplication.class, args);
    }

}
