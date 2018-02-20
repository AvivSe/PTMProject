/**
 * This class describes my Pipe Game problem.
 * in this game we have water flow from 's' to 'g', its a n*m grid and the water have to flow trow the pipes.
 * so in this case, Position symbols the location in the grid of the water, to describe various of 'states' we -
 * can both rotate the pipe and change the position.
 * Aviv Segal
 */
package pipe_game_server;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class PgLevel {
    private char[][] matrix;

    public Point getPosition() {
        return position;
    }

    Point position;
    Point start;
    Point end;


    public char[][] getMatrix() {
        return matrix;
    }

    public PgLevel(char[][] data) {
        this.matrix = data.clone();
        this.position = initStart();
        this.start = position;
        this.end = initEnd();
    }

    public PgLevel(PgLevel level) {
        this.matrix = new char[level.getNumOfRows()][level.getNumOfCol()];

        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                this.matrix[i][j] = level.matrix[i][j];
            }
        }

        initStart();
        initEnd();
        this.position = new Point(level.position.x, level.position.y);
    }

    public PgLevel(int numRows, int numColumns) {
        this.matrix = new char[numRows][numColumns];
    }


    public PgLevel setObject(int row, int column, char c) {
        this.matrix[row][column] = c;
        return this;
    }

    public void setObjectOnPosition(char c) {
        this.matrix[position.x][position.y] = c;
    }

    public char getObject(int row, int column) {
            return this.matrix[row][column];
    }

    public char getObjectOnPosition() {
        return this.matrix[position.x][position.y];
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int lengthRows = getNumOfRows();
        int lengthCols = getNumOfCol();
        for(int i = 0; i < lengthRows; i++) {
            for(int j = 0; j < lengthCols; j++) {
                result.append(matrix[i][j]);
            }
            result.append("\n");
        }

        String resultString = result.toString();
        int len = resultString.length();
        return resultString.substring(0,len-1); // remove last \n
    }

    public int getNumOfRows() { return this.matrix.length; }

    public int getNumOfCol() { return this.matrix[0].length;}

    public Point initStart() {
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix[i][j] == 's') {
                    start = new Point(i, j);
                    return start;
                }
            }
        }
        return null;
    }

    public Point initEnd() {
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix[i][j] == 'g') {
                    end = new Point(i, j);
                    return end;
                }
            }
        }
        return null;
    }


    /* ILUZ TIME TO ROTATE */
    public int rotate(char A, char B) {
        if (A == B)
            return 0;
        switch (A) {
            case 'L':
                return 1 + rotate('F', B);
            case 'F':
                return 1 + rotate('7', B);
            case '7':
                return 1 + rotate('J', B);
            case 'J':
                return 1 + rotate('L', B);
            case '|':
                return 1 + rotate('-', B);
            case '-':
                return 1 + rotate('|', B);
        }
        return 0;
    }

    public PgLevel rotate(int row,int column) {
        switch (this.matrix[row][column]) {
            case 'L':
                this.setObject(row,column,'F');
                break;
            case 'F':
                this.setObject(row,column,'7');
                break;
            case '7':
                this.setObject(row,column,'J');
                break;
            case 'J':
                this.setObject(row,column,'L');
                break;
            case '-':
                this.setObject(row,column,'|');
                break;
            case '|':
                this.setObject(row,column,'-');
                break;
        }
        return this;
    }

    PgLevel setPosition(int i,int j) {
        position.x = i;
        position.y = j;
        return this;
    }


    /* END OF ILUZ */
    @Override
    public boolean equals(Object o) {
        return equals((PgLevel)o);
    }

    @Override
    public int hashCode() {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < getNumOfRows(); i++) {
            for(int j=0; j < getNumOfCol(); j++) {
                result.append(matrix[i][j]);
            }
        }
        return result.toString().hashCode();
    }

    public static class LevelBuilder {

        public static PgLevel build(String problem) {
            String[] rows = problem.split("\n");
            int numRows = rows.length;
            int numColumns = rows[0].length();

            PgLevel result = new PgLevel(numRows,numColumns);

            for (int i = 0; i < result.getNumOfRows(); i++) {
                char[] chars = rows[i].toCharArray();
                for (int j = 0; j < result.getNumOfCol(); j++) {
                    result.matrix[i][j] = chars[j];
                }
            }
            result.position = result.initStart();
            result.initEnd();
            return result;
        }
    }

    protected PgLevel copy() {
        StringBuilder stringBuilder = new StringBuilder();
        for(char[] line: this.getMatrix()) {
            for(char c: line) {
                stringBuilder.append(c);
            }
            stringBuilder.append("\n");
        }
        String result = stringBuilder.toString();
        PgLevel copied = LevelBuilder.build(result.substring(0, result.length()-1));
        copied.end = this.end;
        copied.start = this.start;
        copied.position = new Point(position.x, position.y);
        return copied;
    }

    public static void main(String[] args) {
        PgLevel level = LevelBuilder.build("s-7\n|-g");
        System.out.println(level);
    }

}