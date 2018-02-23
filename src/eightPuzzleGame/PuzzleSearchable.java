package eightPuzzleGame;

import searcher_interface.Searchable;
import searcher_interface.State;

import java.util.ArrayList;
import java.util.List;

public class PuzzleSearchable implements Searchable<Puzzle> {
    private Puzzle initialState;

    public PuzzleSearchable(Puzzle initialState) {
        this.initialState = initialState;
    }

    @Override
    public State<Puzzle> getInitialState() {
        return new State<>(initialState);
    }

    @Override
    public ArrayList<State<Puzzle>> getPossibleStates(State<Puzzle> state) {
        ArrayList<State<Puzzle>> result = new ArrayList<>();

        List<Puzzle> nextSteps = state.getState().getPossibleMoves();

        for(Puzzle step :nextSteps) {
            State<Puzzle> neighbor = new State<>(step);
            neighbor.setCost(state.getCost()+1);
            neighbor.setCameFrom(state);
            result.add(neighbor);
        }

        return result;
    }

    @Override
    public boolean isGoalState(State<Puzzle> state) {
        List<Puzzle> possibleGoals = state.getState().getPossibleGoals();
        Puzzle checkingPuzzle = state.getState();
        int row = checkingPuzzle.position.x;
        int col = checkingPuzzle.position.y;

        for(Puzzle goal : possibleGoals) {
            if(row == goal.position.x && col == goal.position.y && checkingPuzzle.equals(goal)) {
                return true;
            }
        }

        return false;
    }
}
