package server;
// TODO: Problem may be a class.

import level.MyLevel;

public class MySolver implements Solver {

    // TODO: create algorithms that implements Searcher.
    @Override
    public String solve(MyLevel problem) {
       /* Searcher searcher = new BFS();
        Solution solution = searcher.search(new MySearchable(problem));
        //return solution;*/
        return "s7-\n" +
                "gJ|\ndone";

    }
}
