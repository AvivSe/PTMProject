package pipeGame.view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pipeGame.viewmodel.PgViewModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, View {
    PgViewModel viewModel;
    LevelDisplayer levelDisplayer;
    StringProperty currentWindow;

    @FXML
    Button solve;

    @FXML
    Text timer;

    @FXML
    BorderPane borderPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        solve.setOnMouseClicked(event -> {
            try {
                viewModel.solve();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.viewModel = new PgViewModel();

        levelDisplayer = new LevelDisplayer();
        levelDisplayer.viewModel = this.viewModel;
        levelDisplayer.bindData();

        this.currentWindow = new SimpleStringProperty();
        currentWindow.bind(viewModel.currentWindow);

        drawStart();

        currentWindow.addListener(e->drawWindow());
    }

    void drawGameOver() {
        System.out.println("game OVER!");
        Button gameOver = new Button("Game Over!");
        borderPane.setCenter(gameOver);
    }

    void drawStart() {
        Button start = new Button("Start");

        start.setOnMouseClicked(e->viewModel.currentWindow.setValue("levelDisplayer"));
        borderPane.setCenter(start);
    }
    void drawLevelDisplayer() {
        viewModel.startGame();;

        borderPane.setCenter(levelDisplayer);

        Platform.runLater(()-> viewModel.timeLeft.addListener(event->{
            timer.setText(viewModel.timeLeft.getValue());
            if(Integer.valueOf(viewModel.timeLeft.getValue()) <= 0) {
                viewModel.currentWindow.setValue("gameOver");
                timer.setText("Game Over!");
            }
        }));
    }

    void drawWindow() {
        switch (currentWindow.get()) {
            case "start":
                drawStart();
                break;
            case "levelDisplayer":
                drawLevelDisplayer();
                break;
            case "gameOver":
                drawGameOver();
                break;
                default:
                    break;
        }
    }
    public void openConfigurationWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConfigurationWindow.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Pipe Game - Configuration");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ignored) {}
    }

    public void openFile(){
        viewModel.openFile();
        levelDisplayer.bindData();
//            changeStatus("Loaded from file", "purple");
    }
}
