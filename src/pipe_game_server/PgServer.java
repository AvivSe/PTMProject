
package pipe_game_server;
//TODO: In whole project, use LOGGER!
//TODO: Using JUNIT to biild testing for the project

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import adminstrator.MyAdministrator;
import server.ClientHandler;
import server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PgServer implements Server {
   // private final Logger LOGGER = LoggerFactory.getLogger(MyServer.class);

    @Override
    public void start(ClientHandler clientHandler) throws IOException {
        this.server = new ServerSocket(port);
        stop = false;
        System.out.println("Server is alive and running on port " + server.getLocalPort());
       // LOGGER.info("Server is alive and running on port {}", server.getLocalPort());
        server.setSoTimeout(1000);

        new Thread(()-> {
            try {
                run(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void stop() throws IOException {
        this.stop = true;
        System.out.println("Server is down.");
    }

    private ServerSocket server;
    public boolean stop;
    private int port;

    private PgServer(int port) throws IOException {
        this.port = port;
        this.server = null;
        this.stop = true;
        System.out.println("Ready to start...");
    }

    private void run(ClientHandler clientHandler) throws IOException {
        while (!stop){
            try {
                System.out.print(".");
                Socket socket = this.server.accept();
                try {

                    System.out.print("\nNew client on port " + socket.getPort() + ", waiting for query..\n");
                    clientHandler.handler(socket.getInputStream(), socket.getOutputStream());
                    socket.getInputStream().close();
                    socket.getOutputStream().close();
                    socket.close();
                } catch (IOException error) {
                    System.out.println(error.getMessage()+" Waiting for another Client...");
                }
            }catch (SocketTimeoutException error) {
                //System.out.println(error.getMessage());
            }
        }
        server.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PgServer myServer = new PgServer(6400);
        //myServer.start(new MyClientHandler(new MySolver(),new MyCacheManager()));
        new MyAdministrator(myServer, new PgClientHandler(new PgSolver(),new PgCacheManager())).gui();
    }

}