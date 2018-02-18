/**
 * Best-first Search (Greedy, Priority based.)
 * 1. Take the best node from the open set ( based on heuristic cost ) and add it to the closed set
 *      If the node is the goal, trace back to start from that node
 *      Otherwise add the neighboring nodes to the open set that are moveable and aren't in the closed set.
 *
 * 2. Repeat until the end is found or no possible options.
 */

package common_searchers;

import searcher_interface.Searchable;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;
import java.util.PriorityQueue;

public class BestFirstSearch <T> extends PrioritySearcher<T> implements Searcher<T> {
    public BestFirstSearch() {
        System.out.println("Greedy Best-first Search is initalized");
    }

    @Override
    public Solution search(Searchable<T> searchable) {
        openList.add(searchable.getInitialState());
        while(openList.size() > 0) {
            State<T> n = openList.poll();
            closeList.add(n);

            if(searchable.isGoalState(n)) {
                System.out.println("Best-first: GOAL!");
                System.out.println("Cost: " + n.getCost());
                return backtrace(n);
            }

            for(State<T> s: searchable.getPossibleStates(n)) {
                if(!closeList.contains(s)) {
                    if(!openList.contains(s)) {
                       // s.setCost(n.getCost() + 1);
                        openList.add(s);
                    } else {
                        updateCostInOpenList(s);
                    }
                }
            }
        }
        System.out.println("Can't find path");
        return null;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue =
                new PriorityQueue<>(Integer::compare);
        queue.add(10);
        queue.add(1);
        queue.add(20);
        System.out.println(queue.poll());
        System.out.println("Size: " + queue.size());

    }

}
