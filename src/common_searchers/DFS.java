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
        System.out.println("DFS took it on him..");
        long startTime = System.nanoTime();
        State<T> initialState = searchable.getInitialState();

        // CHECK IF INITIAL STATE IS THE GOAL //
        if(searchable.isGoalState(initialState)) {
            return backtrace(initialState);
        }

        ArrayList<State<T>> visited = new ArrayList<>();
        Stack<State<T>> stack = new Stack<>();

        stack.push(initialState);
        visited.add(initialState);

        while(!stack.isEmpty()) {
            State<T> node = (State<T>) stack.peek();
            if(searchable.isGoalState(node)) {
                return backtrace(node);
            }

            ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(node);

                for (State<T> child : searchable.getPossibleStates(node)) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        stack.push(child);
                    }
                }

        }

        return null;
    }
}
