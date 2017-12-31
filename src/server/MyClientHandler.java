package server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * The purpose of this kind of ClientHandler is to solve level in our Pipe-Game Project,
 * We adapt some algorithms for this goal, using our levels solver,
 * old requests (and their solutions) are stored in the cache manager.
 */
public class MyClientHandler implements ClientHandler{
    @Override
    public void handler(InputStream input, OutputStream output) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        PrintWriter out = new PrintWriter(output);

        String request = in.readLine();

        System.out.println("the client want solution for: " + request);
        if(oldRequests.containsKey(request)) {
            out.write("from Cache: " + oldRequests.get(request));

        } else {
            out.write("from Solver: " + request + " Solution");
            oldRequests.put(request, request + " Solution");
        }

        out.flush();
        out.close();
    }

    Map<String, String> oldRequests = new HashMap<>(); // Request | Solution
}