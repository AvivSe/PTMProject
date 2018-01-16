package common_searchers;

import searcher_interface.Searchable;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;
import java.util.*;

public class BFS<T> implements Searcher<T> {
   private Queue<State<T>> queue =  new LinkedList<>();
   private ArrayList<State<T>> visited = new ArrayList<>();
   @Override
   public Solution<T> search(Searchable<T> searchable) {
       int outerloop = 0;
       int innerloop  = 0;
       State<T> initialState = searchable.getInitialState();
       queue.add(initialState );
       while(!queue.isEmpty()){
           outerloop++;
           State<T> item = queue.remove();
           if(searchable.isGoalState(item)) {
               System.out.println("(outerloop)!!BFS GOAL!! (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") ");
               return backtrace(item);
           }
           visited.add(item);
           ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(item);
           for(State<T> t: nextPossibleStates){
               innerloop++;
               if(!visited.contains(t)) {
                   t.setCameFrom(item);
                   if(searchable.isGoalState(t)) {
                       System.out.println("(innerloop)!!BFS GOAL!! (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") ");
                       return backtrace(t);
                   }
                   queue.add(t);
                   visited.add(t);
               }
           }
       }
       System.out.println("!!!! GOAL !NOT! FOUND AFTER (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") Iterations");
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