package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création des panneaux pour Celsius et Fahrenheit
        VBox panneauCelsius = new VBox(10);
        VBox panneauFahrenheit = new VBox(10);

        // Création des éléments pour le panneau Celsius
        Label labelCelsius = new Label("Celsius:");
        TextField textFieldCelsius = new TextField();
        Button boutonVersFahrenheit = new Button("Convertir en Fahrenheit");
        Label resultatFahrenheit = new Label();

        // Création des éléments pour le panneau Fahrenheit
        Label labelFahrenheit = new Label("Fahrenheit:");
        TextField textFieldFahrenheit = new TextField();
        Button boutonVersCelsius = new Button("Convertir en Celsius");
        Label resultatCelsius = new Label();

        // Ajout des éléments aux panneaux
        panneauCelsius.getChildren().addAll(labelCelsius, textFieldCelsius, boutonVersFahrenheit, resultatFahrenheit);
        panneauFahrenheit.getChildren().addAll(labelFahrenheit, textFieldFahrenheit, boutonVersCelsius, resultatCelsius);

        // Configuration des actions des boutons
        boutonVersFahrenheit.setOnAction(e -> {
            try {
                String celsiusText = textFieldCelsius.getText().replace(',', '.');
                double celsius = Double.parseDouble(celsiusText);
                double fahrenheit = celsiusToFahrenheit(celsius);
                resultatFahrenheit.setText(String.format("%.2f Fahrenheit", fahrenheit));
            } catch (NumberFormatException ex) {
                resultatFahrenheit.setText("Veuillez entrer un nombre valide.");
            }
        });

        boutonVersCelsius.setOnAction(e -> {
            try {
                String fahrenheitText = textFieldFahrenheit.getText().replace(',', '.');
                double fahrenheit = Double.parseDouble(fahrenheitText);
                double celsius = fahrenheitToCelsius(fahrenheit);
                resultatCelsius.setText(String.format("%.2f Celsius", celsius));
            } catch (NumberFormatException ex) {
                resultatCelsius.setText("Veuillez entrer un nombre valide.");
            }
        });

        // Configuration de la disposition principale
        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        // Configuration de la scène et de la fenêtre principale
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Convertisseur de Températures");
        primaryStage.show();
    }

    // Méthode de conversion de Celsius en Fahrenheit
    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    // Méthode de conversion de Fahrenheit en Celsius
    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static void main(String[] args) {
        launch(args);
    }
}