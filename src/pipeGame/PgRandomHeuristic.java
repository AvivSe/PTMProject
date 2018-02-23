package pipeGame;

import searcher_interface.Heuristic;
import searcher_interface.State;

import java.util.Random;

public class PgRandomHeuristic implements Heuristic<PgLevel> {
    private Random random = new Random();
    @Override
    public double indication() {
        return 0;
    }

    @Override
    public boolean isLeftBetter(double left, double right) {
        return left > right;
    }

    @Override
    public double calcHeuristic(State<PgLevel> s) {
        return (s.getCost() *random.nextInt());
    }
}
