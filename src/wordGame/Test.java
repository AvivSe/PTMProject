package wordGame;

import common_searchers.BestFirstSearch;
import searcher_interface.*;
import test.WordGame;

import java.util.*;

public class Test {

    public static List<String> solveWordGame(WordGame tp) {
        int n = tp.getCurrentState().length();

        List<String> legalMoves = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            for(int j = i +1; j <n; j++) {
               legalMoves.add(i+","+j);
            }
        }

        Searchable<MoveHelper> searchable = new Searchable<>() {
            @Override
            public State<MoveHelper> getInitialState() {
                return new State<>(new MoveHelper(tp.getStart()));
            }

            @Override
            public List<State<MoveHelper>> getPossibleStates(State<MoveHelper> state) {
                List<State<MoveHelper>> nextStates = new ArrayList<>();

                legalMoves.forEach(move -> {
                    if (!(state.getState().getMove().equals(move))) {
                        MoveHelper helper = new MoveHelper(move, state.getState().wordState);
                        nextStates.add(new State<>(helper, state, state.getCost() + helper.distance));
                    }
                });

                return nextStates;
            }

            @Override
            public boolean isGoalState(State<MoveHelper> state) {
                LinkedList<String> offerActions = new LinkedList<>();

                State<MoveHelper> tmp = state;
                while(tmp.hasCameFrom()) {
                    offerActions.addFirst(tmp.getState().getMove());
                    tmp = tmp.getCameFrom();
                }

                tp.applyActions(offerActions);
                boolean result = tp.isGoal();

                Collections.reverse(offerActions);
                tp.applyActions(offerActions);

                return result;
            }
        };

        Searcher<MoveHelper> searcher = new BestFirstSearch<>(new EmptyHeur());
        List<String> result = new LinkedList<>();

        Solution<MoveHelper> solution = searcher.search(searchable);
        solution.forEach(x->result.add(x.getMove()));

        result.remove(0);
        return result;
    }

    public static void main(String[] args) {
        //----------- Question 3 --------------
        // test Best first Search (30 points)


        test.WordGame wg=new WordGame("afbcegd","fgedabc");
        List<String> actions = solveWordGame(wg);

        if(actions!=null)
            wg.applyActions(actions); // applies the actions

        if(!wg.isGoal())
            System.out.println("the Word Game is not solved (-30)");


        System.out.println("done");


    }
}
