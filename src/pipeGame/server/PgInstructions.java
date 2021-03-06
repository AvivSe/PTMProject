/**
 * The idea of this class is to take the generic Solution which taken from one the abstract algorithms,
 * and to adapt it for our Pipe Game.
 * In our protocol we use vectors to describes the steps from start to goal,
 * with this requirement, we take each state given by the backtrace, analyzes the specific part was change on the grid,
 * and send it to client as vectors. for example (0,1,2) means - go to row 0, col 1, and rotate 180 degrees (click twice)
 *
 * Aviv Segal
 */

package pipeGame.server;
import server_interface.Instructions;
import searcher_interface.Solution;

import java.util.ArrayList;

public class PgInstructions extends ArrayList<String> implements Instructions {
    public PgInstructions() {
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
    public PgInstructions(Solution<PgLevel> solution) {
        for (PgLevel state : solution) {
            add(state.toString());
        }
    }

    // Vectors
    public PgInstructions(Solution<PgLevel> solution, PgLevel request) {
        for (PgLevel outer : solution) {
            int x = outer.getRow();
            int y = outer.getCol();

            char c = outer.getObjectOnPosition();

            char c2 = request.getObject(x, y);
            if (c != ' ' && c != 's' && c != 'g' && c != c2) {
                add(x + "," + y + "," + timesToRotate(c2, c));
            }
        }
    }

    private int timesToRotate(char left, char right) {
        if(left == right) return 0;

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
}
