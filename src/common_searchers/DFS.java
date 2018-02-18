package common_searchers;

import searcher_interface.Searchable;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.Stack;

public class DFS<T> extends CommonSearcher implements Searcher<T> {
    private Stack<State<T>> stack;

    public DFS() {
        stack = new Stack<>();
        System.out.println("DFS is initialized..");
    }


    @Override
    public Solution search(Searchable<T> searchable) {
        long startTime = System.nanoTime();
        State<T> initialState = searchable.getInitialState();

        stack.add(initialState);

        while(!stack.isEmpty()){
            State<T> current = stack.pop();
            if (!closeList.contains(current)) {
                if (searchable.isGoalState(current)) {
                    System.out.println("DFS: GOAL!");
                    System.out.println("Cost: " + current.getCost());
                    return backtrace(current);
                } else {
                    ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(current);
                    stack.addAll(nextPossibleStates);
                    closeList.add(current);
                }
            } 
        }

        System.out.println("DFS: Can't find path.");
        return null;
    }


/*
    @Override
    public Solution search(Searchable<T> searchable) {

        long startTime = System.nanoTime();
        State<T> initialState = searchable.getInitialState();

        stack.push(initialState);
        closeList.add(initialState);

        while(!stack.isEmpty()) {
            State<T> state = stack.pop();
            if(searchable.isGoalState(state)) {
                System.out.println("DFS: GOAL!");
                System.out.println("Cost: " + state.getCost());
                return backtrace(state);
            }

            for (State<T> child : searchable.getPossibleStates(state)) {
                if (!closeList.contains(child)) {
                    closeList.add(child);
                    stack.push(child);
                }
            }

        }

        return null;
    }*/
}
