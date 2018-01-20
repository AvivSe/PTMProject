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
        PgLevel request = PgLevel.LevelBuilder.build(tmp);
        System.out.println("Client ask for directions to level: \n"  + tmp);
//        System.out.println("Problem is: ");
//        System.out.println(tmp);
        //PgLevel normalizedRequest = normalize(request);
        PgLevel normalizedRequest = request;
//        System.out.println("it is equales this?");
//        System.out.println(request);
//        System.out.println("and this?");
//        System.out.println(normalizedRequest);

        Directions pgDirections = this.cacheManager.load(tmp);
        if (pgDirections != null) {
            System.out.println("Cache said: I have it in files :)");
        }


        if(pgDirections == null) {
            System.out.println("Solver said: maybe I can solve it :)");
            pgDirections = solver.solve(normalizedRequest);
            if (pgDirections == null) {
                System.out.println("Solver said: I cant solve it :(");
            } else {
                cacheManager.save(tmp , pgDirections.toString());
            }
        }

        System.out.println("\nSolution is:\n"+ pgDirections.toString());

        out.write(pgDirections.toString());

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
    private PgLevel normalize(PgLevel original) {

        StringBuilder result = new StringBuilder();
        for(char item: original.toString().toCharArray()) {
            switch(item) {
                case 'F':
                case '7':
                case 'J':
                    result.append('L');
                    break;
                case '-':
                    result.append('|');
                    break;
                default:
                    result.append(item);
                    break;
            }
        }

        return PgLevel.LevelBuilder.build(result.toString());
    }
//    private String directions(PgLevel request, Solution solution) {
//        StringBuilder res = new StringBuilder();
//
//        for(int i = 0; i < request.getNumOfRows(); i++) {
//            for(int j = 0; j < request.getNumOfCol(); j++) {
//                Part left = request.getObject(i,j);
//                Part right =  PartBuilder.build(solution.getChar(i,j));
//                /**
//                 * Write vector if and only both chars are not equals.
//                 * Either way, both will work.
//                 */
//                if (!left.toString().equals(right.toString())) {
//                    res.append(i).append(",").append(j).append(",").append(((Pipe)left).rotate((Pipe)right)).append("\n");
//                }
//
//            }
//        }
//        res.append("done");
//        System.out.println("Directions are:\n" + res.toString());
//
//        return res.toString();
//    }
}