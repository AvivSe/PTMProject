package eightPuzzleGame;

import common_searchers.BFS;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import server_interface.Instructions;
import server_interface.Solver;
import java.util.List;

public class PuzzleSolver implements Solver<Puzzle> {
    @Override
    public PuzzleInstructions solve(Puzzle problem) {
        Searcher BFS = new BFS();
        Solution<Puzzle> solution = BFS.search(new PuzzleSearchable(problem));

        return new PuzzleInstructions(solution);
    }

    public static void main(String[] args) {
        byte[][] puzzleData={
                {8,3,5},
                {4,1,6},
                {2,7,0}
        };

        Puzzle puzzle = new Puzzle(puzzleData);
        PuzzleSolver puzzleSolver = new PuzzleSolver();

        List<String> actions = puzzleSolver.solve(puzzle);

        System.out.println(actions.size());
    }
}
