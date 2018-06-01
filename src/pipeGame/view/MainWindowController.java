package pipeGame.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pipeGame.model.PgModel;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        solve.setOnMouseClicked(event -> viewModel.solve());
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