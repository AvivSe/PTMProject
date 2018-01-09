package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



public class BFS implements Searcher<char[][]> {
   private Queue<State<char[][]>> queue =  new LinkedList<>();;
   private ArrayList<State<char[][]>> visited = new ArrayList<>();

   @Override
   public Solution search(Searchable<char[][]> searchable) {

       State<char[][]> initialState = searchable.getInitialState();

       queue.add(initialState );
       visited.add(initialState);

       while(!queue.isEmpty()){
           State<char[][]> item=queue.remove();
           if(searchable.isGoalState(item)) {
               return new Solution();
           }
           System.out.println("Hi I Am:");
           //****** test *****//
           for(char[] row: item.getState()) {
               for (char ch: row) {
                   System.out.print(ch);
               }
           }
           System.out.println(" And my next possible states are:");
           //****** end test *****/
           ArrayList<State<char[][]>> nextPossibleStates = searchable.getPossibleStates(item);

           for(State<char[][]> t: nextPossibleStates){
               if(t != null && !visited.contains(t)) {

                   //****** test *****//
                   for(char[] row: t.getState()) {
                       for (char ch: row) {
                           System.out.print(ch);
                       }
                       if(searchable.isGoalState(t)) {
                           System.out.print(" --->> GOAL !!!!");
                       }
                       System.out.println();


                   }
                   //****** end test *****/
                   queue.add(t);
                   visited.add(t);
               }
           }
           System.out.println("**********************");
       }
       return null;
   }

}