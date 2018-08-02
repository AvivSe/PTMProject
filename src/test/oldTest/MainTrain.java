package test.oldTest;

//import java.util.Arrays;
import mazeGame.Grid;
import mazeGame.Maze;
import mazeGame.MazeSolver;

import java.util.List;
import java.util.Random;

public class MainTrain {

	public static void main(String[] args) {
		//----------- Question 1 --------------
		// design test (40 points)
		DesignTest dt=new DesignTest();
		TestSetter.setClasses(dt);
		dt.testDesign();
		
		//----------- Question 2 --------------
		// execution test (40 points)
		Random r=new Random();
		int port=6000+r.nextInt(1000);
		TestSetter.runServer(port);
		try{
			TestServer.runClient(port);
		}finally{
			TestSetter.stopServer();
		}

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
		Maze m = new Maze(mazeData);

		MazeSolver mazeSolver = new MazeSolver();

		List<String> actions = mazeSolver.solve(m);

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
