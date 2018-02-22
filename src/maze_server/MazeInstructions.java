package maze_server;

import game_server_interface.Instructions;
import searcher_interface.Solution;

import java.util.ArrayList;

public class MazeInstructions extends ArrayList<String> implements Instructions {
    MazeInstructions(Solution<Grid> solution, Maze m) {
//        if(m.getEntrance().col < solution.get(0).col)
//            this.add("RIGHT");
//        else if(m.getEntrance().col > solution.get(0).col)
//            this.add("LEFT");
//        else if(m.getEntrance().row < solution.get(0).row)
//            this.add("DOWN");
//        else if(m.getEntrance().col > solution.get(0).col)
//            this.add("UP");

        for(int i = 0; i < solution.size()-1; i++) {
            if (solution.get(i).col < solution.get(i + 1).col)
                this.add("RIGHT");
            else if (solution.get(i).col > solution.get(i + 1).col)
                this.add("LEFT");
            else if (solution.get(i).row < solution.get(i + 1).row)
                this.add("DOWN");
            else if (solution.get(i).col > solution.get(i + 1).col)
                this.add("UP");
        }

    }
}
