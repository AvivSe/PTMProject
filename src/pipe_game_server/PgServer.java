/**
 * PgServer - This class implements server for Pipe Game.
 * Aviv Segal 12/2017
 */

package pipe_game_server;

import adminstrator.MyAdministrator;
import game_server_interface.ClientHandler;
import game_server_interface.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PgServer implements Server {


    @Override
    public void start(ClientHandler clientHandler) throws IOException {
        this.server = new ServerSocket(port);
        stop = false;
        System.out.println("Server is alive and running on port " + server.getLocalPort());

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

    public PgServer(int port) throws IOException {
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
        myServer.start(new PgClientHandler(new PgSolver(),new PgCacheManager()));
        //new MyAdministrator(myServer, new PgClientHandler(new PgSolver(),new PgCacheManager())).gui();
    }

}