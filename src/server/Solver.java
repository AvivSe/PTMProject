package server;

import pipe_game_server.PgLevel;
import game_server.Solution;

public interface Solver {
    public Solution solve(PgLevel level);
}