package pipe_game_server;

import searcher_interface.Heuristic;
import searcher_interface.State;

import java.util.Random;

public class PgRandomHeuristic implements Heuristic<PgLevel> {
    private Random random = new Random();

    @Override
    public double calcHeuristic(State<PgLevel> s) {
        return (s.getCost() *random.nextInt());
    }
}
