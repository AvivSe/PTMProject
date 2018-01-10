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
        return possibleStates;
    }

    public boolean isGoalState(State<char[][]> state) {
        char[][] chars = cloneChars(state.getState());
        for(int i=0;i<chars.length;i++)
            for(int j=0;j<chars[i].length;j++)
                if(chars[i][j] == 's')
                    return findGoal(i,j,chars);
        return false;
    }

    public boolean findGoal(int i,int j,char[][] chars) {
        if(chars[i][j] != 0) {
            char currentPart = chars[i][j];
            switch (currentPart) {
                case 's':
                    chars[i][j] = 0; //SET PART TO NULL
                    if(chars[i + 1][j] == '7'||chars[i + 1][j] == 'J'||chars[i + 1][j] == '-'||chars[i + 1][j] == 'g'){
                        return findGoal(i+1,j,chars);
                    }
                    if(chars[i - 1][j] == 'L'||chars[i - 1][j] == 'F'||chars[i - 1][j] == '-'||chars[i - 1][j] == 'g'){
                        return findGoal(i-1,j,chars);
                    }
                    if(chars[i][j + 1] == 'L'||chars[i][j + 1] == 'J'||chars[i][j + 1] == '|'||chars[i][j + 1] == 'g'){
                        return findGoal(i,j+1,chars);
                    }
                    if(chars[i][j - 1] == '7'||chars[i][j - 1] == 'F'||chars[i][j - 1] == '|'||chars[i][j - 1] == 'g'){
                        return findGoal(i,j-1,chars);
                    }
                    else
                        return false;
                case 'L':
                    chars[i][j] = 0; //SET PART TO NULL
                    if(chars[i + 1][j] == '7'||chars[i + 1][j] =='J'||chars[i + 1][j]=='-'||chars[i + 1][j] =='g'){
                        return findGoal(i+1,j,chars);
                    }
                    if(chars[i][j - 1] =='7'||chars[i][j - 1]=='|'||chars[i][j - 1]=='F'||chars[i][j - 1]=='g'){
                        return findGoal(i,j-1,chars);
                    }
                case 'F':
                    chars[i][j] = 0; //SET PART TO NULL
                    if(chars[i+1][j]=='7'||chars[i+1][j]=='J'||chars[i+1][j]=='-'||chars[i+1][j]=='g'){
                        return findGoal(i+1,j,chars);
                    }
                    if(chars[i][j+1]=='L'||chars[i][j+1]=='J'||chars[i][j+1]=='|'||chars[i][j+1]=='g'){
                        return findGoal(i,j+1,chars);
                    }
                    else
                        return false;
                case 'J':
                    chars[i][j] = 0; //SET PART TO NULL
                    if(chars[i][j-1]=='7'||chars[i][j-1]=='F'||chars[i][j-1]=='|'||chars[i][j-1]=='g'){
                        return findGoal(i,j-1,chars);
                    }
                    if(chars[i-1][j]=='L'||chars[i-1][j]=='F'||chars[i-1][j]=='-'||chars[i-1][j]=='g'){
                        return findGoal(i-1,j,chars);
                    }
                    else
                        return false;
                case '7':
                    if(chars[i-1][j]=='L'||chars[i-1][j]=='F'||chars[i-1][j]=='-'||chars[i-1][j]=='g'){
                        return findGoal(i-1,j,chars);
                    }
                    if(chars[i][j+1]=='L'||chars[i][j+1]=='J'||chars[i][j+1]=='|'||chars[i][j+1]=='g'){
                        return findGoal(1,j+1,chars);
                    }
                case '-':
                    if(chars[i-1][j]=='L'||chars[i-1][j]=='F'||chars[i-1][j]=='-'||chars[i-1][j]=='g'){
                        return findGoal(i-1,j,chars);
                    }
                    if(chars[i+1][j]== '7'||chars[i+1][j]=='J'||chars[i+1][j]=='-'||chars[i+1][j]=='g'){
                        return findGoal(i+1,j,chars);
                    }
                    else
                        return false;
                case'|':
                    if(chars[i][j+1]=='L'||chars[i][j+1]=='J'||chars[i][j+1]=='|'||chars[i][j+1]=='g'){
                        return findGoal(i,j+1,chars);
                    }
                    if(chars[i][j-1]=='7'||chars[i][j-1]=='F'||chars[i][j-1]=='|'||chars[i][j-1]=='g'){
                        return findGoal(i,j-1,chars);
                    }
                    else
                        return false;
                case 'g':
                    return true; // GOAL STATE
            }
        }
        return false;
    }


    public static char[][] cloneChars(char[][] src) {
        char[][] chars = new char[src.length][src[0].length];

        for(int i = 0; i <chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                chars[i][j] = src[i][j];
            }
        }


        return chars;
    }

}