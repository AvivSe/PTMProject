package pipe_game_server;
// TODO: Problem may be a class.

import common_searchers.BFS;
import searcher_interface.*;
import game_server_interface.Solver;

public class PgSolver implements Solver {

    // TODO: create algorithms that implements Searcher.
    @Override
    public PgDirections solve(PgLevel level) {
        Searcher<PgLevel> searcher = new BFS<>();
        return new PgDirections(searcher.search(new PgSearchable(level)), level);
    }

    public static void main(String[] args) {
        PgSolver mySolver = new PgSolver();
        PgLevel level = PgLevel.LevelBuilder.build(" 7- \n" +
                "Fs-7\n" +
                "7-g ");
        System.out.println("You ask for solution to: ");
        System.out.println(level);
        long startTime = System.nanoTime();
        PgDirections vectors = mySolver.solve(level);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000 ;
        System.out.println("LOG: " + duration + "ms");
        System.out.println("\nThe vectors backtrace is:");
        System.out.println(vectors.toString());

    }
}
