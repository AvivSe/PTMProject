package pipeGame.view;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

class Grid extends Button {
    Grid(String str) {
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