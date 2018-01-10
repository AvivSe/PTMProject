package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class BFS implements Searcher<char[][]> {
   private Queue<State<char[][]>> queue =  new LinkedList<>();;
   private ArrayList<State<char[][]>> visited = new ArrayList<>();

   @Override
   public Solution search(Searchable<char[][]> searchable) {

       State<char[][]> initialState = searchable.getInitialState();

       queue.add(initialState );

       while(!queue.isEmpty()){
           State<char[][]> item=queue.remove();
           if(searchable.isGoalState(item)) {
               return new Solution();
           }
//           System.out.println("Hi I Am:");
//           //****** test *****//
//           StringBuilder initCompareable = new StringBuilder();
//           for(char[] row: item.getState()) {
//               for (char ch: row) {
//                   initCompareable.append(ch);
//               }
//           }
//           System.out.println(initCompareable.toString());
//           visited.add(item);
//           System.out.println("And my next possible states are:");
//           //****** end test *****/
           ArrayList<State<char[][]>> nextPossibleStates = searchable.getPossibleStates(item);

           for(State<char[][]> t: nextPossibleStates){
               StringBuilder comparable = new StringBuilder();
               for(char[] row: t.getState()) {
                   for (char ch : row) {
                       comparable.append(ch);
                   }
               }
               if(!visited.contains(t)) {
                   t.setCameFrom(item);
//                   //****** test *****//
//                   System.out.print(comparable + " came-from " + initCompareable);
//                   if(searchable.isGoalState(t)) {
//                       System.out.print(" --->> GOAL !!!!");
//                   }
//                   System.out.println();
//                   //****** end test *****/
                   queue.add(t);
                   visited.add(t);
               }
           }
           System.out.println("**********************");
           System.out.println("VISITED TILL NOW: " + visited.size() + " " + visited);
           System.out.println("**********************");

       }
       return null;
   }

}