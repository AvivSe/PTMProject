package common_searchers;

import searcher_interface.Searchable;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;
import java.util.*;

public class BFS<T> extends CommonSearcher implements Searcher<T> {
    public BFS() {
        System.out.println("BFS is initialized..");
    }

    @Override
    public Solution search(Searchable<T> searchable) {
        long startTime = System.nanoTime();
        State<T> initialState = searchable.getInitialState();

        Queue<State<T>> queue =  new LinkedList<>();
        ArrayList<State<T>> visited = new ArrayList<>();

        queue.add(initialState);

        while(!queue.isEmpty()){
            State<T> current = queue.remove();
            if (!visited.contains(current)) {
                if (searchable.isGoalState(current)) {
                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime);
                    Long ms = duration / 1000000;
                    Double sec = (double) duration / 1000000000.0;
                    System.out.println("BFS GOAL: " + ms + "ms " +sec+"sec");
                    return backtrace(current);
                } else {
                    ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(current);
                        queue.addAll(nextPossibleStates);
                    visited.add(current);
                }
            }
        }
        System.out.println("BFS: Can't find path.");
        return null;
    }


}