package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    private ImageView imageView;
    private Image perdu7;
    private Image perdu6;
    private Image perdu5;
    private Image perdu4;
    private Image perdu3;
    private Image perdu2;
    private Image perdu1;
    private Image perdu0;
    private Image gagne;

    @Override
    public void start(Stage primaryStage) throws Exception {
        perdu7 = chargerImage("/exercice6/pendu7.png");
        perdu6 = chargerImage("/exercice6/pendu6.png");
        perdu5 = chargerImage("/exercice6/pendu5.png");
        perdu4 = chargerImage("/exercice6/pendu4.png");
        perdu3 = chargerImage("/exercice6/pendu3.png");
        perdu2 = chargerImage("/exercice6/pendu2.png");
        perdu1 = chargerImage("/exercice6/pendu1.png");
        perdu0 = chargerImage("/exercice6/pendu0.png");
        gagne = chargerImage("/exercice6/penduWin.png");

        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(650); // Augmenter la hauteur pour inclure l'image

        labelMot = new Label();
        labelTentatives = new Label();
        labelTentativesFausses = new Label();
        champLettre = new TextField();
        boutonRecommencer = new Button("Recommencer");
        imageView = new ImageView();

        initialiserJeu();

        boutonRecommencer.setOnAction(e -> recommencerJeu());

        champLettre.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                devinerMotOuLettre();
            }
        });

        VBox layout = new VBox(10, labelMot, labelTentatives, champLettre, boutonRecommencer, labelTentativesFausses, imageView);
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
        tentativesRestantes = 7; // Commencer avec 7 tentatives pour correspondre aux images
        tentativesFausses = new StringBuilder();

        labelMot.setText(motAffiche.toString());
        labelTentatives.setText("Tentatives restantes: " + tentativesRestantes);
        labelTentativesFausses.setText("Tentatives fausses: ");
        champLettre.clear();
        champLettre.setDisable(false);
        imageView.setImage(perdu7); // Afficher l'image initiale
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
            motAffiche.setCharAt(i * 2, caractere); // Mettre à jour les positions paires
            lettreTrouvee = true;
        }

        if (!lettreTrouvee) {
            tentativesRestantes--;
            tentativesFausses.append(caractere).append(" ");
            labelTentativesFausses.setText("Tentatives fausses: " + tentativesFausses.toString());
            mettreAJourImage();
        }

        labelMot.setText(motAffiche.toString());
        labelTentatives.setText("Tentatives restantes: " + tentativesRestantes);

        if (motAffiche.toString().replace(" ", "").equals(motADeviner)) {
            labelMot.setText("Félicitations! Vous avez gagné!");
            imageView.setImage(gagne);
            desactiverChamps();
        } else if (tentativesRestantes == 0) {
            labelMot.setText("Vous avez perdu! Le mot était: " + motADeviner);
            desactiverChamps();
        }
    }

    private void devinerMot(String mot) {
        if (mot.equals(motADeviner)) {
            labelMot.setText("Félicitations! Vous avez gagné!");
            imageView.setImage(gagne);
            desactiverChamps();
        } else {
            tentativesRestantes--;
            tentativesFausses.append(mot).append(" ");
            labelTentativesFausses.setText("Tentatives fausses: " + tentativesFausses.toString());
            labelTentatives.setText("Tentatives restantes: " + tentativesRestantes);
            mettreAJourImage();
            if (tentativesRestantes == 0) {
                labelMot.setText("Vous avez perdu! Le mot était: " + motADeviner);
                desactiverChamps();
            }
        }
    }

    private void mettreAJourImage() {
        switch (tentativesRestantes) {
            case 6:
                imageView.setImage(perdu6);
                break;
            case 5:
                imageView.setImage(perdu5);
                break;
            case 4:
                imageView.setImage(perdu4);
                break;
            case 3:
                imageView.setImage(perdu3);
                break;
            case 2:
                imageView.setImage(perdu2);
                break;
            case 1:
                imageView.setImage(perdu1);
                break;
            case 0:
                imageView.setImage(perdu0);
                break;
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

    private Image chargerImage(String chemin) {
        try {
            return new Image(getClass().getResource(chemin).toExternalForm());
        } catch (Exception e) {
            System.err.println("Impossible de charger l'image : " + chemin);
            return null;
        }
    }
}
