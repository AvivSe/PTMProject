package search;

import java.util.*;


public class BFS implements Searcher<char[][]> {
   private Queue<State<char[][]>> queue =  new LinkedList<>();;
   private ArrayList<State<char[][]>> visited = new ArrayList<>();
   @Override
   public Solution search(Searchable<char[][]> searchable) {
       int iterations  = 0;
       State<char[][]> initialState = searchable.getInitialState();
       queue.add(initialState );

       while(!queue.isEmpty()){
           State<char[][]> item=queue.remove();
           if(searchable.isGoalState(item)) {
               return new Solution();
           }
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
           visited.add(item);
           System.out.println("Possible states from here are:");
           //****** end test *****/
           ArrayList<State<char[][]>> nextPossibleStates = searchable.getPossibleStates(item);

           for(State<char[][]> t: nextPossibleStates){
               iterations++;
               StringBuilder comparable = new StringBuilder();
               for(char[] row: t.getState()) {
                   for (char ch : row) {
                       comparable.append(ch);
                   }
                   comparable.append("\n");
               }
               boolean isClosed = false;

               for(State<char[][]> s: visited) {
                   if (Arrays.deepEquals(s.getState(), t.getState())) {
                       isClosed = true;
                   }
               }

               //if(!isClosed) {
                if(!visited.contains(t)) {
                   t.setCameFrom(item);
                   //****** test *****//
                   System.out.print(comparable);
                   if(searchable.isGoalState(t)) {
                       System.out.print("!^^^^GOAL FOUND^^^^! (after " + iterations + " iterations)");
                       Solution solution = new Solution();


                       for(char[] line: t.getState()) {
                           StringBuilder lineBuilder = new StringBuilder();
                           for(int i = 0; i < line.length; i++) {
                               lineBuilder.append(line[i]);
                           }
                           solution.add(lineBuilder.toString());
                       }

                       return solution;
                   }System.out.println();
                   //****** end test *****/
                   queue.add(t);
                   visited.add(t);
               }
           }
           System.out.println("**********************");
           System.out.println("VISITED TILL NOW: " + visited.size());
           System.out.println("**********************");

       }
       System.out.println("Goal state not found, after " + iterations + " iterations");

       return null;
   }

}