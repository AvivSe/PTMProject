package common_searchers;

import searcher_interface.Solution;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

class CommonSearcher<T>   {
    ArrayList<State<T>> closeList;

    CommonSearcher() {
        closeList = new ArrayList<>();
    }



    // TODO: MAKE IT GENERIC AGAIN!
    Solution<T> backtrace(State<T> goalState) {

        Solution<T> sol = new Solution<>();

        while (goalState.hasCameFrom()) {
            sol.add(goalState.getState());

            goalState = goalState.getCameFrom();
        }

        sol.add(goalState.getState());
        System.out.println("BackTRACE:");
        for(T s: sol) {
            System.out.println(s);
            System.out.println("******");
        }
        return sol;
    }
}
