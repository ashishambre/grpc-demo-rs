import io.grpc.Server;
import io.grpc.ServerBuilder;

public class UserServer {
  public static void main(String[] args) throws Exception {
    Server server = ServerBuilder
            .forPort(9090)
            .addService(new UserServiceImpl())
            .build();

    server.start();

    System.out.println("gRPC server started on port 9090");

    Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));

    server.awaitTermination();
  }
}
