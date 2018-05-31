package pipeGame.view;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;



public class LevelDisplayer extends GridPane {
//    public StringProperty[][] data;
//
//
//    public void setData(char[][] data) {
//
//        setHgap(0);
//        setVgap(0);
//
//        this.data = new StringProperty[data.length][data[0].length];
//
//        for(int i = 0; i < data.length; i++) {
//            for(int j = 0; j < data[i].length; j++) {
//                this.data[i][j].setValue(String.valueOf(data[i][j]));
//            }
//        }
//        reDraw();
//    }
//
//    public void reDraw() {
//        if(data == null)
//            return;
//
//        getChildren().clear();
//        setBackground(new Background(new BackgroundFill(Color.GAINSBORO, new CornerRadii(0), new Insets(0))));
//
//        for(int i = 0; i < data.length; i++) {
//            addRow(i);
//            for(int j = 0; j < data[i].length; j++) {
//                addColumn(j);
//                Node grid = new Grid(data[i][j].get());
//                int finalJ = j;
//                int finalI = i;
//                grid.setOnMouseClicked(e->rotate(finalI, finalJ,1));
//                add(grid,j,i);
//            }
//        }
//    }
//
//    public void rotate(int row,int col,int times){
//        String c = data[row][col].get();
//        for(int i = 0; i < times; i++) {
//            switch(data[row][col].get()) {
//                case "F":
//                    c = "7";
//                    break;
//                case "7":
//                    c = "J";
//                    break;
//                case "J":
//                    c = "L";
//                    break;
//                case 'L':
//                    c = "F";
//                    break;
//                case "|":
//                    c = "-";
//                    break;
//                case "-":
//                    c = "|";
//                    break;
//
//            }
//            data[row][col].setValue(c);
//        }
//        reDraw();
//    }



}
