package server;
// TODO: Problem may be a class.

import search.*;

public class MySolver implements Solver {

    // TODO: create algorithms that implements Searcher.
    @Override
    public Solution solve(MyLevel level) {
       Searcher searcher = new BFS(); // Could be any type of searcher.
       Solution solution = searcher.search(new MySearchable(level));
       return solution;
    }

    public static void main(String[] args) {
        MySolver mySolver = new MySolver();
        MyLevel level = MyLevel.LevelBuilder.build("s|7\nL-g");
        Solution sol = mySolver.solve(level);
        System.out.println("\nAnd the solution is: \n" + sol)
        ;
    }
}
