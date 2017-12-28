package server;

import java.io.IOException;

public interface Server {
    public void start(int port) throws IOException;
    public void stop() throws IOException;
}