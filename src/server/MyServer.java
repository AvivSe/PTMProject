package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer implements Server {

    @Override
    public void start(ClientHandler clientHandler) throws IOException {

        while (true){
            System.out.println("Waiting for next client...");
            Socket socket = this.server.accept();
            System.out.println("New Client: " + socket.getPort());

            clientHandler.handler(socket.getInputStream(), socket.getOutputStream());

        }

    }

    @Override
    public void stop() throws IOException {
        this.server.close();
    }

    ServerSocket server;

    MyServer(int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    public static void main(String[] args) throws IOException {
        MyServer myServer = new MyServer(4200);
        myServer.start(new MyClientHandler());
    }
}
