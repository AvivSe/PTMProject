package server;

import search.Searchable;
import search.State;
import java.util.ArrayList;

public class MySearchable implements Searchable<MyLevel> {

    private State<char[][]> initialState;

    MySearchable(MyLevel level) {

        /****
         *
         *
         * First place we doing adaptation.
         */
        char[][] chars = new char[level.getNumOfRows()][level.getNumOfCol()];
        for(int i = 0; i < level.getNumOfRows(); i++) {
            for (int j= 0; j < level.getNumOfCol(); j++) {
                chars[i][j] = level.getObject(i,j).toString().charAt(0);
            }
        }

        this.initialState = new State<>(chars);
    }

    @Override
    public State getInitialState() {
        return initialState;
    }

    @Override
    public ArrayList<State<MyLevel>> getPossibleStates(State state) {
        ArrayList<State<MyLevel>> possibleStates= new ArrayList<>();

        return possibleStates;
    }

    @Override
    public boolean isGoalState(State<MyLevel> specificState) {
        for(int i = 0; i <specificState.getState().getNumOfRows(); i++) {
            for(int j = 0; j <specificState.getState().getNumOfCol(); j++) {
                if(specificState.getState().getObject(i,j).toString() == "|") {
                    return false;
                }
            }
        }
        return true;
    }
}