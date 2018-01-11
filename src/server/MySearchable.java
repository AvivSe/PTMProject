package server;

import search.Searchable;
import search.State;
import java.util.ArrayList;

public class MySearchable implements Searchable<char[][]> {

    private State<char[][]> initialState;

    MySearchable(MyLevel level) {
        char[][] chars = new char[level.getNumOfRows()][level.getNumOfCol()];
        for(int i = 0; i < level.getNumOfRows(); i++) {
            for (int j= 0; j < level.getNumOfCol(); j++) {
                chars[i][j] = level.getObject(i,j).toString().charAt(0);
            }
        }
        this.initialState = new State<>(chars);
    }

    @Override
    public State<char[][]>getInitialState() {
        return initialState;
    }

    @Override
    public ArrayList<State<char[][]>> getPossibleStates(State<char[][]> state) {
        ArrayList<State<char[][]>> possibleStates= new ArrayList<>();
        char[][] chars = state.getState();

        for(int i = 0; i <chars.length; i++) {
            for(int j = 0; j <chars[i].length; j++) {
                Character toReplaceWith = null;
                switch (chars[i][j]) {
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
                    case '|':
                        toReplaceWith = '-';
                        break;
                    case '-':
                        toReplaceWith = '|';
                        break;
                    default:
                        break;
                }

                if (toReplaceWith != null) {
                    char[][] copy = cloneChars(chars);
                    //TODO make it switch case
                            if(     toReplaceWith == '7' && (left(i, j, chars) || down(i, j, chars)) ||
                                    toReplaceWith == 'J' && (up(i, j, chars) || left(i, j, chars)) ||
                                    toReplaceWith == 'F' && (right(i, j, chars) || down(i, j, chars)) ||
                                    toReplaceWith == 'L' && (right(i, j, chars) || up(i, j, chars)) ||
                                    toReplaceWith == '-' && (right(i, j, chars) || left(i, j, chars)) ||
                                    toReplaceWith == '|' && (up(i, j, chars) || down(i, j, chars)) || right(i, j, chars) || left(i, j, chars)) {
                                copy[i][j] = toReplaceWith;

                                possibleStates.add(new State<>(copy));
                            }
                    }
                }
            }
        return possibleStates;
    }

