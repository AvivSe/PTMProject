package pipeGame.client;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class LevelDisplayer extends GridPane {

    char[][] data;

    public void setData(char[][] data) {
        setHgap(0);
        setVgap(0);
        this.data = data;
        getChildren().clear();
        reDraw();
    }

    public void reDraw() {
        if(data == null)
            return;

        for(int i = 0; i < data.length; i++) {
            addRow(i);
            for(int j = 0; j < data[i].length; j++) {
                addColumn(j);
                char c = data[i][j];

                Button btn = new Button(Character.toString(c));
                btn.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR,26));
                btn.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

                Color color;

                switch (c) {
                    case 's':
                        color = Color.GREEN;
                        break;
                    case 'g':
                        color = Color.RED;
                        break;

                        default:
                            color = Color.BLACK;
                }

                btn.setTextFill(color);
                int finalJ = j;
                int finalI = i;
                btn.setOnAction(e->rotate(finalI, finalJ,1));
                add(btn,j,i);
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
