package common_searchers;

import pipe_game_server.PgLevel;
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
    public Solution<State<T>> search(Searchable<T> searchable) {

        queue.add(searchable.getInitialState());

        while(!queue.isEmpty()) {
            State<T> state = queue.remove();
            closeList.add(state);

            if(searchable.isGoalState(state)) {
                System.out.println("BFS GOAL: ");
                System.out.println(state.getState());
                return backtrace(state);
            }

            for(State<T> neighbor: searchable.getPossibleStates(state)) {
//                System.out.println(neighbor.getState());
//                System.out.println("******");

                if(!queue.contains(neighbor) && !closeList.contains(neighbor)) {
                    queue.add(neighbor);
                }

            }
        }
        System.out.println("BFS CANNOT FIND PATH");
        return null;




//        long startTime = System.nanoTime();
//        State<T> initialState = searchable.getInitialState();
//
//        queue.add(initialState);
//
//        while(!queue.isEmpty()){
//            State<T> current = queue.remove();
//            if (!closeList.contains(current)) {
//                closeList.add(current);
//                if (searchable.isGoalState(current)) {
//                    System.out.println("BFS: GOAL!");
//                    System.out.println(current.getState());
//                    System.out.println("Cost: " + current.getCost());
//                    return backtrace(current);
//                } else {
//                    ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(current);
//                        queue.addAll(nextPossibleStates);
//                }
//            }
//        }
//
//        System.out.println("BFS: Can't find path.");
//        return null;
    }


}