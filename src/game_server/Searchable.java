package game_server;

import java.util.ArrayList;

public interface Searchable<T> {
    State<T> getInitialState();
    ArrayList<State<T>> getPossibleStates(State<T> state);
    boolean isGoalState(State<T> state);
}
