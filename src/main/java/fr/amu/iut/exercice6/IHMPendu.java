package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Random;

public class IHMPendu extends Application {

    private String motADeviner;
    private char[] motCache;
    private int nombreVies = 7;

    private ImageView imagePendu;
    private Label labelVies;
    private Label labelMotCache;
    private GridPane clavier;
    private Dico dico = new Dico();
    private Button boutonRejouer;

    @Override
    public void start(Stage primaryStage) {
        motADeviner = dico.getMot();
        motCache = new char[motADeviner.length()];
        Arrays.fill(motCache, '*');

        imagePendu = new ImageView(new Image(getClass().getResourceAsStream("/exercice6/pendu7.png")));
        labelVies = new Label("Nombre de vies : " + nombreVies);
        labelMotCache = new Label(String.valueOf(motCache));
        clavier = creerClavier();

        boutonRejouer = new Button("Rejouer");
        boutonRejouer.setOnAction(event -> demarrerNouvellePartie());
        HBox rejouerBox = new HBox(boutonRejouer); // Créer un HBox pour centrer le bouton
        rejouerBox.setAlignment(Pos.CENTER);
        rejouerBox.setPadding(new Insets(10)); // Ajouter un peu de marge autour du bouton

        BorderPane root = new BorderPane();
        root.setTop(new HBox(10, imagePendu, labelVies));
        BorderPane.setAlignment(root.getTop(), Pos.CENTER);
        BorderPane.setMargin(root.getTop(), new Insets(10));

        root.setCenter(labelMotCache);
        BorderPane.setAlignment(root.getCenter(), Pos.CENTER);
        BorderPane.setMargin(root.getCenter(), new Insets(20));

        root.setBottom(clavier); // Le clavier reste en bas (partie "bottom")
        BorderPane.setAlignment(root.getBottom(), Pos.CENTER);
        BorderPane.setMargin(root.getBottom(), new Insets(10));

        root.setBottom(new VBox(clavier, rejouerBox)); // Les placer verticalement dans la partie "bottom"
        BorderPane.setAlignment(root.getBottom(), Pos.CENTER);

        Scene scene = new Scene(root, 400, 500); // Augmenter légèrement la hauteur

        scene.setOnKeyPressed(event -> {
            String lettre = event.getText();
            if (lettre.length() == 1 && Character.isLetter(lettre.charAt(0))) {
                char lettreChoisie = Character.toLowerCase(lettre.charAt(0));
                for (javafx.scene.Node node : clavier.getChildren()) {
                    if (node instanceof Button && ((Button) node).getText().equalsIgnoreCase(String.valueOf(lettreChoisie)) && !node.isDisabled()) {
                        gererLettreCliquee(lettreChoisie);
                        break;
                    }
                }
            }
        });

        primaryStage.setTitle("Jeu du Pendu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void demarrerNouvellePartie() {
        motADeviner = dico.getMot();
        motCache = new char[motADeviner.length()];
        Arrays.fill(motCache, '*');
        nombreVies = 7;
        labelVies.setText("Nombre de vies : " + nombreVies);
        labelMotCache.setText(String.valueOf(motCache));
        imagePendu.setImage(new Image(getClass().getResourceAsStream("/exercice6/pendu7.png")));

        // Réactiver tous les boutons du clavier
        for (javafx.scene.Node node : clavier.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(false);
            }
        }
        boutonRejouer.setDisable(false); // Réactiver le bouton Rejouer si nécessaire
    }

    private GridPane creerClavier() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int row = 0;
        int col = 0;

        for (char lettre : alphabet.toCharArray()) {
            Button boutonLettre = new Button(String.valueOf(lettre).toUpperCase());
            boutonLettre.setOnAction(event -> gererLettreCliquee(lettre));
            gridPane.add(boutonLettre, col, row);
            col++;
            if (col > 6) { // Organiser les lettres sur plusieurs lignes
                col = 0;
                row++;
            }
        }
        return gridPane;
    }

    private void gererLettreCliquee(char lettre) {
        boolean lettreTrouvee = false;
        for (int i = 0; i < motADeviner.length(); i++) {
            if (motADeviner.charAt(i) == lettre) {
                motCache[i] = lettre;
                lettreTrouvee = true;
            }
        }

        labelMotCache.setText(String.valueOf(motCache));

        if (!lettreTrouvee) {
            nombreVies--;
            labelVies.setText("Nombre de vies : " + nombreVies);
            // Mettre à jour l'image du pendu en fonction de nombreVies
            int imageIndex = 7 - nombreVies;
            if (imageIndex >= 0 && imageIndex <= 7) {
                imagePendu.setImage(new Image(getClass().getResourceAsStream("/exercice6/pendu" + imageIndex + ".png")));
            }
        }

        if (String.valueOf(motCache).equals(motADeviner)) {
            labelMotCache.setText("Vous avez gagné ! Le mot était : " + motADeviner);
            desactiverClavier();
            boutonRejouer.setDisable(false); // Activer le bouton Rejouer
        } else if (nombreVies == 0) {
            labelMotCache.setText("Vous avez perdu ! Le mot était : " + motADeviner);
            imagePendu.setImage(new Image(getClass().getResourceAsStream("/exercice6/pendu0.png"))); // Image de la défaite
            desactiverClavier();
            boutonRejouer.setDisable(false); // Activer le bouton Rejouer
        }

        // Désactiver le bouton de la lettre cliquée
        for (javafx.scene.Node node : clavier.getChildren()) {
            if (node instanceof Button && ((Button) node).getText().equalsIgnoreCase(String.valueOf(lettre))) {
                node.setDisable(true);
                break;
            }
        }
    }

    private void desactiverClavier() {
        for (javafx.scene.Node node : clavier.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}