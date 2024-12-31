package io.github.venkat1701.grpcpoc.services.impl;

import io.github.venkat1701.GreetingServiceGrpc;
import io.github.venkat1701.GreetingServiceProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class GreetingClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 8080).usePlaintext().build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceProto.GreetingRequest request = GreetingServiceProto.GreetingRequest
                .newBuilder()
                .setName("World")
                .build();

        try {
            GreetingServiceProto.GreetingResponse response = stub.greet(request);
            System.out.println("Response from server: " + response.getName());
        } catch (Exception e) {
            System.err.println("Error while calling gRPC service: " + e.getMessage());
        } finally {
            channel.shutdown();
        }
    }
}
