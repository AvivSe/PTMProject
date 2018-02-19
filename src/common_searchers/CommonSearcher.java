package common_searchers;

import pipe_game_server.PgLevel;
import searcher_interface.Solution;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.Collections;

class CommonSearcher<T>   {
    ArrayList<State<T>> closeList;

    CommonSearcher() {
        closeList = new ArrayList<>();
    }

    int recursiveBackTrace(Solution<T> solution, State<T> s) {
        if (!s.hasCameFrom())
            return 0;

        solution.add(s.getState());

        return 1+(recursiveBackTrace(solution,s.getCameFrom()));

    }

    // TODO: MAKE IT GENERIC AGAIN!
    Solution<T> backtrace(State<T> s) {
        Solution<T> solution = new Solution<>();
        recursiveBackTrace(solution,s);
        Collections.reverse(solution);
        return solution;
//        State<PgLevel> s1 = (State<PgLevel>)s;
//        System.out.println("Building backtrace..");
//        Solution<PgLevel> sol = new Solution<>();
//        sol.add(s1.getState());
//        System.out.println("*****");
//
//        while (s1.hasCameFrom()) {
//            sol.add(s1.getCameFrom().getState());
//            System.out.println(s.getState());
//            System.out.println("*("+s1.getState().getPosition().x +","+s1.getState().getPosition().y+")*");
//            System.out.println("*****");
//            s1 = s1.getCameFrom();
//        }
//
//        Collections.reverse(sol);
//
//        return sol;
    }
}
