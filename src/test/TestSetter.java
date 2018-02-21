package test;

// edit these imports according to your project
import game_server_interface.CacheManager;
import game_server_interface.ClientHandler;
import pipe_game_server.PgCacheManager;
import pipe_game_server.PgClientHandler;
import pipe_game_server.PgServer;
import pipe_game_server.PgSolver;
import game_server_interface.Server;
import game_server_interface.Solver;

import java.io.IOException;

public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		dt.setServerClass(PgServer.class);
		dt.setClientHandlerInterface(ClientHandler.class);
		dt.setClientHandlerClass(PgClientHandler.class);
		dt.setCacheManagerInterface(CacheManager.class);
		dt.setCacheManagerClass(PgCacheManager.class);
		dt.setSolverInterface(Solver.class);
		dt.setSolverClass(PgSolver.class);
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
