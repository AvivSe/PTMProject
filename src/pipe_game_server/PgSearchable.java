package pipe_game_server;

import parts.Part;
import parts.PartBuilder;
import parts.Pipe;
import searcher_interface.Searchable;
import searcher_interface.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Predicate;

public class PgSearchable implements Searchable<PgLevel> {

    private State<PgLevel> initialState;

    PgSearchable(PgLevel level) {
        this.initialState = new State<>(level);
    }

    @Override
    public State<PgLevel> getInitialState() {
        return initialState;
    }

    @Override
    public ArrayList<State<PgLevel>> getPossibleStates(State<PgLevel> state) {
        ArrayList<State<PgLevel>> possibleStates= new ArrayList<>();

        PgLevel level = state.getState();

        for(int i = 0; i < level.getNumOfRows(); i++) {
            for (int j = 0; j < level.getNumOfCol(); j++) {
                Part p = level.getObject(i,j);
                Class<?>[] interfaces = p.getClass().getSuperclass().getInterfaces();
                for(Class<?> anInterface : interfaces) {
                    if (anInterface.toString().contains("Rotateable")) {
                        PgLevel lvlCopy = level.copy();
                        Pipe pCopy = ((Pipe)PartBuilder.build(p.toString().charAt(0)));
                        pCopy.getRotation().changeRotation(pCopy);
                        lvlCopy.setObject(i, j, pCopy);
                        possibleStates.add(new State<>(lvlCopy));
                        break;
                    }
                }
            }
        }
//        Character[][] chars = state.getState();
//        for(int i = 0; i <chars.length; i++) {
//            for(int j = 0; j <chars[i].length; j++) {
//                Character toReplaceWith = null;
//                switch (chars[i][j]) {
//                    case 'L':
//                        toReplaceWith = 'F';
//                        break;
//                    case 'F':
//                        toReplaceWith = '7';
//                        break;
//                    case '7':
//                        toReplaceWith = 'J';
//                        break;
//                    case 'J':
//                        toReplaceWith = 'L';
//                        break;
//                    case '|':
//                        toReplaceWith = '-';
//                        break;
//                    case '-':
//                        toReplaceWith = '|';
//                        break;
//                    default:
//                        break;
//                }
//
//                if (toReplaceWith != null) {
//                    Character[][] copy = cloneChars(chars);
//                    //TODO make it switch case
//                            if(     toReplaceWith == '7' && (left(i, j, chars) || down(i, j, chars)) ||
//                                    toReplaceWith == 'J' && (up(i, j, chars) || left(i, j, chars)) ||
//                                    toReplaceWith == 'F' && (right(i, j, chars) || down(i, j, chars)) ||
//                                    toReplaceWith == 'L' && (right(i, j, chars) || up(i, j, chars)) ||
//                                    toReplaceWith == '-' && (right(i, j, chars) || left(i, j, chars)) ||
//                                    toReplaceWith == '|' && (up(i, j, chars) || down(i, j, chars)) || right(i, j, chars) || left(i, j, chars)) {
//                                copy[i][j] = toReplaceWith;
//
//                                possibleStates.add(new State<>(copy));
//                            }
//                    }
//                }
//            }
        return possibleStates;
    }

    public boolean isGoalState(State<PgLevel> state) {
        PgLevel level = state.getState();
        Point start = level.getStart();
        // Find start point
        return findGoal(start.x, start.y, level, CameFrom.start);

       // System.out.println("Cannot find start point.");
       // return false;
    }

    private enum CameFrom {
        up,right,down,left,start
    }

