package game_server;

import java.util.*;

public class BreadthFirstSearch<T> implements Searcher<T> {
   private Queue<State<T>> queue =  new LinkedList<>();
   private ArrayList<State<T>> visited = new ArrayList<>();
   @Override
   public Solution search(Searchable<T> searchable) {
       int outerloop = 0;
       int innerloop  = 0;
       State<T> initialState = searchable.getInitialState();
       queue.add(initialState );


       while(!queue.isEmpty()){
           outerloop++;
           State<T> item = queue.remove();
//           System.out.println("Visiting at:");
           if(searchable.isGoalState(item)) {
               System.out.println("!!BFS GOAL!! (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") ");
               return Solution.SolutionBuilder.build(item);
           }
           visited.add(item);
           ArrayList<State<T>> nextPossibleStates = searchable.getPossibleStates(item);

           for(State<T> t: nextPossibleStates){
               innerloop++;
               if(!visited.contains(t)) {
                   t.setCameFrom(item);
                   if(searchable.isGoalState(t)) {
                       System.out.println("!!BFS GOAL!! (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") ");
                       return Solution.SolutionBuilder.build(t);
                   }
                   System.out.println();
                   queue.add(t);
                   visited.add(t);
               }
           }
           System.out.println("**********************");
           System.out.println("VISITED TILL NOW: " + visited.size());
           System.out.println("**********************");
       }
       System.out.println("!!!! GOAL !NOT! FOUND AFTER (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") Iterations");
       return null;
   }
}