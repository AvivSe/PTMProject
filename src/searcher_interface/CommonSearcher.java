package searcher_interface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CommonSearcher<T> {
    List<State<T>> closeList;

    CommonSearcher() {
        closeList = new ArrayList<>();
    }

    Solution<T> BackTrace(State<T> state) {
        Solution<T> solution = new Solution<>();
        State<T> currentState = state;

        while (currentState.hasCameFrom()) {
            solution.add(currentState.getState());
            currentState = currentState.getCameFrom();
        }

        Collections.reverse(solution);
        return solution;
    }
}
