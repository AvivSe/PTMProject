/**
 * The idea of this class is to take the generic Solution which taken from one the abstract algorithms,
 * and to adapt it for our Pipe Game.
 * In our protocol we use vectors to describes the steps from start to goal,
 * with this requirement, we take each state given by the backtrace, analyzes the specific part was change on the grid,
 * and send it to client as vectors. for example (0,1,2) means - go to row 0, col 1, and rotate 180 degrees (click twice)
 *
 * Aviv Segal
 */

package pipe_game_server;
import game_server_interface.Directions;
import searcher_interface.Solution;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.HashSet;

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

        int N = request.getNumOfRows();
        int M = request.getNumOfCol();

        if(solution.size() < M*N) {
            System.out.println(":)");
            for (PgLevel solu : solution) {
                int x = solu.position.x;
                int y = solu.position.y;
                char c = solu.getObjectOnPosition();
                char c2 = request.getObject(x, y);
                if (c != ' ' && c != 's' && c != 'g' && c != c2) {
                    add(x + "," + y + "," + timesToRotate(c2, c));
                }
            }
        } else {
            PgLevel finalState = solution.get(solution.size()-1);
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    char left = request.getObject(i,j);
                    char right =  finalState.getObject(i,j);
                    if (left != right) {
                        int timesToRotate = timesToRotate(left, right);
                        if (timesToRotate != 0) {
                            add(i + "," + j + "," + timesToRotate);
                        }
                    }
                }
            }
        }

    }

    private int timesToRotate(char left, char right) {
        int timesToRotate = 0;
        switch (left) {
            case '|':
            case '-':
                timesToRotate = 1;
                break;

            case 'L':
                switch (right) {
                    case 'F':
                        timesToRotate = 1;
                        break;
                    case '7':
                        timesToRotate = 2;
                        break;
                    case 'J':
                        timesToRotate = 3;
                        break;
                } break;

            case 'F':
                switch (right) {
                    case '7':
                        timesToRotate = 1;
                        break;
                    case 'J':
                        timesToRotate = 2;
                        break;
                    case 'L':
                        timesToRotate = 3;
                        break;
                } break;

            case '7':
                switch (right) {
                    case 'J':
                        timesToRotate = 1;
                        break;
                    case 'L':
                        timesToRotate = 2;
                        break;
                    case 'F':
                        timesToRotate = 3;
                        break;
                } break;

            case 'J':
                switch (right) {
                    case 'L':
                        timesToRotate = 1;
                        break;
                    case 'F':
                        timesToRotate = 2;
                        break;
                    case '7':
                        timesToRotate = 3;
                        break;
                } break;
        }
        return timesToRotate;
    }

    public static void main(String[] args) {
        PgLevel source = PgLevel.LevelBuilder.build("LLL\nFFF\nJJJ\n777");

        Solution<PgLevel> sol = new Solution<>();
        State<PgLevel> targerState = new State<PgLevel>(PgLevel.LevelBuilder.build("F7J\n7JL\nLF7\nJLF"));
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());
        sol.add(targerState.getState());

        PgDirections directions = new PgDirections(sol, source);
        for(String s: directions) {
            System.out.println(s);
        }

    }
}
