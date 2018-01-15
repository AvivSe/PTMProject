package game_server_interface;

import pipe_game_server.PgLevel;

public interface Solver {
    public Directions solve(PgLevel level);
}