package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {

    void handle(InputStream input, OutputStream output) throws IOException;

}