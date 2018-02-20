/**
 * Best-first Search (Greedy, Priority based.)
 * 1. Take the best node from the open set ( based on heuristic cost ) and add it to the closed set
 * If the node is the goal, trace back to start from that node
 * Otherwise add the neighboring nodes to the open set that are moveable and aren't in the closed set.
 * <p>
 * 2. Repeat until the end is found or no possible options.
 */

package common_searchers;

import searcher_interface.*;

import java.util.List;
import java.util.PriorityQueue;

public class BestFirstSearch<T> extends PrioritySearcher<T> implements Searcher<T> {
    public BestFirstSearch(Heuristic heuristic) {
        super(heuristic);
        System.out.println("Greedy Best-first Search is initialized");
    }

    @Override
    public Solution search(Searchable<T> searchable) {
        openList.add(searchable.getInitialState());
        while (openList.size() > 0) {
            State<T> n = openList.poll();

            if (searchable.isGoalState(n)) {
                System.out.println("Best-first: GOAL!");
                System.out.println("Cost: " + n.getCost());
                return bt(n);
            }

            List<State<T>> possibleStates = searchable.getPossibleStates(n);
            for (State<T> s : possibleStates) {
                if (!closeList.contains(s)) {
                    if (!openList.contains(s)) {
                        openList.add(s);
                    } else {
                        updateCostInOpenList(s);
                    }
                }
            }

            closeList.add(n);
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
