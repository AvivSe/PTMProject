package pipeGame.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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

    @FXML
    Button viewMode;

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
            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.println("test");
            out.flush();
            out.close();
            s.close();
        } catch (IOException e) {
            changeStatus(address.getText() + " is NOT available.", "red");
        }

    }

    public void setRandomLevel() {
        changeStatus("Generating level", "blue");

        // TODO return levels from files
        ArrayList<char[][]> list = new ArrayList<>();

        list.add(new char[][]{
                {'s', 'F', 'L'},
                {'F', '7', 'L'},
                {'L', 'J', 'F'},
                {'7', 'F', 'g'}
        });

        list.add(new char[][]{
            {'g','F','L',' '},
            {'F','7','L','J'},
            {'L','J','F','L'},
            {'J','L','7','J'},
            {'7','F','L','s'}
        });

        list.add(new char[][]{
                {'F','7','L','J'},
                {'L','s','F','L'},
                {'J','L','7','J'},
                {'7','F','L','g'}
        });

        list.add(new char[][]{
                {'s','|','g'}
        });

        list.add(new char[][]{
                {'s','L','L','L'},
                {'L','L','L','L'},
                {'L','L','L','L'},
                {'L','L','L','g'}
        });

        list.add(new char[][]{
                {'s','|'},
                {'L','L'},
                {'7','g'}
        });

        Collections.shuffle(list);

        setLevel(list.get(0));
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

    public void setViewMode() {
        if(viewMode.getText().toLowerCase().equals("visual")) {
            viewMode.setText("Textual");
        } else {
            viewMode.setText("Visual");
        }
        levelDisplayer.changeViewMode();
        changeStatus("View mode changed to - "+ viewMode.getText(), "blue");
    }

    public int getWeight() {
       int result = 0;
       for(char[] row: data)
           for(char c: row)
               if(c == 'L' || c == 'F' || c == '7' || c == 'J' || c == '|' || c == '-')
                   result++;

       return result;
    }

    public void solve() throws IOException, InterruptedException {
        changeStatus("Waiting for answer...", "blue");

        Socket s = new Socket(getIp(), getPort());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());

        out.println(getWeight());
        out.flush();

        StringBuilder stringBuilder = new StringBuilder();

        for(char[] line: data) {
            for (char c : line) {
                stringBuilder.append(c);
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("done");
        out.println(stringBuilder.toString());
        out.flush();

        String answer = in.readLine();
        ArrayList<String> solution = new ArrayList<>();

        while(!answer.equals("done")) {
            //System.out.println(answer);
            solution.add(answer);
            answer = in.readLine();
        }

        levelDisplayer.viewSolution(solution);

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
