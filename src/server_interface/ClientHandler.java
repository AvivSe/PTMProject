package server_interface;

import java.io.*;
/**
 *
 * ClientHandler is a service that takes care for specific client,
 * it's helps stream the data between the server and client.
 */
public interface ClientHandler {
    /**
     *
     * Takes care specific client,
     * once got a problem it's solve it and put solution on client output stream.
     * @param in client Reader
     * @param out client Writer
     * @throws IOException
     */
    void handler(InputStream in, OutputStream out) throws IOException;

}