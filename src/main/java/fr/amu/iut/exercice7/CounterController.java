package fr.amu.iut.exercice7;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CounterController implements Initializable {

    int counter = 0;
    @FXML
    private Label counterLabel;

    @FXML
    public void increment() {
        counter ++;
        counterLabel.setText(String.valueOf(counter));
    }
    @FXML
    public void decrement() {
        counter--;
        counterLabel.setText(String.valueOf(counter));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing CounterController...");
    }
}