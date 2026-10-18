import com.example.grpc.UserRequest;
import com.example.grpc.UserResponse;
import com.example.grpc.UserServiceGrpc;
import io.grpc.Status;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

  @Override
  public void getUser(UserRequest request, io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
    int userId = request.getId();
    System.out.println("Received request for user: " + userId);

    if(userId != 101) {
      responseObserver.onError(
              Status.NOT_FOUND
                      .withDescription("User not found: " + userId)
                      .asRuntimeException()
      );
      return;
    }

    UserResponse response = UserResponse.newBuilder()
            .setId(101)
            .setName("Alice")
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
