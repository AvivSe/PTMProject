/**
 * PgServer - This class implements server for Pipe Game.
 * Aviv Segal 12/2017
 */

package pipeGame;

import server_interface.ClientHandler;
import server_interface.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

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
    public void stop()  {
        this.stop = true;
        System.out.println("Server is down.");
    }

    private ServerSocket server;
    public boolean stop;
    private int port;

    public PgServer(int port) {
        this.port = port;
        this.server = null;
        this.stop = true;
        System.out.println("Ready to start...");
    }

    class Work implements Comparable,Callable {

        @Override
        public int compareTo(Object o) {
            return 0;
        }

        @Override
        public Object call() throws Exception {
            return null;
        }
    }
    private void run(ClientHandler clientHandler) throws IOException {

        while (!stop){
            try {
                System.out.print(".");
                Socket socket = this.server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            int weight = Integer.valueOf(in.readLine());
                            System.out.println("\nNew client on port " + socket.getPort() + " Weight: " + weight);


                            clientHandler.handler(socket.getInputStream(), socket.getOutputStream());
                            socket.getInputStream().close();
                            socket.getOutputStream().close();
                            socket.close();


                        } catch (IOException error) {
                            System.out.println(error.getMessage()+" Waiting for another Client...");
                        }
                    }
                }).start();
            }catch (SocketTimeoutException ignored) { }
        }

        server.close();
    }

    public static void main(String[] args)  {
        PgServer myServer = new PgServer(6400);

        /* w/o controller un/re comment this */
        myServer.start(new PgClientHandler(new PgSolver(),new PgCacheManager()));
        //new PgServerController(myServer, new PgClientHandler(new PgSolver(),new PgCacheManager())).gui();
    }

}