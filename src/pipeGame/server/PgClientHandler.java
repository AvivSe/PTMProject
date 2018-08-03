package pipeGame.server;

import java.io.*;

import server_interface.CacheManager;
import server_interface.ClientHandler;
import server_interface.Instructions;
import server_interface.Solver;
/**
 *
 * The purpose of this kind of ClientHandler is to solve level in our Pipe-Game Project,
 * We adapt some algorithms for this goal, using our levels solver,
 * old requests (and their solutions) are stored in the cache manager.
 *
 * Aviv Segal
 */
public class PgClientHandler implements ClientHandler {
    @Override
    public void handler(InputStream input, OutputStream output) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        PrintWriter out = new PrintWriter(output);

        StringBuilder req = new StringBuilder(in.readLine() + "\n");
        while (!req.toString().contains("done")) {
            req.append(in.readLine()).append("\n");
        }

        String tmp = req.toString();
        tmp = req.toString().substring(0,tmp.length()-5);
        PgLevel request = PgLevelBuilder.build(tmp);
        System.out.println("Client ask for directions to level: \n"  + tmp);

        try {
                out.print(this.cacheManager.load(tmp).toString());
//              System.out.println("Cache said: I have it in files :)");
        } catch (NullPointerException error) {
            try {
//              System.out.println("Solver said: maybe I can solve it :)");
                Instructions pgInstructions = solver.solve(request);
                cacheManager.save(tmp , pgInstructions.toString());
//              System.out.println("\nSolution is:\n"+ pgInstructions.toString());
                out.print(pgInstructions.toString());
            } catch (NullPointerException error2) { // if there is no solution till now:
//                System.out.println("Solver said: I cant solve it :(");
//                System.out.print("Client did not got an answer. ");
                out.print("done");
            }
        }
        System.out.print("Client got answer. ");

        out.flush();
        out.close();
    }

    private Solver solver;
    private CacheManager cacheManager;

    public PgClientHandler(PgSolver solver, PgCacheManager cacheManager) {
        this.solver = solver;
        this.cacheManager = cacheManager;
    }
}