    public boolean isGoalState(State<char[][]> state) {
        char[][] chars = state.getState();
        // Find start point
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if (chars[i][j] == 's') {
                    return findGoal(i, j, chars, CameFrom.start);
                }
            }
        }
        System.out.println("Cannot find start point.");
        return false;
    }

    private enum CameFrom {
        up,right,down,left,start
    }

    private static boolean findGoal(int i, int j, char[][] chars, CameFrom cameFrom) {
        switch (chars[i][j]) {
            case 'g':
                return true;
            case 's':
                switch (cameFrom) {
                    case up:
                        if (right(i, j, chars)) return findGoal(i, j + 1, chars, CameFrom.left);
                        if (down(i, j, chars)) return findGoal(i + 1, j, chars, CameFrom.up);
                        if (left(i, j, chars)) return findGoal(i, j - 1, chars, CameFrom.right);
                        break;

                    case right:
                        if (up(i, j, chars)) return findGoal(i - 1, j, chars, CameFrom.down);
                        if (down(i, j, chars)) return findGoal(i + 1, j, chars, CameFrom.up);
                        if (left(i, j, chars)) return findGoal(i, j - 1, chars, CameFrom.right);
                        break;

                    case down:
                        if (up(i, j, chars)) return findGoal(i - 1, j, chars, CameFrom.down);
                        if (right(i, j, chars)) return findGoal(i, j + 1, chars, CameFrom.left);
                        if (left(i, j, chars)) return findGoal(i, j - 1, chars, CameFrom.right);
                        break;

                    case left:
                        if (up(i, j, chars)) return findGoal(i - 1, j, chars, CameFrom.down);
                        if (right(i, j, chars)) return findGoal(i, j + 1, chars, CameFrom.left);
                        if (down(i, j, chars)) return findGoal(i + 1, j, chars, CameFrom.up);
                        break;

                    case start:
                        if (up(i, j, chars)) return findGoal(i - 1, j, chars, CameFrom.down);
                        if (right(i, j, chars)) return findGoal(i, j + 1, chars, CameFrom.left);
                        if (down(i, j, chars)) return findGoal(i + 1, j, chars, CameFrom.up);
                        if (left(i, j, chars)) return findGoal(i, j - 1, chars, CameFrom.right);
                        break;
                }
                break;
            case 'F':
                if (cameFrom == CameFrom.down)
                    if (right(i, j, chars)) return findGoal(i, j + 1, chars, CameFrom.left);
                    else return findGoal(i,j,chars,CameFrom.up);
                if(cameFrom == CameFrom.right)
                    if (down(i, j, chars)) return findGoal(i + 1, j, chars, CameFrom.up);
                    else return findGoal(i,j,chars,CameFrom.left);
                break;
            case 'J':
                if(cameFrom == CameFrom.left)
                    if (up(i, j, chars)) return findGoal(i - 1, j, chars, CameFrom.down);
                    else return findGoal(i,j,chars,CameFrom.right);
                if(cameFrom == CameFrom.up)
                    if (left(i, j, chars)) return findGoal(i, j - 1, chars, CameFrom.right);
                    else return findGoal(i,j,chars,CameFrom.down);
                break;
            case '7':
                if(cameFrom == CameFrom.left)
                    if (down(i, j, chars)) return findGoal(i + 1, j, chars,CameFrom.up);
                    else return findGoal(i,j,chars,CameFrom.right);
                if(cameFrom == CameFrom.down)
                    if (left(i, j, chars)) return findGoal(i, j - 1, chars,CameFrom.right);
                    else return findGoal(i,j,chars,CameFrom.up);
                break;
            case 'L':
                if(cameFrom == CameFrom.right)
                    if (up(i, j, chars)) return findGoal(i - 1, j, chars,CameFrom.down);
                    else return findGoal(i,j,chars,CameFrom.left);
                if(cameFrom == CameFrom.up)
                    if (right(i, j, chars)) return findGoal(i, j + 1, chars,CameFrom.left);
                    else return findGoal(i,j,chars,CameFrom.down);
                break;
            case '-':
                if(cameFrom == CameFrom.right)
                    if (left(i, j, chars)) return findGoal(i, j - 1, chars,CameFrom.right);
                    else return findGoal(i,j,chars,CameFrom.left);
                if(cameFrom == CameFrom.left)
                    if (right(i, j, chars)) return findGoal(i, j + 1, chars, CameFrom.left);
                    else return findGoal(i,j,chars,CameFrom.right);
                break;
            case '|':
                if(cameFrom == CameFrom.down)
                    if (up(i, j, chars)) return findGoal(i - 1, j, chars, CameFrom.down);
                    else return findGoal(i,j,chars,CameFrom.up);
                if(cameFrom == CameFrom.up)
                    if (down(i, j, chars)) return findGoal(i + 1, j, chars, CameFrom.up);
                    else return findGoal(i,j,chars,CameFrom.down);
                break;

        }
        return false;
    }

    private static boolean up(int i, int j, char[][] chars) {
        try {
            switch (chars[i - 1][j]) {
                case '7':
                case '|':
                case 'F':
                case 'g':
                case 's':
                    return true;
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean right(int i, int j, char[][] chars) {
        try {
            switch (chars[i][j + 1]) {
                case '7':
                case 'J':
                case '-':
                case 'g':
                case 's':
                    return true;
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean left(int i, int j, char[][] chars) {
        try {
            switch (chars[i][j - 1]) {
                case 'F':
                case 'L':
                case '-':
                case 'g':
                case 's':
                    return true;
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            //System.out.println(error.getMessage());
        }
        return false;
    }

    private static boolean down(int i, int j, char[][] chars) {
        try {
            switch (chars[i + 1][j]) {
                case 'J':
                case 'L':
                case '|':
                case 'g':
                case 's':
                    return true;
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            //System.out.println(error);
        }
        return false;
    }

    private static char[][] cloneChars(char[][] src) {
        char[][] chars = new char[src.length][src[0].length];

        for(int i = 0; i <chars.length; i++) {
            System.arraycopy(src[i], 0, chars[i], 0, chars[i].length);
        }
        return chars;
    }
}