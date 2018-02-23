package eightPuzzleGame;
import searcher_interface.Solution;
import server_interface.Instructions;
import java.util.ArrayList;

public class PuzzleInstructions extends ArrayList<String> implements Instructions {
    PuzzleInstructions(Solution<Puzzle> solution) {
        for(Puzzle step: solution) {
            add(step.toString());
        }
    }
}