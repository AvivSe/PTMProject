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

public class PgSearchable implements Searchable<PgLevel> {
    private State<PgLevel> initialState;
    private ArrayList<PgLevel> visited;

    PgSearchable(PgLevel level) {
        this.initialState = new State<>(level);
        visited = new ArrayList<>();
    }

    @Override
    public State<PgLevel> getInitialState() {
        return initialState;
    }

    private boolean isOutOfBound(int i, int j) {
        return (i < 0 || i >= initialState.getState().getNumOfRows() ||
                j < 0 || j >= initialState.getState().getNumOfCol());
    }

    private void AnalyzePossibleState(ArrayList<State<PgLevel>> list, State<PgLevel> state, Point position, String move) {
        if (isOutOfBound(position.x, position.y))
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
                        queue.add(new State<>(tmp.copy()));
                        tmp.setObject(position.x,position.y, '7');
                        queue.add(new State<>(tmp.copy()));
                        break;
                    case '-':
                    case '|':
                        tmp.setObject(position.x,position.y, '|');
                        queue.add(new State<>(tmp.copy()));;
                        break;
                } break;

            case "Move Down":
                switch (level.getObject(position.x,position.y)) {
                    case 'L':
                    case 'J':
                    case 'F':
                    case '7':
                        tmp.setObject(position.x,position.y, 'J');
                        queue.add(new State<>(tmp.copy()));
                        tmp.setObject(position.x,position.y, 'L');
                        queue.add(new State<>(tmp.copy()));
                        break;
                    case '-':
                    case '|':
                        tmp.setObject(position.x,position.y, '|');
                        queue.add(new State<>(tmp.copy()));
                        break;
                } break;

            case "Move Right":
                switch (level.getObject(position.x,position.y)) {
                    case 'L':
                    case 'J':
                    case 'F':
                    case '7':
                        tmp.setObject(position.x,position.y, 'J');
                        queue.add(new State<>(tmp.copy()));
                        tmp.setObject(position.x,position.y, '7');
                        queue.add(new State<>(tmp.copy()));
                        break;
                    case '-':
                    case '|':
                        tmp.setObject(position.x,position.y, '-');
                        queue.add(new State<>(tmp.copy()));
                        break;
                } break;

            case "Move Left":
                switch (level.getObject(position.x,position.y)) {
                    case 'L':
                    case 'J':
                    case 'F':
                    case '7':
                        tmp.setObject(position.x,position.y, 'L');
                        queue.add(new State<>(tmp.copy()));
                        tmp.setObject(position.x,position.y, 'F');
                        queue.add(new State<>(tmp.copy()));
                        break;
                    case '-':
                    case '|':
                        tmp.setObject(position.x,position.y, '-');
                        queue.add(new State<>(tmp.copy()));
                        break;
                } break;
        }

        while(!queue.isEmpty()) {
            State<PgLevel> current = queue.remove();
                current.setCameFrom(state);
                current.setCost(state.getCost() + 1);
                list.add(current);
        }
    }
