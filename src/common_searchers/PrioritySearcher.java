package common_searchers;

import searcher_interface.Heuristic;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.PriorityQueue;

class PrioritySearcher<T> extends CommonSearcher<T> {
    PriorityQueue<State<T>> openList;

    PrioritySearcher(Heuristic heuristic) {
        this.openList = new PriorityQueue<>((o1, o2) -> {
            if (heuristic.calcHeuristic(o1) < heuristic.calcHeuristic(o2)) return -1;
            else if (heuristic.calcHeuristic(o1) > heuristic.calcHeuristic(o2)) return 1;
            else return 0;
        });    }

    void updateCostInPriorityQueue(State<T> newState) {
        ArrayList<State<T>> poppedStates = new ArrayList<>();
        //State<T> poppedState = openList.remove();

        // Pop all the states until you reach the state we wish to update.
        while (openList.size() != 0 && !openList.peek().equals(newState) ) {
            poppedStates.add(openList.remove());
        }

        if (openList.peek().getCost() > newState.getCost()) {
            // Dequeue the state with the old cost
            // and enqueue the state with the new cost.
            openList.remove();
            openList.add(newState);
        }

        // Re-enqueue the popped states
        openList.addAll(poppedStates);
    }

}
