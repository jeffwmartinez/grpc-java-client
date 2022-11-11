package com.example.grpc;


import io.grpc.*;

public class Client
{
    public static void main( String[] args ) throws Exception
    {
      // Channel is the abstraction to connect to a service endpoint
      // Let's use plaintext communication because we don't have certs

      // Local Dev
      // final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8585")
      //   .usePlaintext(true)
      //   .build();

      final ManagedChannel channel = ManagedChannelBuilder.forTarget("jefmarti-grpc-server-java.azurewebsites.net").build();

      
      // It is up to the client to determine whether to block the call
      // Here we create a blocking stub, but an async stub,
      // or an async stub with Future are always possible.
      GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
      GreetingServiceOuterClass.HelloRequest request =
        GreetingServiceOuterClass.HelloRequest.newBuilder()
          .setName("Jeff")
          .build();

      // Finally, make the call using the stub
      GreetingServiceOuterClass.HelloResponse response = 
        stub.greeting(request);

      System.out.println(response);

      // A Channel should be shutdown before stopping the process.
      channel.shutdownNow();
    }
}
