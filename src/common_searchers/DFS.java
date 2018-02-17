package common_searchers;

import searcher_interface.Searchable;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.Stack;
public class DFS<T> extends CommonSearcher implements Searcher<T> {

    public DFS() {
        System.out.println("DFS is initialized..");
    }


    @Override
    public Solution search(Searchable<T> searchable) {
        long startTime = System.nanoTime();
        State<T> initialState = searchable.getInitialState();

        ArrayList<State<T>> visited = new ArrayList<>();
        Stack<State<T>> stack = new Stack<>();

        stack.push(initialState);
        visited.add(initialState);

        while(!stack.isEmpty()) {
            State<T> state = stack.pop();
            if(searchable.isGoalState(state)) {
                return backtrace(state);
            }

            for (State<T> neighbor : searchable.getPossibleStates(state)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }

        }

        return null;
    }
}
