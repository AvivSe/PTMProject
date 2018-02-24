package eightPuzzleGame;

import searcher_interface.Heuristic;
import searcher_interface.State;

public class PuzzleHeuristic implements Heuristic<Puzzle> {
    @Override
    public double indication() {
        return 0;
    }

    @Override
    public boolean isLeftBetter(double left, double right) {
        return left < right;
    }

    @Override
    public double calcHeuristic(State<Puzzle> state) {

        return 1;
    }
}
