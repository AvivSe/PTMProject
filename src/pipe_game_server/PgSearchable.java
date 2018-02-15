package pipe_game_server;


import searcher_interface.Searchable;
import searcher_interface.State;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PgSearchable implements Searchable<PgLevel> {
    private boolean optimize;

    private State<PgLevel> initialState;

    public PgSearchable(PgLevel level) {
        this.optimize = false;
        this.initialState = new State<>(level);
    }
    PgSearchable(PgLevel level, boolean optimize) {
        this.optimize = optimize;
        this.initialState = new State<>(level);
    }

    @Override
    public State<PgLevel> getInitialState() {
        return initialState;
    }

    @Override
    public ArrayList<State<PgLevel>> getPossibleStates(State<PgLevel> state) {
        ArrayList<State<PgLevel>> possibleStates = new ArrayList<>();
        PgLevel level = state.getState();



        for(int i = 0; i < level.getNumOfRows(); i++) {
            for (int j = 0; j < level.getNumOfCol(); j++) {
                char c = level.getObject(i, j);
                char toReplaceWith = ' ';
                if (c != ' ' && c != 's' && c != 'g') {
                    switch (c) {
                        case '|':
                            toReplaceWith = '-';
                            break;
                        case '-':
                            toReplaceWith = '|';
                            break;
                        case 'L':
                            toReplaceWith = 'F';
                            break;
                        case 'F':
                            toReplaceWith = '7';
                            break;
                        case '7':
                            toReplaceWith = 'J';
                            break;
                        case 'J':
                            toReplaceWith = 'L';
                            break;

                    }
                    if (canDoSomeOneNextStep(i,j, level,toReplaceWith)) {
                        PgLevel lvlCopy = level.copy();
                        lvlCopy.setObject(i, j, toReplaceWith);
                        possibleStates.add(new State<>(lvlCopy));
                    }
                }
            }
        }
        return possibleStates;
    }
    private boolean canDoSomeOneNextStep(int i, int j,PgLevel level , char toReplaceWith) {
        if (!optimize) return true;
        if (
        toReplaceWith == '7' && (left(i, j, level) || down(i, j, level)) ||
                toReplaceWith == 'J' && (up(i, j, level) || left(i, j, level)) ||
                toReplaceWith == 'F' && (right(i, j, level) || down(i, j, level)) ||
                toReplaceWith == 'L' && (right(i, j, level) || up(i, j, level)) ||
                toReplaceWith == '-' && (right(i, j, level) || left(i, j, level)) ||
                toReplaceWith == '|' && (up(i, j, level) || down(i, j, level)) ||
                /* FOR ANYTHING ELSE, check if you can go somewhere. */
                right(i, j, level) || left(i, j, level) || up(i, j, level) || down(i, j, level)
                ) {
            return true;
        }
        return false;
    }

    private static boolean up(int i, int j, PgLevel level) {
        try {
            switch (level.getObject(i-1,j)) {
                case '7':
                case '|':
                case 'F':
                case 'g':
                case 's':
                    return true;
            }
        } catch (IndexOutOfBoundsException | StackOverflowError error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean right(int i, int j, PgLevel level) {
        try {
            switch (level.getObject(i,j+1)) {
                case '7':
                case 'J':
                case '-':
                case 'g':
                case 's':
                    return true;
            }
        } catch (IndexOutOfBoundsException | StackOverflowError error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean left(int i, int j, PgLevel level) {
        try {
            switch (level.getObject(i,j-1)) {
                case 'F':
                case 'L':
                case '-':
                case 'g':
                case 's':
                    return true;
            }
        } catch (IndexOutOfBoundsException | StackOverflowError error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean down(int i, int j, PgLevel level) {
        try {
            switch (level.getObject(i+1,j)) {
                case 'J':
                case 'L':
                case '|':
                case 'g':
                case 's':
                    return true;
            }
        } catch (IndexOutOfBoundsException | StackOverflowError error) {
            //System.out.println(error);
        }
        return false;
    }

    public boolean isGoalState(State<PgLevel> state) {
        PgLevel level = state.getState();
        Point start = level.getStart();
        return findGoal(start  .x, start.y, level);
    }
    private static boolean findGoal(int i, int j, PgLevel level) {
        level = level.copy();
        char c = level.getObject(i, j);
        level.setObject(i, j, ' ');
        switch (c) {
            case 'g':
                return true;
            case 's':
                return  up(i, j, level) && findGoal(i - 1, j, level) ||
                        down(i, j, level) && findGoal(i + 1, j, level) ||
                        right(i, j, level) && findGoal(i, j + 1, level) ||
                        left(i, j, level) && findGoal(i, j - 1, level);
            case '|':
                return  up(i, j, level) && findGoal(i - 1, j, level) ||
                        down(i, j, level) && findGoal(i + 1, j, level);
            case '-':
                return  right(i, j, level) && findGoal(i, j + 1, level) ||
                        left(i, j, level) && findGoal(i, j - 1, level);
            case 'L':
                return  up(i, j, level) && findGoal(i - 1, j, level) ||
                        right(i, j, level) && findGoal(i, j + 1, level);
            case 'F':
                return  down(i, j, level) && findGoal(i + 1, j, level) ||
                        right(i, j, level) && findGoal(i, j + 1, level);
            case '7':
                return  down(i, j, level) && findGoal(i + 1, j, level) ||
                        left(i, j, level) && findGoal(i, j - 1, level);
            case 'J':
                return  up(i, j, level) && findGoal(i - 1, j, level) ||
                        left(i, j, level) && findGoal(i, j - 1, level);
            default:
                return false;
        }
    }



    public static void main(String[] args) {
        PgLevel level = PgLevel.LevelBuilder.build(
                "s-7 \n" +
                        " |L7\n" +
                        "-F |\n" +
                        "7F-J\n" +
                        " g -");

        System.out.println(level);
        /* Is goals state + time */
        PgSearchable searchable = new PgSearchable(level);

//        long startTime = System.nanoTime();
//        System.out.println(searchable.isGoalState(new State<PgLevel>(level)));
//        long endTime = System.nanoTime();
//        long duration = (endTime - startTime);
//        Long ms = duration / 1000000;
//        Double sec = (double) duration / 1000000000.0;
//        System.out.println("isGoalState took: " + ms + "ms" + " ("+sec+"sec)");

        System.out.println("---------------------------");

        long startTime2 = System.nanoTime();
        System.out.println(searchable.isGoalState(new State<PgLevel>(level)));
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);
        Long ms2 = duration2 / 1000000;
        Double sec2 = (double) duration2 / 1000000000.0;

        System.out.println("isGoalState took: " + ms2 + "ms" + " ("+sec2+"sec)");


        /* Print only next possible states */
//        ArrayList<State<PgLevel>> list = searchable.getPossibleStates(new State<>(level));
//        for(State<PgLevel> item: list) {
//            System.out.println(item);
//            System.out.println();
//        }

    }


}