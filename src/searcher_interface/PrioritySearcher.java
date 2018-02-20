package searcher_interface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.ToDoubleFunction;

class PrioritySearcher<T> extends CommonSearcher<T> {
    PriorityQueue<State<T>> openList;
    Heuristic heuristic1;

    PrioritySearcher(Heuristic heuristic) {
        this.openList = new PriorityQueue<>(Comparator.comparingDouble((ToDoubleFunction<State<T>>) heuristic::calcHeuristic));
    }

    void updateHeuristicInPriorityQueue(State<T> newState) {
        ArrayList<State<T>> poppedStates = new ArrayList<>();
        //State<T> poppedState = openList.remove();

        // Pop all the states until you reach the state we wish to update.
        while (openList.size() != 0 && openList.peek() != newState) {
            poppedStates.add(openList.remove());
        }

        if (heuristic1.calcHeuristic(openList.peek()) > heuristic1.calcHeuristic(newState)) {
            // Dequeue the state with the old cost
            // and enqueue the state with the new cost.
            openList.remove();
            openList.add(newState);
        }

        // Re-enqueue the popped states
        openList.addAll(poppedStates);
    }

}
