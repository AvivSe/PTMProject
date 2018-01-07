package search;

import java.util.List;


public interface Searchable<T> {
    State<T> getInitialState();
    List<State<T>> getPossibleStates(State<T> state);
    boolean isGoal(State<T> state);
}
