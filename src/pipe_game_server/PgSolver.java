package pipe_game_server;
// TODO: Problem may be a class.

import common_searchers.BFS;
import common_searchers.DFS;
import searcher_interface.*;
import game_server_interface.Solver;

public class PgSolver implements Solver {

    // TODO: create algorithms that implements Searcher.
    @Override
    public PgDirections solve(PgLevel level) {
        //Searcher<PgLevel> searcher = new BFS<>();
        Searcher<PgLevel> searcher = new DFS<>();
        return new PgDirections(searcher.search(new PgSearchable(level,true)), level);
    }

    public static void main(String[] args) {
        PgSolver mySolver = new PgSolver();
        PgLevel level = PgLevel.LevelBuilder.build(
                "s|J \n" +
                        "--F7\n" +
                        "-L |\n" +
                        "7F|L\n" +
                        " g -");
//        "s|F \n" +
//                " L77\n" +
//                "LF |\n" +
//                " 7-7\n" +
//                " g -"
        System.out.println("You ask for solution to: ");
        System.out.println(level);

        try {
            long startTime = System.nanoTime();
            PgDirections vectors = mySolver.solve(level);
            long endTime = System.nanoTime();

            long duration = (endTime - startTime);
            Long ms = duration / 1000000;
            Double sec = (double) duration / 1000000000.0;

            System.out.println("TOTAL: " + ms + "ms" + " ("+sec+"sec)");
            System.out.println("\nThe vectors backtrace is:");
            if (vectors != null) {
                System.out.println(vectors.toString());
            }
        } catch (NullPointerException ignored){}




    }
}
