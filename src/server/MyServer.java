package server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class MyServer implements Server {

    @Override
    public void start(ClientHandler clientHandler) throws IOException {
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
    private boolean stop;

    private MyServer(int port) throws IOException {
        this.server = new ServerSocket(port);
        this.stop = false;
    }

    private void run(ClientHandler clientHandler) throws IOException {
        while (!stop){
            try {
                Socket socket = this.server.accept();
                try {
                    System.out.print("New client on port " + socket.getPort() + ", wating for query..");
                    clientHandler.handler(socket.getInputStream(), socket.getOutputStream());
                    socket.getInputStream().close();
                    socket.getOutputStream().close();
                    socket.close();
                } catch (IOException error) {
                    System.out.println(error.getMessage());
                    System.out.print("Waiting for next client");
                }
            }catch (SocketTimeoutException error) {
                System.out.print(".");
                //System.out.println(error.getMessage());
            }
        }
        server.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyServer myServer = new MyServer(4200);
        myServer.start(new MyClientHandler(new MySolver(),new MyCacheManager()));
    }
}

