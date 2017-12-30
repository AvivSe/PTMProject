package server;

import java.io.*;

public class MyClientHandler implements ClientHandler{

    @Override
    public void handle(InputStream input, OutputStream output) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        PrintWriter out = new PrintWriter(output);

        String request = in.readLine();

        System.out.println("the client want solution for: " + request);

        out.write("this is sultion for: " + request);
        out.flush();
        out.close();
    }

}