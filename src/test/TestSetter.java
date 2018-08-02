package test;

import common_searchers.BestFirstSearch;
import pipeGame.server.*;
import searcher_interface.*;
import server_interface.CacheManager;
import server_interface.ClientHandler;
import server_interface.Server;
import server_interface.Solver;

import java.util.*;

// edit these imports according to your project

public class TestSetter {

	public static void setClasses(DesignTest dt){

		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		// server's implementation
		dt.setServerClass(PgServer.class);
		// client handler interface
		dt.setClientHandlerInterface(ClientHandler.class);
		// client handler class
		dt.setClientHandlerClass(PgClientHandler.class);
		// cache manager interface
		dt.setCacheManagerInterface(CacheManager.class);
		// cache manager class
		dt.setCacheManagerClass(PgCacheManager.class);
		// solver interface
		dt.setSolverInterface(Solver.class);
		// solver class
		dt.setSolverClass(PgSolver.class);
		// searchable interface
		dt.setSearchableInterface(Searchable.class);
		// searcher interface
		dt.setSearcherInterface(Searcher.class);
		// your searchable pipe game class
		dt.setPipeGameClass(PgSearchable.class);
		// your Best First Search implementation
		dt.setBestFSClass(BestFirstSearch.class);
	}

	// run your server here
	static Server s;
	public static void runServer(int port){
		s=new PgServer(port);
		s.start(new PgClientHandler(new PgSolver(),new PgCacheManager()));
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}

	/* ------------- Best First Search Test --------------
	 * You are given a Word Game
	 * Create a new Searchable from the Word Game
	 * Solve the Word Game
	 * and return a list of index switches as strings
	 * e.g., {"0,5" , "3,4" , "2,1"}
	 *
	 */

	static Queue<String> moves =  new LinkedList<>();
	public static List<String> solveWordGame(WordGame tp) {

		int n = tp.getCurrentState().length();
		for(int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				String tmp = j + "," + (n-1);
				moves.add(tmp);
			}
		}

		// of course, you should replace this code with a calculated answer...
		List<String> sol=Arrays.asList("0,3","1,3");


		Searchable<String> wordGameSearchable = new Searchable<String>() {
			State<String> initalstate = new State<>("",null,0);

			@Override
			public State<String> getInitialState() {
				return initalstate;
			}

			@Override
			public ArrayList<State<String>> getPossibleStates(State<String> state) {
				ArrayList<State<String>> result = new ArrayList<>();
				String move = moves.poll();
				moves.add(move);
				String[] tmp = move.split(",");
				int i = Integer.valueOf(tmp[0]), j = Integer.valueOf(tmp[1]);
				double cost = tp.switchLetters(i, j);
				result.add(new State<>(move, state, state.getCost() + cost));

				return result;
			}

			@Override
			public boolean isGoalState(State<String> state) {
				return tp.isGoal();
			}
		};

		Searcher<String> searcher = new BestFirstSearch<>(new Heuristic() {
			@Override
			public double indication() {
				return 0;
			}

			@Override
			public boolean isLeftBetter(double left, double right) {
				return false;
			}

			@Override
			public double calcHeuristic(State state) {
				return 0;
			}
		});
		Solution solution = searcher.search(wordGameSearchable);
		solution.remove(0);
		Collections.reverse(solution);
		tp.applyActions(solution);
		Collections.reverse(solution);
		return solution;
	}




}
