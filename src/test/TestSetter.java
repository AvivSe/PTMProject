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

	public static List<String> solveWordGame(WordGame tp) {

		return null;
	}




}
