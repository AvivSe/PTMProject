package common_searchers;

import searcher_interface.Searchable;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;
import java.util.*;

public class BFS<T> extends CommonSearcher implements Searcher<T> {
    private Queue<State<T>> queue;

    public BFS() {
        super();
        queue =  new LinkedList<>();
        System.out.println("BFS is initialized..");
    }

    @Override
    public Solution search(Searchable<T> searchable) {
        long startTime = System.nanoTime();
        State<T> initialState = searchable.getInitialState();

        queue.add(initialState);

        while(!queue.isEmpty()){
            State<T> current = queue.remove();
            if (!closeList.contains(current)) {
                if (searchable.isGoalState(current)) {
                    System.out.println("BFS: GOAL!");
                    System.out.println("Cost: " + current.getCost());
                    return backtrace(current);
                } else {
                    ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(current);
                        queue.addAll(nextPossibleStates);
                    closeList.add(current);
                }
            }
        }

        System.out.println("BFS: Can't find path.");
        return null;
    }


}