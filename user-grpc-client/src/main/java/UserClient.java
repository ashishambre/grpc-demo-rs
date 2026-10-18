import com.example.grpc.UserRequest;
import com.example.grpc.UserResponse;
import com.example.grpc.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class UserClient {
  public static void main(String[] args){
    ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 9090)
            .usePlaintext()
            .build();

    try {
      UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
      UserRequest request = UserRequest.newBuilder()
              .setId(101)
              .build();

      UserResponse response = stub
              .withDeadlineAfter(3, TimeUnit.SECONDS)
              .getUser(request);

      System.out.println("User ID: " + response.getId());
      System.out.println("User Name: " + response.getName());
    } catch (StatusRuntimeException e) {
      System.err.println("gRPC failed: " + e.getStatus());
    } finally {
      channel.shutdown();
    }
  }
}
