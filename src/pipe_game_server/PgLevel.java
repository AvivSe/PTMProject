package pipe_game_server;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class PgLevel {
    private char[][] matrix;
    Point position;


    public char[][] getMatrix() {
        return matrix;
    }

    public PgLevel(char[][] data) {
        this.matrix = data.clone();
        this.position = getStart();
    }

    public PgLevel(PgLevel level) {
        this.matrix = new char[level.getNumOfRows()][level.getNumOfCol()];

        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                this.matrix[i][j] = level.matrix[i][j];
            }
        }
        this.position = new Point(level.position.x, level.position.y);
    }

    public PgLevel(int numRows, int numColumns) {
        this.matrix = new char[numRows][numColumns];
    }


    @Override
    public boolean equals(Object o) {
        PgLevel pgLevel2 = (PgLevel) o;
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix[i][j] != pgLevel2.matrix[i][j]) {
                    return false;
                }
            }
        }

        return position.x == ((PgLevel) o).position.x && position.y == ((PgLevel) o).position.y;
    }


    public void setObject(int row, int column, char c) {
        this.matrix[row][column] = c;
    }

    public void setObjectOnPosition(char c) {
        this.matrix[position.x][position.y] = c;
    }

    public char getObject(int row, int column) {
            return this.matrix[row][column];
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

    public Point getStart() {
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix[i][j] == 's') {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public Point getGoal() {
        for (int i = 0; i < getNumOfRows(); i++) {
            for (int j = 0; j < getNumOfCol(); j++) {
                if (matrix[i][j] == 'g') {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(position);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }

//    private boolean findPaths(int i, int j) {
//
//        char c = getObject(i, j);
//        setObject(i, j, ' ');
//
//        boolean tmp;
//
//        switch (c) {
//            case 'g':
//                return true;
//            case 's':
//                tmp = up(i, j, this) && findPaths(i - 1, j) ||
//                        down(i, j, this) && findPaths(i + 1, j) ||
//                        right(i, j, this) && findPaths(i, j + 1) ||
//                        left(i, j, this) && findPaths(i, j - 1);
//                if (tmp) switchPotenitial(i,j);
//                return tmp;
//            case '|':
//                return  up(i, j, this) && findPaths(i - 1, j) ||
//                        down(i, j, this) && findPaths(i + 1, j);
//            case '-':
//                return  right(i, j, this) && findPaths(i, j + 1) ||
//                        left(i, j, this) && findPaths(i, j - 1);
//            case 'L':
//                return  up(i, j, this) && findPaths(i - 1, j) ||
//                        right(i, j, this) && findPaths(i, j + 1);
//            case 'F':
//                return  down(i, j, this) && findPaths(i + 1, j) ||
//                        right(i, j, this) && findPaths(i, j + 1);
//            case '7':
//                return  down(i, j, this) && findPaths(i + 1, j) ||
//                        left(i, j, this) && findPaths(i, j - 1);
//            case 'J':
//                return  up(i, j, this) && findPaths(i - 1, j) ||
//                        left(i, j, this) && findPaths(i, j - 1);
//            default:
//                return false;
//        }
//    }

//    interface FunciMonkey {
//        void action(Object obj);
//    }

//    public void foreach(FunciMonkey funciMonkey, Predicate predicate) {
//        for(ArrayList<Part> row: matrix) {
//            for (Part part : row) {
//                if (predicate.test(part)){
//                    funciMonkey.action(part);
//                }
//            }
//        }
//    }

//    public void foreach(FunciMonkey funciMonkey) {
//        for(ArrayList<Part> row: matrix) {
//            for (Part part : row) {
//                    funciMonkey.action(part);
//            }
//        }
//    }

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

            result.position = result.getStart();
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
        return copied;
    }

    protected PgLevel copy(Point newPosition) {
        StringBuilder stringBuilder = new StringBuilder();

        for(char[] line: this.getMatrix()) {
            for(char c: line) {
                stringBuilder.append(c);
            }
            stringBuilder.append("\n");
        }

        position = new Point(5,5);

        String result = stringBuilder.toString();
        PgLevel copied = LevelBuilder.build(result.substring(0, result.length()-1));
        return copied;
    }

    public static void main(String[] args) {
        PgLevel level = LevelBuilder.build("s-7\n|-g");
        System.out.println(level);

       // level.findPaths(0,0);



        //System.out.println(level.getStart());
        //System.out.println(level.getGoal());
    }

}