package server;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        Server server = new AvnerServer();
        server.start(4200);
    }
}
