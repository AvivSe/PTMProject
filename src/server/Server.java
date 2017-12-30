package server;

import java.io.IOException;

public interface Server {
    /**
     * start working with specific client.
     * @param clientHandler
     * @throws IOException
     */
    public void start(ClientHandler clientHandler) throws IOException;

}