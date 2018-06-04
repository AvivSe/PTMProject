package pipeGame.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;
import pipeGame.model.PgModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class PgViewModel implements ViewModel {
    public StringProperty[][] data;
    public PgModel model;
    public StringProperty timeLeft;
    public Timer timer;
    public TimerTask task;
    public StringProperty currentWindow;

    int index;

    public PgViewModel() {
        this.model = new PgModel();
        currentWindow = new SimpleStringProperty();

        // TODO: replace this hard-coded default level
        this.setLevel(new char[][] {
                {'s','F','L',' '},
                {'F','7','L','J'},
                {'L','J','F','L'},
                {'J','L','7','J'},
                {'7','F','L','g'}});

        currentWindow.setValue("start");
    }

    public void startGame() {
        startTimer();
    }

    public void setLevel(char[][] level){
        this.data = new StringProperty[level.length][level[0].length];

        for(int i = 0; i < level.length; i++) {
            for( int j = 0; j < level[i].length; j++) {
                this.data[i][j] = new SimpleStringProperty(String.valueOf(level[i][j]));
            }
        }
    }

    public void startTimer() {
        stopTimer();
        timer=new Timer();
        timeLeft = new SimpleStringProperty("5");
        task=new TimerTask() {
            @Override
            public void run() {
                timeLeft.set(String.valueOf(Integer.valueOf(timeLeft.get())-1));
                if(Integer.valueOf(timeLeft.get())  <= 0) {
                    stopTimer();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stopTimer() {
        if(timer!=null) {
            System.out.println("cacling");
            task.cancel();
            timer.cancel();
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


    public void solve() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        for(StringProperty[] line: data) {
            for (StringProperty c : line) {
                stringBuilder.append(c.get());
            }
            stringBuilder.append("\n");
        }

        ArrayList<String> solution = model.solve(stringBuilder.toString());
        index = 0;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(index < solution.size()) {
                    String[] vector = solution.get(index).split(",");
                    int i = Integer.valueOf(vector[0]);
                    int j = Integer.valueOf(vector[1]);
                    int times = Integer.valueOf(vector[2]);
                    rotate(i,j,times);
                    index++;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 100);
    }

}
