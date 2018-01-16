package common_searchers;

import searcher_interface.Searchable;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;
import java.util.*;

public class BFS<T> implements Searcher<T> {
    State<T> initialState;
   @Override
   public Solution<T> search(Searchable<T> searchable) {
       //TODO: REMOVE NEXT LINE AND BUILD A CONSTRUCTOR
       initialState = searchable.getInitialState();

       // CHECK IF INITIAL STATE IS THE GOAL //
       if(searchable.isGoalState(initialState)) {
           return backtrace(initialState);
       }

       Queue<State<T>> queue =  new LinkedList<>();
       ArrayList<State<T>> visited = new ArrayList<>();
       queue.add(initialState );
       visited.add(initialState);

       while(!queue.isEmpty()){
           State<T> current = queue.remove();
               if (searchable.isGoalState(current)) {
                   return backtrace(current);
               } else {
                   ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(current);
                   if (nextPossibleStates.isEmpty()) {
                       return null;
                   } else {
                       queue.addAll(nextPossibleStates);
                   }
                   visited.add(current);
               }
       }
       return null;
   }

   private Solution<T> backtrace(State<T> s) {
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