package search;

import search.Searchable;
import search.Searcher;
import search.Solution;
import search.State;

public class simpleSearcher implements Searcher<char[][]> {
    @Override
    public Solution search(Searchable<char[][]> searchable) {
        Solution sol = new Solution();
        State<char[][]> thisIsMyState = searchable.getInitialState();
        char[][] hereIcanTreval = thisIsMyState.getState();
        for(int i = 0; i < hereIcanTreval.length; i++) {
            for(int j = 0; j < hereIcanTreval[i].length; j++) {
                if (hereIcanTreval[i][j] == '|') {
                    hereIcanTreval[i][j] = '-';
                    sol.add("Step Number: " + sol.size()+ " please go here (row,col): " + i + ","+j);
                }
                if (searchable.isGoalState(thisIsMyState)) {

                    return sol;
                }
            }
        }
        sol.add("No Sol");
        return sol;
    }
}
