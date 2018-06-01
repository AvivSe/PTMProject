package pipeGame.view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pipeGame.viewmodel.PgViewModel;

public class LevelDisplayer extends GridPane {
    public StringProperty[][] data;
    PgViewModel viewModel;

    public void bindData() {
        this.data = new StringProperty[viewModel.data.length][viewModel.data[0].length];

        // Bind data with View-Model
        for(int i = 0; i < viewModel.data.length; i++) {
            for(int j = 0; j < viewModel.data[i].length; j++) {
                this.data[i][j] = new SimpleStringProperty();
                this.data[i][j].addListener((obs,old,nwv)->Platform.runLater(this::reDraw));
                this.data[i][j].bind(viewModel.data[i][j]);
            }
        }
    }

    public void reDraw() {
        if(data == null)
            return;

        getChildren().clear();
        setBackground(new Background(
                new BackgroundFill(Color.GAINSBORO, new CornerRadii(0), new Insets(0))));

        for(int i = 0; i < data.length; i++) {
            addRow(i);
            for(int j = 0; j < data[i].length; j++) {
                addColumn(j);
                Node grid = new Grid(data[i][j].get());
                int finalJ = j;
                int finalI = i;
                grid.setOnMouseClicked(e->viewModel.rotate(finalI, finalJ,1));
                add(grid,j,i);
            }
        }
    }





}
