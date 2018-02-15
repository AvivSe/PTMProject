package common_searchers;

import searcher_interface.Searchable;
import searcher_interface.Searcher;
import searcher_interface.Solution;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.Random;

public class HillClimbing<T> extends CommonSearcher implements Searcher<T> {
    private long timeToRun;
    private StateGrader<T> grader;

    @Override
    public Solution search(Searchable<T> searchable) {
        //Define the current state as an initial state
        State<T> next = searchable.getInitialState();
        Solution result = new Solution();

        long time0 = System.currentTimeMillis();

        //Loop until the goal state is achieved or no more operators can be applied on the current state:
        while (System.currentTimeMillis() - time0 < timeToRun) {
            ArrayList<State<T>> neighbors = searchable.getPossibleStates(next);

            if (Math.random() < 0.7) { // with a high probability
                // find the best one
                int grade = 0;
                for (State<T> step : neighbors) {
                    int g = grader.grade(step);
                    if (g > grade) {
                        grade = g;
                        next = step;
                        //add this step to the solution
                        //result.add
                        //result.add(step[0]);

                    }
                }
            } else {
                next = neighbors.get(new Random().nextInt(neighbors.size()));
            }
        }

        return result;

    }

    interface StateGrader<T>{
        int grade(State<T> s); // give a grade to a certain state - how close it is to the solution
    }
}
