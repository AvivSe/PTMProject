/**
 * In order to use my abstract algorithms, I created an object adapter to my Pipe Game problem.
 * This class is kind of the heart of this project, this is the bridge between the Pipe Game problem and -
 * - the "Black Box" algorithms classes.
 * Aviv Segal
 */
package pipe_game_server;

import searcher_interface.Searchable;
import searcher_interface.State;
import java.awt.*;
import java.util.*;
import java.util.List;
import static pipe_game_server.PgSearchable.Direction.*;

public class PgSearchable implements Searchable<PgLevel> {
    private State<PgLevel> initialState;

    PgSearchable(PgLevel level) {
        this.initialState = new State<>(level);
    }

    @Override
    public State<PgLevel> getInitialState() {
        return initialState;
    }

    private boolean isOutOfBound(int i, int j) {
        return (i < 0 || i >= initialState.getState().getNumOfRows() ||
                j < 0 || j >= initialState.getState().getNumOfCol());
    }

    private Boolean hasNoPipe(PgLevel level, Point position) {
        char c = level.getObject(position.x, position.y);
        return c == ' ';
    }

    private Boolean isCurvedPipe(PgLevel level, Point position) {
        char c = level.getObject(position.x, position.y);
        return c == 'L' || c == 'J' || c == 'F' || c == '7';
    }

    private Boolean isGoal(PgLevel level, Point position) {
        char c = level.getObject(position.x, position.y);
        return c == 'g';
    }

    private Point applyMoveToDirection(int row, int col, Direction dir) {
        switch (dir) {
            case UP:
                return new Point(row - 1, col);
            case DOWN:
                return new Point(row + 1, col);
            case LEFT:
                return new Point(row, col - 1);
            case RIGHT:
                return new Point(row, col + 1);
        }
        return null;
    }

    private void AnalyzePossibleState(List<State<PgLevel>> list, State<PgLevel> state, Direction cameFromDirection) {
        int row = state.getState().getX();
        int col = state.getState().getY();
        Point position = applyMoveToDirection(row, col, cameFromDirection);

        /* case you out of matrix bounds */
        if (isOutOfBound(position.x, position.y)) return;

        /* case you moved where you came from */
        if (state.getCameFrom() != null) {
            int cameFromX = state.getCameFrom().getState().getX();
            int cameFromY = state.getCameFrom().getState().getY();
            if (position.x == cameFromX && position.y == cameFromY)
                return;
        }

        PgLevel level = state.getState();

        /* editing only deep copy */
        PgLevel tmp = new PgLevel(level);

        if (hasNoPipe(level, position)) return;

        if (isGoal(level, position)) {
            tmp.setX(position.x);
            tmp.setY(position.y);
            list.add(new State<>(new PgLevel(tmp)));
            return;
        }

        tmp.setX(position.x);
        tmp.setY(position.y);

        switch (cameFromDirection) {
            case UP:
                if (isCurvedPipe(level, position)) {
                    tmp.setObject(position.x, position.y, 'F');
                    list.add(new State<>(new PgLevel(tmp)));
                    tmp.setObject(position.x, position.y, '7');
                    list.add(new State<>(new PgLevel(tmp)));
                } else {
                    tmp.setObject(position.x, position.y, '|');
                    list.add(new State<>(new PgLevel(tmp)));
                }
                break;

            case DOWN:
                if (isCurvedPipe(level, position)) {
                    tmp.setObject(position.x, position.y, 'J');
                    list.add(new State<>(new PgLevel(tmp)));
                    tmp.setObject(position.x, position.y, 'L');
                    list.add(new State<>(new PgLevel(tmp)));
                } else {
                    tmp.setObject(position.x, position.y, '|');
                    list.add(new State<>(new PgLevel(tmp)));
                }
                break;

            case RIGHT:
                if (isCurvedPipe(level, position)) {
                    tmp.setObject(position.x, position.y, 'J');
                    list.add(new State<>(new PgLevel(tmp)));
                    tmp.setObject(position.x, position.y, '7');
                    list.add(new State<>(new PgLevel(tmp)));
                } else {
                    tmp.setObject(position.x, position.y, '-');
                    list.add(new State<>(new PgLevel(tmp)));
                }
                break;

            case LEFT:
                if (isCurvedPipe(level, position)) {
                    tmp.setObject(position.x, position.y, 'F');
                    list.add(new State<>(new PgLevel(tmp)));
                    tmp.setObject(position.x, position.y, 'L');
                    list.add(new State<>(new PgLevel(tmp)));
                } else {
                    tmp.setObject(position.x, position.y, '-');
                    list.add(new State<>(new PgLevel(tmp)));
                }
                break;
        }
    }

    @Override
    public ArrayList<State<PgLevel>> getPossibleStates(State<PgLevel> state) {
        ArrayList<State<PgLevel>> possibleStates = new ArrayList<>();
        int row = state.getState().getX();
        int col = state.getState().getY();

        List<Direction> nextSteps = nextSteps(state.getState());

        for (Direction dir : nextSteps) {
            AnalyzePossibleState(possibleStates, state, dir);
        }

        for (State<PgLevel> s : possibleStates) {
            s.setCameFrom(state);
            s.setCost(state.getCost() + 1);
        }

        return possibleStates;
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT}

    private List<Direction> nextSteps(PgLevel level) {

        ArrayList<Direction> result = new ArrayList<>();

        int i = level.getX();
        int j = level.getY();

        char c = level.getObject(i, j);

        switch (c) {
            case 's':
                result.add(UP);
                result.add(RIGHT);
                result.add(DOWN);
                result.add(LEFT);
                break;
            case 'L':
                result.add(UP);
                result.add(RIGHT);
                break;
            case 'F':
                result.add(DOWN);
                result.add(RIGHT);
                break;
            case '7':
                result.add(DOWN);
                result.add(LEFT);
                break;
            case 'J':
                result.add(UP);
                result.add(LEFT);
                break;
            case '|':
                result.add(DOWN);
                result.add(UP);
                break;
            case '-':
                result.add(LEFT);
                result.add(RIGHT);
                break;
        }
        return result;
    }

    public boolean isGoalState(State<PgLevel> state) {
        return state.getState().getObjectOnPosition() == 'g';
        }


}