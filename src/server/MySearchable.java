package server;

import search.Searchable;
import search.State;
import java.util.ArrayList;
import java.util.Arrays;

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
                    copy[i][j] = toReplaceWith;

                    possibleStates.add(new State<>(copy));

                }

            }
        }
/*        *//****** test *****//*
        for(State<char[][]> s: possibleStates) {
            for (char[] row: s.getState()) {
                for(char item: row) {
                    System.out.print(item);
                }
            }
            System.out.println();
        }
        *//****** end test *****/
        return possibleStates;
    }

    @Override
    public boolean isGoalState(State<char[][]> specificState) {
        char[][] chars = specificState.getState();

        for(int i = 0; i <chars.length; i++) {
            for(int j = 0; j <chars[i].length; j++) {
                if(chars[i][j] == '|') {
                    return false;
                }
            }
        }
        return true;
    }

    public static char[][] cloneChars(char[][] src) {
        char[][] result = new char[src.length][src[0].length];
        for (char[] item: src) {
            result[0] = Arrays.copyOf(item, item.length);
        }

        return result;
    }


}