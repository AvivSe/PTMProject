package server;

import java.io.IOException;

public interface Server {
    /**
     *
     * Wait for interaction with client, here we accepting clients.
     * this process take care one client at time.
     * @param clientHandler Various implements solving different kinds of problems or requests.
     * @throws IOException
     */
    public void start(ClientHandler clientHandler) throws IOException;
    public void stop() throws IOException;
}