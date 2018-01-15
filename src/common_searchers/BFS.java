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
       Solution<T> sol = new Solution<>();
       State<T> initialState = searchable.getInitialState();
       queue.add(initialState );
       while(!queue.isEmpty()){
           outerloop++;
           State<T> item = queue.remove();
           if(searchable.isGoalState(item)) {
               System.out.println("!!BFS GOAL!! (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") ");
               sol.add(item.getState());
               return sol;
           }
           visited.add(item);
           ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(item);
           for(State<T> t: nextPossibleStates){
               innerloop++;
               if(!visited.contains(t)) {
                   t.setCameFrom(item);
                   if(searchable.isGoalState(t)) {
                       System.out.println("!!BFS GOAL!! (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") ");
                       sol.add(t.getState());
                       return sol;
                   }
                   queue.add(t);
                   visited.add(t);
               }
           }
       }
       System.out.println("!!!! GOAL !NOT! FOUND AFTER (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") Iterations");
       return null;
   }
}