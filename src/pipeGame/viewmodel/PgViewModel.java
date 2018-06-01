package pipeGame.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;
import pipeGame.model.PgModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class PgViewModel implements Observer, ViewModel {
    public StringProperty[][] data;
    public PgModel model;
    public StringProperty address;

    public PgViewModel() {
        this.model = new PgModel();

        // Default level:
        this.setLevel(new char[][] {
                {'s','F','L',' '},
                {'F','7','L','J'},
                {'L','J','F','L'},
                {'J','L','7','J'},
                {'7','F','L','g'}});
    }

    public void setLevel(char[][] level){
        this.data = new StringProperty[level.length][level[0].length];

        for(int i = 0; i < level.length; i++) {
            for( int j = 0; j < level[i].length; j++) {
                this.data[i][j] = new SimpleStringProperty(String.valueOf(level[i][j]));
            }
        }
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open pipe game file (.pg)");
        fileChooser.setInitialDirectory(new File("./"));
        File chosen = fileChooser.showOpenDialog(null);
        if (chosen != null) {
            System.out.println(chosen.getName());
            ArrayList<ArrayList<Character>> newData = new ArrayList<>();

            Scanner scanner = null;
            try {
                scanner = new Scanner(chosen);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (scanner.hasNext()) {
                ArrayList<Character> row = new ArrayList<>();
                newData.add(row);
                for (char c : scanner.nextLine().toCharArray()) {
                    row.add(c);
                }
            }

            char result[][] = new char[newData.size()][newData.get(0).size()];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    result[i][j] = newData.get(i).get(j);
                }
            }

            this.setLevel(result);
        }
    }

    public void rotate(int row,int col,int times){
        String c = data[row][col].get();
        for(int i = 0; i < times; i++) {
            switch(data[row][col].get()) {
                case "F":
                    c = "7";
                    break;
                case "7":
                    c = "J";
                    break;
                case "J":
                    c = "L";
                    break;
                case "L":
                    c = "F";
                    break;
                case "|":
                    c = "-";
                    break;
                case "-":
                    c = "|";
                    break;

            }
            data[row][col].setValue(c);
        }
    }


    public void solve() {
        StringBuilder stringBuilder = new StringBuilder();

        for(StringProperty[] line: data) {
            for (StringProperty c : line) {
                stringBuilder.append(c.get());
            }
            stringBuilder.append("\n");
        }

        model.solve(stringBuilder.toString());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
