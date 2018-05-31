package pipeGame.client;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class LevelDisplayer extends GridPane {

    char[][] data;
    String view = "textual";

    public void setData(char[][] data) {
        setHgap(0);
        setVgap(0);
        this.data = data;
        getChildren().clear();
        reDraw();
    }

    public void changeViewMode() {
        if(view.toLowerCase().equals("visual")) {
            view = "textual";
        } else {
            view = "visual";
        }
        reDraw();
    }

    public void reDraw() {
        if(data == null)
            return;
        getChildren().clear();

        setBackground(new Background(new BackgroundFill(Color.GAINSBORO, new CornerRadii(0), new Insets(0))));

        for(int i = 0; i < data.length; i++) {
            addRow(i);
            for(int j = 0; j < data[i].length; j++) {
                addColumn(j);
                char c = data[i][j];

                Node grid = new PgPipeGrid(c);

                if(view.equals("textual"))
                    grid = new PgPipeButtonGrid(Character.toString(c));

                int finalJ = j;
                int finalI = i;
                grid.setOnMouseClicked(e->rotate(finalI, finalJ,1));
                add(grid,j,i);
            }
        }
    }

    public void viewSolution(ArrayList<String> solution) {

        for(String s: solution) {
            String vector[] =  s.split(",");
            System.out.println(s);
            this.rotate(Integer.valueOf(vector[0]),Integer.valueOf(vector[1]),Integer.valueOf(vector[2]));

        }

    }

    public void rotate(int row,int col,int times){
        char c = data[row][col];
        for(int i = 0; i < times; i++) {
            switch(data[row][col]) {
                case 'F':
                    c = '7';
                    break;
                case '7':
                    c = 'J';
                    break;
                case 'J':
                    c = 'L';
                    break;
                case 'L':
                    c = 'F';
                    break;
                case '|':
                    c = '-';
                    break;
                case '-':
                    c = '|';
                    break;

            }
            data[row][col] = c;
        }
        reDraw();
    }
}
