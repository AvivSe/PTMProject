package wordGame;

import searcher_interface.Heuristic;
import searcher_interface.State;

public class EmptyHeur implements Heuristic<MoveHelper> {
    @Override
    public double indication() {
        return 0;
    }

    @Override
    public boolean isLeftBetter(double left, double right) {
        return left < right;
    }

    @Override
    public double calcHeuristic(State<MoveHelper> state) {
        return 1;
    }
}
