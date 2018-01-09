package search;

import search.Searchable;
import search.Searcher;
import search.Solution;
import search.State;

import java.util.ArrayList;

public class simpleSearcher implements Searcher<char[][]> {
    @Override
    public Solution search(Searchable<char[][]> searchable) {
        Solution sol = new Solution();
        char[][] chars = searchable.getInitialState().getState();

        if(searchable.isGoalState(searchable.getInitialState())) {
            sol.add("You are right. no more steps needed.");
            return sol;
        }
        ArrayList<State<char[][]>> nextPossibleStates =  searchable.getPossibleStates(searchable.getInitialState());
        for(int i = 0; i < nextPossibleStates.size(); i++) {
            System.out.println(i);
            if(searchable.isGoalState(nextPossibleStates.get(i))) {
                sol.add("I Found sol");
                return sol;
            }
        }
        sol.add("No Sol");
        return sol;
    }
}
