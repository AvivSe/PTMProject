package pipeGame.viewmodel;

import javafx.beans.property.StringProperty;

import java.util.Observable;
import java.util.Observer;

public class PgViewModel implements Observer, ViewModel {
    public StringProperty[][] data;

    @Override
    public void update(Observable o, Object arg) {

    }
}
