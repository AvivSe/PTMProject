package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public interface ClientHandler {
    /**
     *
     * Takes care specific client,
     * once got a problem it's solve it and put solution on client output stream.
     * @param input client input stream
     * @param output client output stream
     * @throws IOException
     */
    void handler(InputStream input, OutputStream output) throws IOException;

}