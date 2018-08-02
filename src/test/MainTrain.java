package test;


import java.util.List;
import java.util.Random;

public class MainTrain {

	public static void main(String[] args) {
//		//----------- Question 1 --------------
//		// design test (30 points)
//		DesignTest dt=new DesignTest();
//		TestSetter.setClasses(dt);
//		dt.testDesign();
//
//		//----------- Question 2 --------------
//		// execution test (40 points)
//		Random r=new Random();
//		int port=6000+r.nextInt(1000);
//		TestSetter.runServer(port);
//		try{
//			TestServer.runClient(port);
//		}finally{
//			TestSetter.stopServer();
//		}
//
		//----------- Question 3 --------------
		// test Best first Search (30 points)
		
		
		WordGame wg=new WordGame("bdca","abcd");		
		List<String> actions = TestSetter.solveWordGame(wg);
		
		if(actions!=null)
			wg.applyActions(actions); // applies the actions
		
		if(!wg.isGoal())
			System.out.println("the Word Game is not solved (-30)");
		
		
		System.out.println("done");
	}

}
