package server;

import level.MyLevel;
import search.Searchable;
import search.State;
import java.util.ArrayList;

public class MySearchable implements Searchable<char[][]> {
    private State<MyLevel> initialState;
    MySearchable(MyLevel level) {
        this.initialState = new State<>(level);
    }


    @Override
    public State getInitialState() {
        return initialState;
    }

    @Override
    public ArrayList<State> getPossibleStates(State state) {

        return null;
    }

    @Override
    public boolean isGoalState(int i,int j,State state) {
        switch(state.getState()[i][j])

    }
}