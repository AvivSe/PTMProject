/**
 * PgSolver - This class implements solver for Pipe Game.
 * Solver may use algorithms as BFS / DFS / Best-FirstSearch / A Star / HillClimbing
 * In my project I use those abstract algorithms, adjacent to their pseudo code.
 * The idea is to use those alg' as a black box, we only have to create the right adapter in order to use them.
 * Aviv Segal 12/2017
 */

package pipe_game_server;

import searcher_interface.*;
import game_server_interface.Solver;

public class PgSolver implements Solver {
    @Override
    public PgDirections solve(PgLevel level) {

        Searcher<PgLevel> BFSSearcher = new BFS<>();
        Searcher<PgLevel> DFSSearcer = new DFS<>();
        Searcher<PgLevel> BestFirstSearcher = new BestFirstSearch<>(new PgManhattanDistance());
        Searcher<PgLevel> HillClimbingManhattan = new HillClimbing<>(5000, new PgManhattanDistance());

        Solution solution;

        PgSearchable pgSearchable = new PgSearchable(level);

        //solution = RandomHB.search(pgSearchable);
       //solution = HillClimbingManhattan.search(pgSearchable);

       // if(solution == null) {
            //solution = BFSSearcher.search(pgSearchable);
            //solution = DFSSearcer.search(pgSearchable);
            solution = BestFirstSearcher.search(pgSearchable);
     //   }

        return new PgDirections(solution, level);
    }

    public static void main(String[] args) {
        PgSolver mySolver = new PgSolver();
        PgLevel level = LevelBuilder.build(
                "s|g");

//        System.out.println("You ask for solution to: ");
//        System.out.println(level);

        try {
            long startTime = System.nanoTime();
            PgDirections vectors = mySolver.solve(level);
            long endTime = System.nanoTime();
            // put here something to check
            long duration = (endTime - startTime);
            Long ms = duration / 1000000;
            Double sec = (double) duration / 1000000000.0;

//            System.out.println("TOTAL: " + ms + "ms" + " (" + sec + "sec)");
//            System.out.println("\nThe vectors backtrace is:");

//            if (vectors != null) {
//                System.out.println(vectors.toString());
//            }
        } catch (NullPointerException ignored) {
        }


    }
}
