package mazeGame;

import searcher_interface.Searchable;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.List;

public class MazeSearchable implements Searchable<Grid> {
    Maze maze;

    public MazeSearchable(Maze maze) {
        this.maze = maze;
    }

    @Override
    public State getInitialState() {
        return new State<>(maze.getEntrance());
    }

    @Override
    public ArrayList<State<Grid>> getPossibleStates(State<Grid> state) {
        ArrayList<State<Grid>> results = new ArrayList<>();

        List<Grid> possibleMoves = maze.getPossibleMoves(state.getState());

        /* case you moved where you came from */
        if (state.getCameFrom() != null) {
            int cameFromRow = state.getCameFrom().getState().row;
            int cameFromCol = state.getCameFrom().getState().col;

            if (state.getState().row == cameFromRow && state.getState().col == cameFromCol)
                return results;

        }

        for(Grid move: possibleMoves) {
            //if(move.row == state.getState().row || move.col == state.getState().col) {
                State<Grid> neighbor = new State<>(move);
                neighbor.setCameFrom(state);
                neighbor.setCost(state.getCost() + 1);
                results.add(neighbor);
            //}
        }

        return results;
    }

    @Override
    public boolean isGoalState(State<Grid> state) {
        return state.getState().row == maze.getExit().row && state.getState().col == maze.getExit().col;
    }
}
