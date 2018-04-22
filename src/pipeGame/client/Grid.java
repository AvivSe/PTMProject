package pipeGame.client;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


class PgPipeButtonGrid extends Button {
    PgPipeButtonGrid(String str) {
        super(str);
        setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR,26));
        setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

        Color color;

        switch (str.charAt(0)) {
            case 's':
                color = javafx.scene.paint.Color.GREEN;
                break;
            case 'g':
                color = javafx.scene.paint.Color.RED;
                break;

            default:
                color = Color.BLACK;
        }

        setTextFill(color);
    }
}

class PgPipeGrid extends GridPane {
    PgPipeGrid(char c) {
        for(int i = 0; i < 3; i++) {
            addRow(i);
            for(int j = 0; j < 3; j++) {
                addColumn(j);
                Pane pane = new Pane();
                pane.setPrefSize(Integer.MAX_VALUE,Integer.MAX_VALUE);
                Color color = null;
                double topLeft = 0, topRight = 0, bottomRight = 0, bottomLeft = 0;
                double r = 0.3;
                switch (c) {
                    case 's':
                            color = Color.DARKGREEN;
                            if(i==0 && j ==0)
                                topLeft = 1;
                            if(i==2 && j==0)
                                topRight = 1;
                            if(i==0&&j==2)
                                bottomLeft = 1;
                            if(i==2 && j==2)
                                bottomRight = 1;
                            if(i==1 && j==1)
                                topLeft = topRight = bottomRight = bottomLeft = 1;
                        break;
                    case 'g':
                            color = Color.DARKRED;
                            if(i==0 && j ==0)
                                topLeft = 1;
                            if(i==2 && j==0)
                                topRight = 1;
                            if(i==0&&j==2)
                                bottomLeft = 1;
                            if(i==2 && j==2)
                                bottomRight = 1;
                        break;
                    case 'F':
                        if((i==1 && j==2) || (i==1 && j==1) || (i==2 && j==1))
                            color = Color.DARKBLUE;
                        if(i==1 && j == 1) {
                            topLeft = r;
                        }
                        break;
                    case '7':
                        if((i==0 && j==1) || (i==1 && j==1) || (i==1 && j==2))
                            color = Color.DARKBLUE;
                        if(i==1 && j == 1) {
                            topRight = r;
                        }
                        break;
                    case 'J':
                        if((i==0 && j==1) || (i==1 && j==1) || (i==1 && j==0))
                            color = Color.DARKBLUE;
                        if(i==1 && j == 1) {
                            bottomRight = r;
                        }
                        break;
                    case 'L':
                        if((i==2 && j==1) || (i==1 && j==1) || (i==1 && j==0))
                            color = Color.DARKBLUE;
                        if(i==1 && j == 1) {
                            bottomLeft = r;
                        }
                        break;
                    case '|':
                        if((i==1 && j==0) || (i==1 && j==1) || (i==1 && j==2))
                            color = Color.DARKBLUE;
                        break;
                    case '-':
                        if((i==0 && j==1) || (i==1 && j==1) || (i==2 && j==1))
                            color = Color.DARKBLUE;
                        break;
                }
                pane.setBackground(new Background(new BackgroundFill(color, new CornerRadii(topLeft, topRight, bottomRight, bottomLeft,true), new Insets(0))));
                add(pane,i,j);

            }
        }

    }
}