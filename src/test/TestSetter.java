package test;

// edit these imports according to your project
import server_interface.*;
		import pipeGame.*;
import common_searchers.BestFirstSearch;

import searcher_interface.*;


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
}
