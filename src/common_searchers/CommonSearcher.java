package common_searchers;

import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;

import java.util.Collections;

public class CommonSearcher<T>   {
    protected Solution<T> backtrace(State<T> s) {
        System.out.println("Building backtrace..");
        Solution<T> sol = new Solution<>();
        sol.add(s.getState());

        while (s.hasCameFrom()) {
            sol.add(s.getCameFrom().getState());
            s = s.getCameFrom();
        }
        Collections.reverse(sol);

        return sol;
    }
}
