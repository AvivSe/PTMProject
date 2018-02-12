package pipe_game_server;

import game_server_interface.Directions;
import searcher_interface.Solution;
import searcher_interface.State;

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
                char left = request.getObject(i,j);
                char right =  finalState.getObject(i,j);
                /**
                 * Write vector if and only both chars are not equals.
                 * Either way, both will work.
                 */
                if (left != right) {
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

                    if (timesToRotate != 0) {
                        add(String.valueOf(i) + "," + j + "," + timesToRotate);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PgLevel source = PgLevel.LevelBuilder.build("LLL\nFFF\nJJJ\n777");

        Solution<PgLevel> sol = new Solution<>();
        State<PgLevel> targerState = new State<PgLevel>(PgLevel.LevelBuilder.build("FJ7\nJ7F\n7LF\nLFJ"));
        sol.add(targerState.getState());
        PgDirections directions = new PgDirections(sol, source);

        for(String s: directions) {
            System.out.println(s);
        }

    }
}
