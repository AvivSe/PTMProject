package eightPuzzleGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Puzzle {
    // Puzzle data
    byte[][] data;

    // position
    Point position;

    // CTOR
    public Puzzle(byte[][] data) {
        byte[][] copyData = new byte[data.length][data[0].length];

        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, copyData[i], 0, data[i].length);
        }

        this.data = copyData;

        this.position = getpos((byte) 0);
    }

    // COPY TOR with swaping
    public Puzzle(byte[][] data, int row, int col) {
        byte[][] copyData = new byte[data.length][data[0].length];

        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, copyData[i], 0, data[i].length);
        }

        this.data = copyData;

        this.position = getpos((byte) 0);
        this.data[position.x][position.y] = this.data[row][col];
        this.data[row][col] = 0;
        this.position = new Point(row , col);
    }

    // helper
    private Point getpos(byte n){
        for(int i=0;i<data.length;i++)
            for(int j=0;j<data[i].length;j++){
                if(data[i][j]==n)
                    return new Point(i,j);
            }
        return null;
    }


    // helper
    private boolean isInArrayBounds(int i, int j){
        return (i>=0 && i<data.length && j>=0 && j<data[i].length);
    }

    // returns a list of possible grid positions given a grid position
    List<Puzzle> getPossibleMoves(){
        int i = position.x;
        int j = position.y;
        List<Puzzle> list=new ArrayList<>();

        if(isInArrayBounds(i, j-1)) list.add(new Puzzle(this.data, i,j-1));
        if(isInArrayBounds(i-1, j)) list.add(new Puzzle(this.data,i-1,j));
        if(isInArrayBounds(i, j+1)) list.add(new Puzzle(this.data, i,j+1));
        if(isInArrayBounds(i+1, j)) list.add(new Puzzle(this.data, i+1,j));

        return list;
    }

    // returns a list of possible grid positions given a grid position
    List<Puzzle> getPossibleGoals(){
        List<Puzzle> goals=new ArrayList<>();

        byte[][] goal1={
                {1,2,3},
                {8,0,4},
                {7,6,5}
        };

//        byte[][] goal2={
//                {1,2,3},
//                {6,5,4},
//                {7,8,0}
//        };

        goals.add(new Puzzle(goal1));

        return goals;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(byte[] row: data) {
            for(byte col: row) {
                stringBuilder.append(col+ " ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;

        if (position.x != puzzle.position.x || position.y != puzzle.position.y) return false;

        for(int i =0; i < puzzle.data.length; i++) {
            for(int j = 0; j < puzzle.data[i].length; j++) {
                if(data[i][j] != puzzle.data[i][j])
                    return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(position);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

}
