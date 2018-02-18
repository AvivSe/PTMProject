package common_searchers;

import searcher_interface.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PrioritySearcher<T> extends CommonSearcher<T> {
    protected PriorityQueue<State<T>> openList;

    PrioritySearcher() {
        openList = new PriorityQueue<>(Comparator.comparingDouble(State::getCost));

        System.out.println("PrioritySearcher is initialized");
    }

    public void updateCostInOpenList(State<T> newState) {
        ArrayList<State<T>> poppedStates = new ArrayList<>();
        State<T> poppedState = openList.remove();
        // Pop all the states until you reach the state we wish to update.
        while (openList.size() != 0 && openList.peek() != newState) {
            poppedStates.add(openList.remove());
        }

        // If the new state's cost is actually lower than the old one's.
        if (openList.peek().getCost() > newState.getCost()) {
            // Dequeue the state with the old cost
            // and enqueue the state with the new cost.
            openList.remove();
            openList.add(newState);
        }

        // Re-enqueue the popped states
        for(State<T> s:poppedStates) {
            openList.add(s);
        }
    }

}
