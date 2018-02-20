package pipe_game_server;

import java.io.*;

import game_server_interface.CacheManager;
import game_server_interface.ClientHandler;
import game_server_interface.Directions;
import game_server_interface.Solver;

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
        PgLevel request = LevelBuilder.build(tmp);
        System.out.println("Client ask for directions to level: \n"  + tmp);

        try {
            // TODO: Turen on cash manger by uncommet thus lines.
                out.print(this.cacheManager.load(tmp).toString());
                System.out.println("Cache said: I have it in files :)");
                out.flush();
        } catch (NullPointerException error) {
            try {
                System.out.println("Solver said: maybe I can solve it :)");
                Directions pgDirections = solver.solve(request);
                cacheManager.save(tmp , pgDirections.toString());
                System.out.println("\nSolution is:\n"+ pgDirections.toString());
                out.print(pgDirections.toString());
                out.flush();
            } catch (NullPointerException error2) {
                System.out.println("Solver said: I cant solve it :(");
                System.out.print("Client did not got an answer. ");
                out.print("done");
                out.flush();
            }
        }
//        Directions pgDirections = this.cacheManager.load(tmp);
//        if (pgDirections != null) {
//            System.out.println("Cache said: I have it in files :)");
//            out.write(pgDirections.toString());
//        } else {
//            System.out.println("Solver said: maybe I can solve it :)");
//            try {
//                pgDirections = solver.solve(request);
//                cacheManager.save(tmp , pgDirections.toString());
//                System.out.println("\nSolution is:\n"+ pgDirections.toString());
//                out.write(pgDirections.toString());
//            }  catch (NullPointerException error) {
//                System.out.println("Solver said: I cant solve it :(");
//                System.out.print("Client did not got an answer. ");
//                out.write("done");
//            }
//        }
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