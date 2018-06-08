package pipeGame.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;
import pipeGame.model.PgModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PgViewModel implements ViewModel {
    public StringProperty[][] data;
    public StringProperty timeLeft;
    public StringProperty currentWindow;
    private PgModel model;
    private Timer timer;
    private TimerTask task;
    private int index;

    public PgViewModel() {
        this.model = new PgModel();
        timeLeft = new SimpleStringProperty();
        currentWindow = new SimpleStringProperty("startView");
    }

    public void startGame() {
        this.setLevel(null);
        this.currentWindow.setValue("gameView");
        this.startTimer();
        this.model.win.addListener(e->{
            if(model.win.getValue().equals(true))
                this.winView();
        });
    }

    private void setLevel(char[][] level){
        // Set level if exists
        if(level != null) {
            this.data = new StringProperty[level.length][level[0].length];
            for (int i = 0; i < level.length; i++) {
                for (int j = 0; j < level[i].length; j++) {
                    this.data[i][j] = new SimpleStringProperty(String.valueOf(level[i][j]));
                    this.data[i][j].addListener(e->Platform.runLater(()->model.isGoal(dataToCharArray())));
                }
            }
        }
        // Get Random level from folder if not exists
        else {
            //TODO make path dynamic
            File folder = new File("src/pipeGame/defualts/levels");
            List<File> listOfFiles = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
            fileToData(listOfFiles.get(ThreadLocalRandom.current().nextInt(0,  listOfFiles.size())));
        }
    }

    private void fileToData(File file) {
        if (file != null) {
            System.out.println(file.getName());
            ArrayList<ArrayList<Character>> newData = new ArrayList<>();
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            assert scanner != null;
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

    public void startTimer() {
        stopTimer();
        timer=new Timer();
        //timeLeft.setValue(String.valueOf(model.weight(dataToString())*5));
        timeLeft.setValue("10");
        task=new TimerTask() {
            @Override
            public void run() {
                timeLeft.set(String.valueOf(Integer.valueOf(timeLeft.get())-1));
                if(Integer.valueOf(timeLeft.get())  <= 0) {
                    currentWindow.setValue("overView");
                    stopTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    private void stopTimer() {
        if(timer!=null) {
            model.win.setValue(false);
            timeLeft.setValue("0");
            task.cancel();
            timer.cancel();
        }
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open pipe game file (.pg)");
        fileChooser.setInitialDirectory(new File("./"));
        File chosen = fileChooser.showOpenDialog(null);
        fileToData(chosen);
        this.startView();
    }

    private String dataToString() {
        StringBuilder result = new StringBuilder();
        for(StringProperty[] line: data)
            for (StringProperty s: line)
                result.append(s.getValue());

        return result.toString();
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

    private void startView() {
        this.currentWindow.setValue("startView");
        this.stopTimer();
    }

    private void winView() {
        if(!this.currentWindow.getValue().equals("winView")) {
            this.stopTimer();
            this.currentWindow.setValue("winView");
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

    private char[][] dataToCharArray() {
        int rows = data.length;
        int cols = data[0].length;
        char[][] result = new char[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                result[i][j] = data[i][j].getValue().charAt(0);
            }
        }
        return result;
    }
}