/**
 * PgServer - This class implements server for Pipe Game.
 * Aviv Segal 12/2017
 */

package pipeGame.server;

import server_interface.ClientHandler;
import server_interface.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    private void run(ClientHandler clientHandler) throws IOException {

        while (!stop){
            try {
                //System.out.print(".");
                Socket socket = this.server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(socket.getOutputStream());
                            String firstLine = in.readLine();

                            if(!firstLine.equals("test")) {
                                System.out.println("\nNew client on port " + socket.getPort() + " Weight: " + firstLine);
                                clientHandler.handler(in, out);
                            }

                            in.close();
                            out.close();
                            socket.close();

                        } catch (IOException e) {
                            e.printStackTrace();
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