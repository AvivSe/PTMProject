package pipeGame;

import java.awt.*;

public class PgLevelBuilder {
    public static PgLevel build(String problem) {
        String[] rows = problem.split("\n");
        int numRows = rows.length;
        int numColumns = rows[0].length();

        PgLevel result = new PgLevel(numRows, numColumns);

        for (int i = 0; i < result.getNumOfRows(); i++) {
            char[] chars = rows[i].toCharArray();
            for (int j = 0; j < result.getNumOfCols(); j++) {
                if (chars[j] == 's') {
                    result.setStart(new Point(i, j));
                    result.setPosition(result.getStart());
                }
                if (chars[j] == 'g') {
                    result.setEnd(new Point(i, j));
                }
                result.setObject(i, j, chars[j]);
            }
        }

        return result;
    }
}