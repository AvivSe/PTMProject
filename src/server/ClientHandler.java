package server;

import java.net.Socket;

public interface ClientHandler {
    interface Response {
        String manipulate(String request);
    }

    String handle(String request,  Response response);
}