package fr.amu.iut.exercice15;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private void initialize() {
        createBindings();
    }


    private void createBindings() {

        okBtn.disableProperty().bind(
                Bindings.isEmpty(userId.textProperty()).or(Bindings.isEmpty(pwd.textProperty()))
        );
        cancelBtn.disableProperty().bind(
                Bindings.isEmpty(userId.textProperty()).and(Bindings.isEmpty(pwd.textProperty()))
        );
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        System.out.print(pwd.getText());
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}