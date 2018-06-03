package pipeGame.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pipeGame.viewmodel.PgViewModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, View {
    PgViewModel viewModel;

    @FXML
    LevelDisplayer levelDisplayer;

    @FXML
    Button solve;

    @FXML
    Text timer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(()-> viewModel.timeLeft.addListener(event->{

            timer.setText(viewModel.timeLeft.getValue());
            if(Integer.valueOf(viewModel.timeLeft.getValue()) <= 0) {
                timer.setText("Game Over!");
            }
        }));

        solve.setOnMouseClicked(event -> {
            try {
                viewModel.solve();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.viewModel = new PgViewModel();

        levelDisplayer.viewModel = this.viewModel;
        levelDisplayer.bindData();
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