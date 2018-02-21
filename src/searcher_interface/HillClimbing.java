
package searcher_interface;
/**
 *
 * In numerical analysis, hill climbing is a mathematical optimization technique which belongs to the family of local search.
 * It is an iterative algorithm that starts with an arbitrary solution to a problem,
 * then attempts to find a better solution by incrementally changing a single element of the solution.
 * If the change produces a better solution, an incremental change is made to the new solution, repeating until no-
 * further improvements can be found.
 */

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

            if (searchable.isGoalState(next)) {
//                System.out.println("HillClimbing: Goal");
                return this.BackTrace(next);
            }

            if (neighbors.size() > 0) {
                if (Math.random() < 0.7) { // with a high probability
                    // find the best one
                    double grade =  Double.POSITIVE_INFINITY;
                    for (State<T> step : neighbors) {
                        double g = grader.calcHeuristic(step);
                        if (g < grade) {
                            grade = g;
                            next = step;
                        }
                    }
                } else {
                    next = neighbors.get(new Random().nextInt(neighbors.size()));
                }
            }
        }
//        System.out.println("HillClimbing: TimeOut / NoPath");
        return null;

    }

}
