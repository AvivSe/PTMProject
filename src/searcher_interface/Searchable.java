/**
 * Searchable Interface - is an object adapter for any kind of problem.
 * In my project I use those abstract algorithms, adjacent to their pseudo code.
 * The idea is to use those alg' as a black box, we only have to create the right adapter in order to use them.
 * Aviv Segal 12/2017
 */

package searcher_interface;
import java.util.List;

public interface Searchable<T> {
    State<T> getInitialState();
    List<State<T>> getPossibleStates(State<T> state);
    boolean isGoalState(State<T> state);
}
