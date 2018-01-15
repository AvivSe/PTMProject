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
        Searchable searchable = new PgSearchable(level);
        PgDirections pgDirections = new PgDirections();
        Solution<PgLevel> solution = searcher.search(searchable);

        pgDirections.add(solution.toString());
        return pgDirections;
    }

    public static void main(String[] args) {
        PgSolver mySolver = new PgSolver();
        PgLevel level = PgLevel.LevelBuilder.build("s|J\n  -\n  g");

        PgDirections directions = mySolver.solve(level);

        System.out.println("\nAnd the direction are: \n");


        for(String item: directions) {
            System.out.println(item);
            System.out.println();
        }

    }
}
