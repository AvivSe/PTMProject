package pipe_game_server;

import game_server_interface.Directions;
import searcher_interface.Solution;

import java.util.ArrayList;

public class PgDirections extends ArrayList<String> implements Directions {
    public PgDirections() {
    }

    public PgDirections(Solution solution) {
        add(solution.toString());
    }
}
