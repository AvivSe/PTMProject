package pipe_game_server;

import searcher_interface.Searchable;
import searcher_interface.State;

import java.awt.*;
import java.util.*;

public class PgSearchable implements Searchable<PgLevel> {
    private boolean optimize;
    private State<PgLevel> initialState;
    static ArrayList<State<PgLevel>> pool = new ArrayList<>();


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

    public void AnalyzePossibleState(ArrayList<State<PgLevel>> list, State<PgLevel> state, Point position, String move) {
        if (position.x < 0 || position.x >= state.getState().getNumOfRows() ||
                position.y < 0 || position.y >= state.getState().getNumOfCol())
            return;

        PgLevel level = state.getState();
        PgLevel tmp = new PgLevel(level);
        tmp.position = new Point(position.x,position.y);
        Queue<State<PgLevel>> queue =  new LinkedList<>();

        switch (move) {
            case "Move Up":
                switch (level.getObject(position.x,position.y)) {
                    case 'L':
                    case 'J':
                    case 'F':
                    case '7':
                        tmp.setObject(position.x,position.y, 'F');
                        queue.add(new State<>(new PgLevel(tmp)));
                        tmp.setObject(position.x,position.y, '7');
                        queue.add(new State<>(new PgLevel(tmp)));
                        break;
                    case '-':
                    case '|':
                        tmp.setObject(position.x,position.y, '|');
                        queue.add(new State<>(new PgLevel(tmp)));;
                        break;
                } break;

            case "Move Down":
                switch (level.getObject(position.x,position.y)) {
                    case 'L':
                    case 'J':
                    case 'F':
                    case '7':
                        tmp.setObject(position.x,position.y, 'J');
                        queue.add(new State<>(new PgLevel(tmp)));
                        tmp.setObject(position.x,position.y, 'L');
                        queue.add(new State<>(new PgLevel(tmp)));
                        break;
                    case '-':
                    case '|':
                        tmp.setObject(position.x,position.y, '|');
                        queue.add(new State<>(new PgLevel(tmp)));
                        break;
                } break;

            case "Move Right":
                switch (level.getObject(position.x,position.y)) {
                    case 'L':
                    case 'J':
                    case 'F':
                    case '7':
                        tmp.setObject(position.x,position.y, 'J');
                        queue.add(new State<>(new PgLevel(tmp)));
                        tmp.setObject(position.x,position.y, '7');
                        queue.add(new State<>(new PgLevel(tmp)));
                        break;
                    case '-':
                    case '|':
                        tmp.setObject(position.x,position.y, '-');
                        queue.add(new State<>(new PgLevel(tmp)));
                        break;
                } break;

            case "Move Left":
                switch (level.getObject(position.x,position.y)) {
                    case 'L':
                    case 'J':
                    case 'F':
                    case '7':
                        tmp.setObject(position.x,position.y, 'F');
                        queue.add(new State<>(new PgLevel(tmp)));
                        tmp.setObject(position.x,position.y, 'L');
                        queue.add(new State<>(new PgLevel(tmp)));
                        break;
                    case '-':
                    case '|':
                        tmp.setObject(position.x,position.y, '-');
                        queue.add(new State<>(new PgLevel(tmp)));
                        break;
                } break;
        }

        while(!queue.isEmpty()) {
            State<PgLevel> current = queue.remove();
            if (!pool.contains(current)) {
                list.add(current);
            }
        }
    }

