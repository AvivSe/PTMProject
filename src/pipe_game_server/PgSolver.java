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
        PgLevel level = PgLevel.LevelBuilder.build("s|J\n  -\n  g");
        System.out.println("You ask for solution to: ");
        System.out.println(level);
        PgDirections directions = mySolver.solve(level);
        System.out.println("\nAnd the direction are:");
        System.out.println(directions.toString());
    }
}
