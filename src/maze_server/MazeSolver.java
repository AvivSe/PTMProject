package maze_server;

import server_interface.Solver;
import common_searchers.BestFirstSearch;
import searcher_interface.Searcher;
import searcher_interface.Solution;

import java.util.List;

public class MazeSolver implements Solver<Maze> {

    @Override
    public MazeInstructions solve(Maze m) {
        Searcher BestFS = new BestFirstSearch(new MazeHeuristic(m));
        Solution<Grid> solution = BestFS.search(new MazeSearchable(m));

        return new MazeInstructions(solution, m);
    }


    public static void main(String[] args) {

        //----------- Question 3 --------------
        // test Best first Search (20 points)
        byte[][] mazeData={
                {1,1,1,1,1},
                {2,0,0,0,1},
                {1,1,1,0,1},
                {1,0,0,0,1},
                {1,0,1,0,1},
                {1,3,1,1,1},
        };
        Maze m=new Maze(mazeData);

        MazeSolver mazeSolver = new MazeSolver();

        List<String> actions = mazeSolver.solve(m);

        System.out.println("Client asked solution for this maze:");

        for(byte[] row: m.data) {
            for(byte column: row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }

        System.out.println("The directions are:");
        System.out.println(actions);


        // the following is the solution for the maze above:
        //List<String> answer = Arrays.asList("RIGHT","RIGHT","RIGHT","DOWN","DOWN","LEFT","LEFT","DOWN","DOWN");
        //actions=answer;

        final Grid p=m.getEntrance();
        Maze.followSolverDirectionsGoalCheck(actions, p);

        if(!p.equals(m.getExit()))
            System.out.println("the Maze is not solved (-20)");


        System.out.println("done");
    }

}
