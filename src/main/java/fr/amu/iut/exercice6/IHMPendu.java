package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IHMPendu extends Application {

    private Dico dico;
    private String motADeviner;
    private StringBuilder motAffiche;
    private int tentativesRestantes;
    private Label labelMot;
    private Label labelTentatives;
    private Label labelTentativesFausses;
    private TextField champLettre;
    private Button boutonRecommencer;
    private StringBuilder tentativesFausses;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);

        labelMot = new Label();
        labelTentatives = new Label();
        labelTentativesFausses = new Label();
        champLettre = new TextField();
        boutonRecommencer = new Button("Recommencer");

        initialiserJeu();

        boutonRecommencer.setOnAction(e -> recommencerJeu());

        champLettre.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                devinerMotOuLettre();
            }
        });

        VBox layout = new VBox(10, labelMot, labelTentatives, champLettre, boutonRecommencer, labelTentativesFausses);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initialiserJeu() {
        dico = new Dico();
        motADeviner = dico.getMot();
        motAffiche = new StringBuilder("_ ".repeat(motADeviner.length()));
        tentativesRestantes = 6;
        tentativesFausses = new StringBuilder();

        labelMot.setText(motAffiche.toString());
        labelTentatives.setText("Tentatives restantes: " + tentativesRestantes);
        labelTentativesFausses.setText("Tentatives fausses: ");
        champLettre.clear();
        champLettre.setDisable(false);
    }

    private void devinerMotOuLettre() {
        String entree = champLettre.getText().toLowerCase();

        if (entree.length() == 1) {
            devinerLettre(entree.charAt(0));
        } else {
            devinerMot(entree);
        }

        champLettre.clear();
    }

    private void devinerLettre(char caractere) {
        boolean lettreTrouvee = false;

        for (int i : dico.getPositions(caractere, motADeviner)) {
            motAffiche.setCharAt(i, caractere);
            lettreTrouvee = true;
        }

        if (!lettreTrouvee) {
            tentativesRestantes--;
            tentativesFausses.append(caractere).append(" ");
            labelTentativesFausses.setText("Tentatives fausses: " + tentativesFausses.toString());
        }

        labelMot.setText(motAffiche.toString());
        labelTentatives.setText("Tentatives restantes: " + tentativesRestantes);

        if (motAffiche.toString().equals(motADeviner)) {
            labelMot.setText("Félicitations! Vous avez gagné!");
            desactiverChamps();
        } else if (tentativesRestantes == 0) {
            labelMot.setText("Vous avez perdu! Le mot était: " + motADeviner);
            desactiverChamps();
        }
    }

    private void devinerMot(String mot) {
        if (mot.equals(motADeviner)) {
            labelMot.setText("Félicitations! Vous avez gagné!");
            desactiverChamps();
        } else {
            tentativesRestantes--;
            tentativesFausses.append(mot).append(" ");
            labelTentativesFausses.setText("Tentatives fausses: " + tentativesFausses.toString());
            labelTentatives.setText("Tentatives restantes: " + tentativesRestantes);
            if (tentativesRestantes == 0) {
                labelMot.setText("Vous avez perdu! Le mot était: " + motADeviner);
                desactiverChamps();
            }
        }
    }

    private void recommencerJeu() {
        initialiserJeu();
    }

    private void desactiverChamps() {
        champLettre.setDisable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
