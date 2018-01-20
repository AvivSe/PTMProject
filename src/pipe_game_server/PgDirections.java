package pipe_game_server;

import game_server_interface.Directions;
import parts.Part;
import parts.Pipe;
import searcher_interface.Solution;

import java.util.ArrayList;

public class PgDirections extends ArrayList<String> implements Directions {
    public PgDirections() {
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String item: this) {
            stringBuilder.append(item).append("\n");
        }
        return stringBuilder.append("done").toString();
    }

    // Visual
    public PgDirections(Solution<PgLevel> solution) {
        for (PgLevel state : solution) {
            add(state.toString());
        }
    }

    // Vectors
    public PgDirections(Solution<PgLevel> solution, PgLevel request) {
        PgLevel finalState = solution.get(solution.size()-1);
        for(int i = 0; i < request.getNumOfRows(); i++) {
            for(int j = 0; j < request.getNumOfCol(); j++) {
                Part left = request.getObject(i,j);
                Part right =  finalState.getObject(i,j);
                /**
                 * Write vector if and only both chars are not equals.
                 * Either way, both will work.
                 */
                // TODO: handle this down casting.
                if (!left.charface().equals(right.charface())) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(i).append(",").append(j).append(",").append(((Pipe)left).rotate((Pipe)right));
                    add(stringBuilder.toString());
                }
            }
        }
    }
}
