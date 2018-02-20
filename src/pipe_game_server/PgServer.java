/**
 * PgServer - This class implements server for Pipe Game.
 * Aviv Segal 12/2017
 */

package pipe_game_server;

import game_server_interface.ClientHandler;
import game_server_interface.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class PgServer implements Server {


    @Override
    public void start(ClientHandler clientHandler) {
        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stop = false;
        System.out.println("Server is alive and running on port " + server.getLocalPort());

        try {
            server.setSoTimeout(1000);
        } catch (SocketException e) {
            e.printStackTrace();
        }

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
            }catch (SocketTimeoutException ignored) { }
        }
        server.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PgServer myServer = new PgServer(6400);
        myServer.start(new PgClientHandler(new PgSolver(),new PgCacheManager()));
    }

}