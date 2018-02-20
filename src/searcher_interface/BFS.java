package searcher_interface;

import java.util.*;

public class BFS<T> extends CommonSearcher implements Searcher<T> {
    private Queue<State<T>> queue;

    public BFS() {
        super();
        queue =  new LinkedList<>();
    }

    @Override
    public Solution search(Searchable<T> searchable) {
        State<T> initialState = searchable.getInitialState();

        queue.add(initialState);

        while(!queue.isEmpty()){
            State<T> current = queue.remove();
           if (!closeList.contains(current)) {
                if (searchable.isGoalState(current)) {
                    System.out.println("BFS: GOAL!");
                    return BackTrace(current);
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