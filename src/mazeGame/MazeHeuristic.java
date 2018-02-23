package mazeGame;

import searcher_interface.Heuristic;
import searcher_interface.State;

public class MazeHeuristic implements Heuristic<Grid>{
    Maze maze;
    public MazeHeuristic(Maze maze) {
        this.maze = maze;
    }

    @Override
    public double indication() {
        return 0;
    }

    @Override
    public boolean isLeftBetter(double left, double right) {
        return false;
    }

    @Override
    public double calcHeuristic(State<Grid> s) {
        int positionRow = s.getState().row;
        int positionCol = s.getState().col;
        int endRow = maze.getExit().row;
        int endCol = maze.getExit().col;

        // Manhattan Distance
        return Math.abs(positionRow - endRow) + Math.abs(positionCol - endCol);
    }
}
