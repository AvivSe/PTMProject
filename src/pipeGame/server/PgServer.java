/**
 * PgServer - This class implements server for Pipe Game.
 * Aviv Segal 12/2017
 */

package pipeGame.server;

import server_interface.ClientHandler;
import server_interface.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class PgServer implements Server {
    private ServerSocket server;
    boolean stop;
    private int port;
    private PriorityExecutorService<PgTask> priorityExecutorService;

    public PgServer(int port) {
        this.port = port;
        this.server = null;
        this.stop = true;
        System.out.println("Ready to start...");

        priorityExecutorService =
                new PriorityExecutorService<>(
                        Executors.newCachedThreadPool(),
                            new PriorityBlockingQueue<>());
    }

    @Override
    public void start(ClientHandler clientHandler) {
        priorityExecutorService.activeExecutor();
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
        this.priorityExecutorService.shutdownExecutor();
        System.out.println("Server is down.");
    }


    private void run(ClientHandler clientHandler) throws IOException {
        while (!stop){
            try {
                //System.out.print(".");
                Socket socket = this.server.accept();
                new Thread(() -> {
                    try {
                        BufferedInputStream socketInputStream = new BufferedInputStream(socket.getInputStream());
                        socketInputStream.mark(0);
                        System.out.println(socketInputStream.markSupported());
                        BufferedReader in = new BufferedReader(new InputStreamReader(socketInputStream));
                        // Getting Priority
                        int priority = 0;

                        String line = in.readLine();
                        while(!line.equals("done")) {
                            for(int i = 0; i < line.length(); i++) {
                                char c = line.charAt(i);
                                if(c == 'L' || c == 'F' || c == 'J' || c == '7')
                                    priority += 2;
                                if(c == '|' || c == '-')
                                    priority++;
                            }
                            line = in.readLine();
                        }
                        socketInputStream.reset();

                        System.out.println("\nNew client on port " + socket.getPort() + " Weight: " + priority);
                        priorityExecutorService.add(new PgTask(() -> {
                            try {
                                clientHandler.handler(socketInputStream, socket.getOutputStream());
                                in.close();
                                socket.close();
                                } catch(IOException ignored) {}
                            }, priority));
                    } catch (IOException e) {
                        e.printStackTrace();
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