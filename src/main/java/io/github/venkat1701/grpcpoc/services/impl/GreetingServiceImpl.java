package io.github.venkat1701.grpcpoc.services.impl;

import io.github.venkat1701.GreetingServiceGrpc;
import io.github.venkat1701.GreetingServiceProto;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greet(GreetingServiceProto.GreetingRequest request,
                      StreamObserver<GreetingServiceProto.GreetingResponse> responseObserver) {
        try {
            String message = "Hello " + request.getName() + "!";
            GreetingServiceProto.GreetingResponse response =
                    GreetingServiceProto.GreetingResponse.newBuilder()
                            .setName(message)
                            .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
