/**
 * Aviv Segal 2018
 *
 */

package pipeGame.server;

import searcher_interface.Heuristic;
import searcher_interface.State;


public class PgManhattanDistance implements Heuristic<PgLevel> {

    @Override
    public double indication() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean isLeftBetter(double left, double right) {

        return left < right;
    }

    @Override
    public double calcHeuristic(State<PgLevel> s) {
        int positionX = s.getState().getPosition().x;
        int positionY = s.getState().getPosition().y;
        int endX = s.getState().getEnd().x;
        int endY = s.getState().getEnd().y;

        // The Manhattan Distance is taking the distance from going all the way on the X axis and -
        // adding that to the distance all the way on the Y axis to go from point A to point B.
        // This heuristic should usually be used whenever the AI can only move in the 4 cardinal directions.
        return Math.abs(positionX - endX) + Math.abs(positionY - endY); // in Pipe Game the movement cost is always 1.

    }
}
