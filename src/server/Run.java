package server;

import java.io.IOException;
import java.net.Socket;

public class Run {
    public static void main(String[] args) throws IOException {
        new MyServer().start(new MyClientHandler());
    }
}