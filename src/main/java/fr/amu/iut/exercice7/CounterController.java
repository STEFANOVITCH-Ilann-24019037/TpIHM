package fr.amu.iut.exercice7;

import javafx.fxml.FXML;
import javafx.scene.control.Label; // Assurez-vous d'importer cette classe

public class CounterController {

    @FXML
    private Label counterLabel; // Utilisez javafx.scene.control.Label

    private int counter = 0;

    @FXML
    public void increment() {
        counter++;
        counterLabel.setText(String.valueOf(counter));
    }

    @FXML
    public void decrement() {
        counter--;
        counterLabel.setText(String.valueOf(counter));
    }
}
