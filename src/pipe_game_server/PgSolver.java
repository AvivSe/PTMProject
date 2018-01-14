package pipe_game_server;
// TODO: Problem may be a class.

import game_server.*;
import server.Solver;

public class PgSolver implements Solver {

    // TODO: create algorithms that implements Searcher.
    @Override
    public Solution solve(PgLevel level) {
       Searcher searcher = new BreadthFirstSearch(); // Could be any type of searcher.
        return searcher.search(new PgSearchable(level));
    }

    public static void main(String[] args) {
        PgSolver mySolver = new PgSolver();
        PgLevel level = PgLevel.LevelBuilder.build("s|J\n  -\n FL\n g ");
        Solution sol = mySolver.solve(level);
        System.out.println("\nAnd the solution is: \n" + sol);

    }
}
