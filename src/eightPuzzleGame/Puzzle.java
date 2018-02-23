package eightPuzzleGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {
//    // Puzzle data
//    byte[][] data;
//
//    // position
//    Point position;
//
//    // CTOR
//    public Puzzle(byte[][] data) {
//        this.data=data;
//    }
//
//    // helper
//    private Point getpos(byte n){
//        for(int i=0;i<data.length;i++)
//            for(int j=0;j<data[i].length;j++){
//                if(data[i][j]==n)
//                    return new Point(i,j);
//            }
//        return null;
//    }
//
//    // returns the grid position of the entrance - 2 in the data
//    public Point getEntrance(){
//        return getpos((byte)2);
//    }
//
//    // returns the grid position of the entrance - 3 in the data
//    public Grid getExit(){
//        return getpos((byte)3);
//    }
//
//    // helper
//    private boolean isFree(int i, int j){
//        return(i>=0 && i<data.length && j>=0 && j<data[i].length && (data[i][j]==(byte)0 || data[i][j]==(byte)3));
//    }
//
//    // returns a list of possible grid positions given a grid position
//    List<Grid> getPossibleMoves(Grid g){
//        int i=g.row, j=g.col;
//        if(i>=0 && i<data.length && j>=0 && j<data[i].length){
//            List<Grid> list=new ArrayList<>();
//            if(isFree(i, j-1)) list.add(new Grid(i,j-1));
//            //if(isFree(i-1, j-1)) list.add(new Grid(i-1,j-1));
//            if(isFree(i-1, j)) list.add(new Grid(i-1,j));
//            //if(isFree(i-1, j+1)) list.add(new Grid(i-1,j+1));
//            if(isFree(i, j+1)) list.add(new Grid(i,j+1));
//            //if(isFree(i+1, j+1)) list.add(new Grid(i+1,j+1));
//            if(isFree(i+1, j)) list.add(new Grid(i+1,j));
//            //if(isFree(i+1, j-1)) list.add(new Grid(i+1,j-1));
//            return list;
//        }
//        return null;
//    }
//
//    public static void followSolverDirectionsGoalCheck(List<String> actions, Grid p) {
//        actions.forEach(s->{
//            if(s.equals("UP")) p.row--;
//            if(s.equals("DOWN")) p.row++;
//            if(s.equals("RIGHT")) p.col++;
//            if(s.equals("LEFT")) p.col--;
//        });
//    }

}
