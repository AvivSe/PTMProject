package server;

import java.net.Socket;

public class MyClientHandler implements ClientHandler {
    public Socket getSocket() {
        return socket;
    }

    Socket socket;
    public MyClientHandler(Socket socket) {
        this.socket = socket;
        System.out.println("ClientHandler said : new connection from : " + socket.getInetAddress());
    }

    @Override
    public String RequestSolution(String level) {
        return "I'm your solution. for level: " + level;
    }
}