//
//    @Override
//    public ArrayList<State<PgLevel>> getPossibleStates(State<PgLevel> state) {
//        ArrayList<State<PgLevel>> possibleStates = new ArrayList<>();
//        int row = state.getState().position.x;
//        int col = state.getState().position.y;
//
//
//
//        for (String move : nextSteps(state.getState())) {
//            switch (move) {
//                case "Move Up":
//                    AnalyzePossibleState(possibleStates, state, new Point(row - 1, col), move);
//                    break;
//                case "Move Right":
//                    AnalyzePossibleState(possibleStates, state, new Point(row, col + 1), move);
//                    break;
//                case "Move Left":
//                    AnalyzePossibleState(possibleStates, state, new Point(row, col - 1), move);
//                    break;
//                case "Move Down":
//                    AnalyzePossibleState(possibleStates, state, new Point(row + 1 , col), move);
//                    break;
//            }
//        }
//
////        for(State<PgLevel> s: possibleStates) {
////            s.setCameFrom(state);
////        }
//        return possibleStates;
//    }
//
//
//    private ArrayList<String> nextSteps(PgLevel level) {
//
//        ArrayList<String> result = new ArrayList<>();
//
//        int i = level.position.x;
//        int j = level.position.y;
//
//        char c = level.getObject(i,j);
//
//        switch (c) {
//            case 's':
//                result.add("Move Up");
//                result.add("Move Right");
//                result.add("Move Down");
//                result.add("Move Left");
//                break;
//            case 'L':
//                result.add("Move Up");
//                result.add("Move Right");
//                break;
//            case 'F':
//                result.add("Move Down");
//                result.add("Move Right");
//                break;
//            case '7':
//                result.add("Move Down");
//                result.add("Move Left");
//                break;
//            case 'J':
//                result.add("Move Up");
//                result.add("Move Left");
//                break;
//            case '|':
//                result.add("Move Down");
//                result.add("Move Up");
//                break;
//            case '-':
//                result.add("Move Left");
//                result.add("Move Right");
//                break;
//        }
//        return result;
//    }
    private ArrayList<String> nextSteps(char c) {

        ArrayList<String> result = new ArrayList<>();
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

    private boolean up(int i, int j, PgLevel level) {
        if(!isOutOfBound(i-1,j)) {
            switch (level.getObject(i - 1, j)) {
                case '7':
                case '|':
                case 'F':
                case 'g':
                    return true;
            }
        }
        return false;
    }

     private boolean right(int i, int j, PgLevel level) {
         if(!isOutOfBound(i,j + 1)) {
             switch (level.getObject(i, j + 1)) {
                 case '7':
                 case 'J':
                 case '-':
                 case 'g':
                     return true;
             }
         }
        return false;
    }

     private boolean left(int i, int j, PgLevel level) {
         if(!isOutOfBound(i,j-1)) {
             switch (level.getObject(i, j - 1)) {
                 case 'F':
                 case 'L':
                 case '-':
                 case 'g':
                     return true;
             }
         }
        return false;
    }

     private boolean down(int i, int j, PgLevel level) {
         if(!isOutOfBound(i+1,j)) {
             switch (level.getObject(i + 1, j)) {
                 case 'J':
                 case 'L':
                 case '|':
                 case 'g':
                     return true;
             }
         }
        return false;
    }

    public boolean isGoalState(State<PgLevel> state) {
        PgLevel level = state.getState();
        return findGoal(level.start.x, level.start.y, level);
    }

    /* MERON CODE */
    @Override
    public ArrayList<State<PgLevel>> getPossibleStates(State<PgLevel> state) {
        ArrayList<State<PgLevel>> possibleStates = new ArrayList<>();


        for (int i = 0; i < state.getState().getNumOfRows(); i++) {
            for (int j = 0; j < state.getState().getNumOfCol(); j++) {
                int times = 0;

                switch (state.getState().getObject(i,j)) {
                    case 'L':
                    case 'F':
                    case 'J':
                    case '7':
                        times = 4;
                        break;
                    case '-':
                    case '|':
                        times = 2;
                        break;
                }

                State<PgLevel> s = new State<> (new PgLevel(state.getState()));
                s.setCameFrom(new State<PgLevel>(state.getState()));
                for(int k = 0; k < times+1; k++) {

                    s.getState().rotate(i,j).setPosition(i,j);
                    if(!visited.contains(s.getState()) && findGoal(i,j,new PgLevel(s.getState()).setObject(state.getState().start.x,state.getState().start.y,' '))) {
//                        System.out.println(s.getState());
//                        System.out.println(s.getState().getPosition().x);
//                        System.out.println(s.getState().getPosition().y);
                        possibleStates.add(s);
                        visited.add(s.getState());
                    }

                }
            }
        }
        //System.out.println("########");
        return possibleStates;
    }

    /* END MERON CODE */
//
//        public boolean isGoalState(State<PgLevel> state) {
//        PgLevel level = state.getState();
//        int i = level.position.x;
//        int j = level.position.y;
//
//        for(String move: nextSteps(level)) {
//            switch(move) {
//                case "Move Up":
//                    if(up(i,j,level) && level.getObject(i - 1 , j ) == 'g') return true;
//                    break;
//                case "Move Right":
//                    if(right(i,j,level) && level.getObject(i , j + 1 ) == 'g') return true;
//                    break;
//                case "Move Down":
//                    if(down(i,j,level) && level.getObject(i + 1, j ) == 'g') return true;
//                    break;
//                case "Move Left":
//                    if(left(i,j,level) && level.getObject(i , j - 1 ) == 'g') return true;
//                    break;
//            }
//        }
//
//
//        return false;
//    }


    @Override
    public double getStateEvaluation(State<PgLevel> state) {
        return PgHeuristic(state,1);
    }

    private boolean findGoal(int i, int j, PgLevel level) {
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


    private boolean findStart(int i, int j, PgLevel level) {
        level = level.copy();
        char c = level.getObject(i, j);
        level.setObject(i, j, ' ');

        switch (c) {
            case 's':
                return true;
            case 'g':
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


    static double PgHeuristic(State<PgLevel> s, int kind) {
        double movement_cost = s.getCost(); // TODO: Should be a better grade;
        int nodeX = s.getState().position.x;
        int nodeY = s.getState().position.y;
        int endX = s.getState().end.x;
        int endY = s.getState().end.y;

        switch (kind) {
            case 1:
                /** 1.
                 * The Manhattan Distance is taking the distance from going all the way on the X axis and adding that to the distance all the way on the Y axis to go from point A to point B.
                 * This heuristic should usually be used whenever the AI can only move in the 4 cardinal directions.
                 */
                return movement_cost * (Math.abs(nodeX - endX) + Math.abs(nodeY - endY));
            case 2:
                /** 2.
                 * The Pythagorean Distance is the most common form of distance, and is used when you can move in all directions.
                 */
                return movement_cost * Math.sqrt((nodeX - endX) ^ 2 + (nodeY - endY) ^ 2);
            case 3:
                /** 3.
                 * The Chebyshev Distance is used whenever you're allowed to move in diagonally, so in 8 directions.
                 * Assuming both straight and diagonals cost the same, this code should work sufficiently.
                 */
                return movement_cost * Math.max(Math.abs(nodeX - endX), Math.abs(nodeY - endY));

                default:
                    // Manhattan Distance is default
                    return movement_cost * (Math.abs(nodeX - endX) + Math.abs(nodeY - endY));
        }
    }


    public static void main(String[] args) {
        PgLevel level = PgLevel.LevelBuilder.build(
                "7-  \n" +
                        "Js-7\n" +
                        "7- g");

        //System.out.println(level.position);
        /* Is goals state + time */
        PgSearchable searchable = new PgSearchable(level);

        System.out.println(searchable.findGoal(1,2, level));
//
//        long startTime = System.nanoTime();
//        System.out.println(searchable.isGoalState(new State<>(level)));
//        long endTime = System.nanoTime();
//        long duration = (endTime - startTime);
//        Long ms = duration / 1000000;
//        Double sec = (double) duration / 1000000000.0;
//        System.out.println("isGoalState took: " + ms + "ms" + " ("+sec+"sec)");

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
//        }

//        /* next steps test */
//        for(String str: searchable.nextSteps(level)) {
//            System.out.println(str);
//        }

//        State<PgLevel> state = new State<>(level);
//        System.out.println(state.getCameFrom());
//
    }


}