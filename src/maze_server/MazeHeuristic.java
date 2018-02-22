package maze_server;

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
        int positionX = s.getState().row;
        int positionY = s.getState().col;
        int endX = maze.getExit().row;
        int endY = maze.getExit().col;

        // Manhattan Distance
        return Math.abs(positionX - endX) + Math.abs(positionY - endY);
    }
}
