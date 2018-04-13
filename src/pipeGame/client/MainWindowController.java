package pipeGame.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainWindowController implements Initializable{

    private char[][] data = {
            {'s','F','L',' '},
            {'F','7','L','J'},
            {'L','J','F','L'},
            {'J','L','7','J'},
            {'7','F','L','g'}
    };

    @FXML
    LevelDisplayer levelDisplayer;

    @FXML
    Label status;

    @FXML
    TextField address;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        levelDisplayer.setData(data);
    }

    public void test() {
        System.out.println("button test is clicked");
        status.setText("testing if "+address.getText() + " is available.");

        try {
            Socket s = new Socket(getIp(),getPort());
            changeStatus(address.getText() + " is available.", "green");

            s.close();
        } catch (IOException e) {
            changeStatus(address.getText() + " is NOT available.", "red");

        }

    }

    public void setRandomLevel() {
        changeStatus("Generating level", "blue");

        char newData[][] = new char[][]{
                {'s', 'F', 'L'},
                {'F', '7', 'L'},
                {'L', 'J', 'F'},
                {'7', 'F', 'g'}
        };

        setLevel(newData);
    }

    public void setLevel(char[][] data) {
        this.data = data;
        levelDisplayer.setData(data);
    }

    public String getIp() {
        return address.getText().split(":")[0];
    }

    public int getPort() {
        return Integer.valueOf(address.getText().split(":")[1]);
    }

    public void changeStatus(String str, String color) {
        status.setStyle("-fx-text-fill: "+color+";");
        status.setText(str);
    }

    public void solve() throws IOException {
        changeStatus("You ask for solution.", "blue");

        Socket s = new Socket(getIp(), getPort());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());
//        int size = data.length*data[0].length;
//        out.println(size);
//        out.flush();

        StringBuilder stringBuilder = new StringBuilder();

        for(char[] line: data) {
            for(int i = 0; i < line.length; i++) {
                stringBuilder.append(line[i]);
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("done");
        out.println(stringBuilder.toString());
        out.flush();

        String answer = in.readLine();

        System.out.println(answer);
        while(!answer.equals("done")) {
            System.out.println(answer);
            levelDisplayer.rotate(Character.getNumericValue(answer.charAt(0)),Character.getNumericValue(answer.charAt(2)),Character.getNumericValue(answer.charAt(4)));
            answer = in.readLine();
        }

        in.close();
        out.close();
        s.close();

        changeStatus("You got an answer.", "green");
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

            setLevel(result);
            changeStatus("Loaded from file", "purple");
        }
    }

}
