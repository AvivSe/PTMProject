package search;

import java.util.*;

public class BreadthFirstSearch implements Searcher<char[][]> {
   private Queue<State<char[][]>> queue =  new LinkedList<>();;
   private ArrayList<State<char[][]>> visited = new ArrayList<>();
   @Override
   public Solution search(Searchable<char[][]> searchable) {
       int outerloop = 0;
       int innerloop  = 0;
       State<char[][]> initialState = searchable.getInitialState();
       queue.add(initialState );


       while(!queue.isEmpty()){
           outerloop++;
           State<char[][]> item = queue.remove();
           System.out.println("Visiting at:");
           //  ****** test *****//
           StringBuilder initCompareable = new StringBuilder();
           for(char[] row: item.getState()) {
               for (char ch: row) {
                   initCompareable.append(ch);
               }
               initCompareable.append("\n");
           }
           System.out.println(initCompareable.toString());

           if(searchable.isGoalState(item)) {
               System.out.println("!!BFS GOAL!! (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") ");
               return Solution.SolutionBuilder.build(item.getState());
           }

           visited.add(item);
           System.out.println("Possible states from here are:");
           //****** end test *****/
           ArrayList<State<char[][]>> nextPossibleStates = searchable.getPossibleStates(item);

           for(State<char[][]> t: nextPossibleStates){
               innerloop++;
               StringBuilder comparable = new StringBuilder();
               for(char[] row: t.getState()) {
                   for (char ch : row) {
                       comparable.append(ch);
                   }
                   comparable.append("\n");
               }
               if(!visited.contains(t)) {
                   t.setCameFrom(item);
                   //****** test *****//
                   System.out.print(comparable);
                   if(searchable.isGoalState(t)) {
                       System.out.println("!!BFS GOAL!! (outerloop,innerloop,visited) = ("+outerloop+","+innerloop+"," +visited.size()+") ");
                       return Solution.SolutionBuilder.build(t.getState());
                   }
                   System.out.println();
                   //****** end test *****/
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