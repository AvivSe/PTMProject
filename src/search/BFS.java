package search;

import java.util.ArrayList;
import java.util.Queue;

public class BFS implements Searcher<char[][]> {

    private Queue<State> queue;
    private ArrayList<State> visited;
    static ArrayList<State> nodes = new ArrayList<>();

    @Override
    public Solution search(Searchable<char[][]> searchable) {
        State initialState = searchable.getInitialState();
        queue.add(initialState );
        visited.add(initialState );

        while(!queue.isEmpty()){
            State item=queue.remove();
            if(searchable.isGoalState(item)) {
                return new Solution();
            }
            System.out.println("Hi I am: \n" + item.getState().toString() + "\n and my childs are: \n");
            ArrayList<State> dontBeChilds = searchable.getPossibleStates(item);
            for(State t:dontBeChilds){
                if(t != null && !visited.contains(t)) {
                    System.out.println(t);
                    queue.add(t);
                    visited.add(t);
                }
            }
        }
        return null;
    }
}