    @Override
    public ArrayList<State<PgLevel>> getPossibleStates(State<PgLevel> state) {
        ArrayList<State<PgLevel>> possibleStates = new ArrayList<>();
        int row = state.getState().position.x;
        int col = state.getState().position.y;

        for (String move : nextSteps(state.getState())) {
            switch (move) {
                case "Move Up":
                    AnalyzePossibleState(possibleStates, state, new Point(row - 1, col), move);
                    break;
                case "Move Right":
                    AnalyzePossibleState(possibleStates, state, new Point(row, col + 1), move);
                    break;
                case "Move Left":
                    AnalyzePossibleState(possibleStates, state, new Point(row, col - 1), move);
                    break;
                case "Move Down":
                    AnalyzePossibleState(possibleStates, state, new Point(row + 1 , col), move);
                    break;
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

    private ArrayList<String> nextSteps(PgLevel level) {

        ArrayList<String> result = new ArrayList<>();

        int i = level.position.x;
        int j = level.position.y;

        char c = level.getObject(i,j);

        switch (c) {
            case 's':
                result.add("Move Up");
                result.add("Move Right");
                result.add("Move Down");
                result.add("Move Left");
                break;
            case 'L':
                result.add("Move Up");
                result.add("Move Right");
                break;
            case 'F':
                result.add("Move Down");
                result.add("Move Right");
                break;
            case '7':
                result.add("Move Down");
                result.add("Move Left");
                break;
            case 'J':
                result.add("Move Up");
                result.add("Move Left");
                break;
            case '|':
                result.add("Move Down");
                result.add("Move Up");
                break;
            case '-':
                result.add("Move Left");
                result.add("Move Right");
                break;
        }
        return result;
    }

    static boolean up(int i, int j, PgLevel level) {
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

    static boolean right(int i, int j, PgLevel level) {
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

    static boolean left(int i, int j, PgLevel level) {
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

    static boolean down(int i, int j, PgLevel level) {
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
        return findGoal(start.x, start.y, level);
    }
    private static boolean findGoal(int i, int j, PgLevel level) {
        level = level.copy();
        char c = level.getObject(i, j);
        level.setObject(i, j, ' ');

        switch (c) {
            case 'g':
                return true;
            case 's':
                return up(i, j, level) && findGoal(i - 1, j, level) ||
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
                "J-F-7   J-|-J   \n" +
                        " -g L-7  -7 L-7 \n" +
                        "-|L7-|L-7|L7-|L7\n" +
                        "-F |-F ||F |-F |\n" +
                        "-F-J7F-J|F-J7F-J\n" +
                        "J||-JLL-L-7- J -\n" +
                        "J-|-J F J-|-JF  \n" +
                        " -7 L-7JL-L-7-7 \n" +
                        "-|L7-|L7-|L7||L7\n" +
                        "-F |-F-|-FJ||F |\n" +
                        "-F-J7F-J-F-JL7-J\n" +
                        "J||-   -J||- s -");

        System.out.println(level);
        /* Is goals state + time */
        PgSearchable searchable = new PgSearchable(level);



        long startTime = System.nanoTime();
        System.out.println(searchable.isGoalState(new State<>(level)));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        Long ms = duration / 1000000;
        Double sec = (double) duration / 1000000000.0;
        System.out.println("isGoalState took: " + ms + "ms" + " ("+sec+"sec)");

//        System.out.println("---------------------------");
//
//        long startTime2 = System.nanoTime();
//        System.out.println(searchable.isGoalState(new State<PgLevel>(level)));
//        long endTime2 = System.nanoTime();
//        long duration2 = (endTime2 - startTime2);
//        Long ms2 = duration2 / 1000000;
//        Double sec2 = (double) duration2 / 1000000000.0;
//
//        System.out.println("isGoalState took: " + ms2 + "ms" + " ("+sec2+"sec)");
//

//        /* Print only next possible states */
//        ArrayList<State<PgLevel>> list = searchable.getPossibleStates(new State<>(level));
//        int i = 0;
//        for(State<PgLevel> item: list) {
//            System.out.println("*****");
//            System.out.println("* "+ ++i +" *");
//            System.out.println("*****");
//            System.out.println(item);
//            System.out.println();
//            ArrayList<State<PgLevel>> list2 = searchable.getPossibleStates(new State<>(item.getState()));
//            int j= 0;
//            for(State<PgLevel> item2: list2) {
//                System.out.println("*******");
//                System.out.println("* "+ i +"."+ ++j + " *");
//                System.out.println("*******");
//                System.out.println(item2);
//                System.out.println();
//            }
//        }

//        /* next steps test */
//        for(String str: searchable.nextSteps(level)) {
//            System.out.println(str);
//        }



//        level.position = new Point(1,2);
//        for(State<PgLevel> state: searchable.getPossibleStates(new State<>(level))) {
//            System.out.println("**"+ state.getState().position +"****");
//            System.out.println(state.getState());
//            for(State<PgLevel> state2: searchable.getPossibleStates(new State<>(state.getState()))) {
//                System.out.println("**"+ state2.getState().position +"****");
//                System.out.println(state2.getState());
//                if(searchable.isGoalState(state2)) {
//                    System.out.println("Goooal");
//                }
//            }
//        }


    }


}