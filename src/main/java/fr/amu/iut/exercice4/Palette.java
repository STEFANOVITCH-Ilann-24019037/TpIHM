package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private VBox panneau;
    private HBox bas;
    private Rectangle rectangle;

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();
        label = new Label("Vert: " + nbVert + ", Rouge: " + nbRouge + ", Bleu: " + nbBleu);
        panneau = new VBox(10);
        bas = new HBox(10);
        rectangle = new Rectangle(200, 200, Color.WHITE);
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        vert.setOnAction(e -> {
            incrementerVert();
            mettreAJourCouleur();
        });
        rouge.setOnAction(e -> {
            incrementerRouge();
            mettreAJourCouleur();
        });
        bleu.setOnAction(e -> {
            incrementerBleu();
            mettreAJourCouleur();
        });

        bas.getChildren().addAll(vert, rouge, bleu);
        bas.setPadding(new Insets(10));

        panneau.getChildren().add(rectangle);
        panneau.setPadding(new Insets(10));

        root.setTop(label);
        root.setCenter(panneau);
        root.setBottom(bas);

        // Configuration de la scène
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Palette de Couleurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void incrementerVert() {
        nbVert++;
        mettreAJourLabel();
    }

    private void incrementerRouge() {
        nbRouge++;
        mettreAJourLabel();
    }

    private void incrementerBleu() {
        nbBleu++;
        mettreAJourLabel();
    }

    private void mettreAJourLabel() {
        label.setText("Vert: " + nbVert + ", Rouge: " + nbRouge + ", Bleu: " + nbBleu);
    }

    private void mettreAJourCouleur() {
        double r = Math.min(nbRouge / 10.0, 1.0);
        double g = Math.min(nbVert / 10.0, 1.0);
        double b = Math.min(nbBleu / 10.0, 1.0);
        rectangle.setFill(Color.color(r, g, b));
    }

}
