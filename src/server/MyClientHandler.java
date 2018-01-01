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
        System.out.print("The client ask solution for: " + request + "...");

        String solution = this.cacheManager.load(request);
        if(solution != null) {
            out.write("Cache: " + solution);
        } else {
            solution = solver.solve(request);
            cacheManager.save(request,solution);
            out.write("Solver: " + solution);
        }
        System.out.print("Client got answer. ");
        out.flush();
        out.close();
    }

    private Solver solver;
    private CacheManager cacheManager;

    MyClientHandler(Solver solver, CacheManager cacheManager) {
        this.solver = solver;
        this.cacheManager = cacheManager;
    }
}