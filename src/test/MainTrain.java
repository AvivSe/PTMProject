package test;

//import java.util.Arrays;
import maze_server.Grid;
import maze_server.Maze;
import maze_server.MazeSolver;

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

	}

}
