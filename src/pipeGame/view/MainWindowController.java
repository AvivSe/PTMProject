package pipeGame.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pipeGame.view.LevelDisplayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainWindowController implements Initializable {
    @FXML
    LevelDisplayer levelDisplayer;

    public void openConfigurationWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConfigurationWindow.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Pipe Game - Configuration");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ignored) {}
    }

    public void openFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open pipe game file (.pg)");
        fileChooser.setInitialDirectory(new File("./"));
        File chosen = fileChooser.showOpenDialog(null);
        if(chosen != null) {
            System.out.println(chosen.getName());
            ArrayList<ArrayList<Character>> newData = new ArrayList<>();

            Scanner scanner = new Scanner(chosen);
            while(scanner.hasNext()) {
                ArrayList<Character> row = new ArrayList<>();
                newData.add(row);
                for(char c: scanner.nextLine().toCharArray()) {
                    row.add(c);
                }
            }

            char result[][] = new char[newData.size()][newData.get(0).size()];
            for(int i = 0; i < result.length; i++) {
                for(int j=0; j < result[0].length; j++) {
                    result[i][j] = newData.get(i).get(j);
                }
            }
//
//            setLevel(result);
//            changeStatus("Loaded from file", "purple");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        levelDisplayer.setData(new char[][] {
//                {'s','F','L',' '},
//                {'F','7','L','J'},
//                {'L','J','F','L'},
//                {'J','L','7','J'},
//                {'7','F','L','g'}});
    }
}
