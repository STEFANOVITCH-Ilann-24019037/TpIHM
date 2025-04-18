package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginControl {

    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView backgroundImage;

    private TextField tempPwdField;
    Image image;

    @FXML
    private void initialize() {
        // Charger l'image en arrière-plan
        image= chargerImage("/exercice8/background.jpg");
        backgroundImage.setImage(image);
    }

    @FXML
    private void okClicked() {
        // Afficher le mot de passe en clair
        if (tempPwdField == null) {
            tempPwdField = new TextField();
            tempPwdField.setText(pwd.getText());
            GridPane.setConstraints(tempPwdField, 1, 2);
        }
        gridPane.getChildren().remove(pwd);
        gridPane.getChildren().add(tempPwdField);
    }

    @FXML
    private void cancelClicked() {
        // Masquer le mot de passe
        if (tempPwdField != null) {
            pwd.setText(tempPwdField.getText());
            gridPane.getChildren().remove(tempPwdField);
            gridPane.getChildren().add(pwd);
            GridPane.setConstraints(pwd, 1, 2);
        }
    }

    @FXML
    private void loginAction() {
        String user = userId.getText();
        String password = pwd.getText();

        // Vérification simple des identifiants (à remplacer par une vérification sécurisée)
        if ("admin".equals(user) && "admin".equals(password)) {
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome " + user + "!");
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Image chargerImage(String chemin) {
        try {
            return new Image(getClass().getResource(chemin).toExternalForm());
        } catch (Exception e) {
            System.err.println("Impossible de charger l'image : " + chemin);
            return null;
        }
    }
}