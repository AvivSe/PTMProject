package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer implements Server {
    Socket client;
    @Override
    public void start(ClientHandler clientHandler) throws IOException {
        client = new ServerSocket(4200).accept();
        System.out.println("New Client: " + client.getRemoteSocketAddress());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String req = bufferedReader.readLine();
        System.out.println("The asked for solution");

        PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
        printWriter.write(clientHandler.handle(req, request -> "Solution("+request+")"));
        printWriter.close();
    }
}