    private static boolean findGoal(int i, int j, PgLevel level, CameFrom cameFrom) {
        switch (level.getObject(i,j).charface()) {
            case 'g':
                return true;
            case 's':
                switch (cameFrom) {
                    case up:
                        if (right(i, j, level)) return findGoal(i, j + 1, level, CameFrom.left);
                        if (down(i, j, level)) return findGoal(i + 1, j, level, CameFrom.up);
                        if (left(i, j, level)) return findGoal(i, j - 1, level, CameFrom.right);
                        break;

                    case right:
                        if (up(i, j, level)) return findGoal(i - 1, j, level, CameFrom.down);
                        if (down(i, j, level)) return findGoal(i + 1, j, level, CameFrom.up);
                        if (left(i, j, level)) return findGoal(i, j - 1, level, CameFrom.right);
                        break;

                    case down:
                        if (up(i, j, level)) return findGoal(i - 1, j, level, CameFrom.down);
                        if (right(i, j, level)) return findGoal(i, j + 1, level, CameFrom.left);
                        if (left(i, j, level)) return findGoal(i, j - 1, level, CameFrom.right);
                        break;

                    case left:
                        if (up(i, j, level)) return findGoal(i - 1, j, level, CameFrom.down);
                        if (right(i, j, level)) return findGoal(i, j + 1, level, CameFrom.left);
                        if (down(i, j, level)) return findGoal(i + 1, j, level, CameFrom.up);
                        break;

                    case start:
                        if (up(i, j, level)) return findGoal(i - 1, j, level, CameFrom.down);
                        if (right(i, j, level)) return findGoal(i, j + 1, level, CameFrom.left);
                        if (down(i, j, level)) return findGoal(i + 1, j, level, CameFrom.up);
                        if (left(i, j, level)) return findGoal(i, j - 1, level, CameFrom.right);
                        break;
                }
                break;
            case 'F':
                if (cameFrom == CameFrom.down)
                    if (right(i, j, level)) return findGoal(i, j + 1, level, CameFrom.left);
                    else return findGoal(i,j,level,CameFrom.up);
                if(cameFrom == CameFrom.right)
                    if (down(i, j, level)) return findGoal(i + 1, j, level, CameFrom.up);
                    else return findGoal(i,j,level,CameFrom.left);
                break;
            case 'J':
                if(cameFrom == CameFrom.left)
                    if (up(i, j, level)) return findGoal(i - 1, j, level, CameFrom.down);
                    else return findGoal(i,j,level,CameFrom.right);
                if(cameFrom == CameFrom.up)
                    if (left(i, j, level)) return findGoal(i, j - 1, level, CameFrom.right);
                    else return findGoal(i,j,level,CameFrom.down);
                break;
            case '7':
                if(cameFrom == CameFrom.left)
                    if (down(i, j, level)) return findGoal(i + 1, j, level,CameFrom.up);
                    else return findGoal(i,j,level,CameFrom.right);
                if(cameFrom == CameFrom.down)
                    if (left(i, j, level)) return findGoal(i, j - 1, level,CameFrom.right);
                    else return findGoal(i,j,level,CameFrom.up);
                break;
            case 'L':
                if(cameFrom == CameFrom.right)
                    if (up(i, j, level)) return findGoal(i - 1, j, level,CameFrom.down);
                    else return findGoal(i,j,level,CameFrom.left);
                if(cameFrom == CameFrom.up)
                    if (right(i, j, level)) return findGoal(i, j + 1, level,CameFrom.left);
                    else return findGoal(i,j,level,CameFrom.down);
                break;
            case '-':
                if(cameFrom == CameFrom.right)
                    if (left(i, j, level)) return findGoal(i, j - 1, level,CameFrom.right);
                    else return findGoal(i,j,level,CameFrom.left);
                if(cameFrom == CameFrom.left)
                    if (right(i, j, level)) return findGoal(i, j + 1, level, CameFrom.left);
                    else return findGoal(i,j,level,CameFrom.right);
                break;
            case '|':
                if(cameFrom == CameFrom.down)
                    if (up(i, j, level)) return findGoal(i - 1, j, level, CameFrom.down);
                    else return findGoal(i,j,level,CameFrom.up);
                if(cameFrom == CameFrom.up)
                    if (down(i, j, level)) return findGoal(i + 1, j, level, CameFrom.up);
                    else return findGoal(i,j,level,CameFrom.down);
                break;

        }
        return false;
    }

    private static boolean up(int i, int j, PgLevel level) {
        try {
            switch (level.getObject(i-1,j).charface()) {
                case '7':
                case '|':
                case 'F':
                case 'g':
                case 's':
                    return true;
            }
        } catch (IndexOutOfBoundsException error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean right(int i, int j, PgLevel level) {
        try {
            switch (level.getObject(i,j+1).toString().charAt(0)) {
                case '7':
                case 'J':
                case '-':
                case 'g':
                case 's':
                    return true;
            }
        } catch (IndexOutOfBoundsException error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean left(int i, int j, PgLevel level) {
        try {
            switch (level.getObject(i,j-1).charface()) {
                case 'F':
                case 'L':
                case '-':
                case 'g':
                case 's':
                    return true;
            }
        } catch (IndexOutOfBoundsException error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean down(int i, int j, PgLevel level) {
        try {
            switch (level.getObject(i+1,j).charface()) {
                case 'J':
                case 'L':
                case '|':
                case 'g':
                case 's':
                    return true;
            }
        } catch (IndexOutOfBoundsException error) {
            //System.out.println(error);
        }
        return false;
    }


    public static void main(String[] args) {
        PgLevel level = PgLevel.LevelBuilder.build("s-7\n  g");

        PgSearchable searchable = new PgSearchable(level);

        ArrayList<State<PgLevel>> list = searchable.getPossibleStates(new State<>(level));

        for(State<PgLevel> item: list) {
            System.out.println(item);
            System.out.println();
        }

    }
}