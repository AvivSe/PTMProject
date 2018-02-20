
package common_searchers;

import searcher_interface.*;

import java.util.ArrayList;
import java.util.Random;

public class HillClimbing<T> extends CommonSearcher<T> implements Searcher<T> {
    private long timeToRun;
    private Heuristic<T> grader;

    public HillClimbing(long timeToRun, Heuristic<T> grader) {
        this.timeToRun = timeToRun;
        this.grader = grader;
    }

    @Override
    public Solution search(Searchable<T> searchable) {
        //Define the current state as an initial state
        State<T> next = searchable.getInitialState();

        long time0 = System.currentTimeMillis();

        //Loop until the goal state is achieved or no more operators can be applied on the current state:
        while (System.currentTimeMillis() - time0 < timeToRun) {
            ArrayList<State<T>> neighbors = searchable.getPossibleStates(next);

            if (searchable.isGoalState(next)) return this.bt(next);

            if (neighbors.size() > 0) {
                if (Math.random() < 0.7) { // with a high probability
                    // find the best one
                    double grade = 0;
                    for (State<T> step : neighbors) {
                        double g = grader.calcHeuristic(step);
                        if (g <= grade) {
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
        }

        return null;

    }

    interface StateGrader<T> {
        int grade(State<T> s); // give a grade to a certain state - how close it is to the solution
    }
}
