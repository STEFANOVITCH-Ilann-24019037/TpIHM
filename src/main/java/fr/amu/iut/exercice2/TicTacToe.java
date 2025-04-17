package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private Button[][] boutons = new Button[3][3];
    private boolean tourJoueurX = true;
    private Image imageX;
    private Image imageO;
    private Image imageVide;

    @Override
    public void start(Stage fenetrePrincipale) {
        imageX = chargerImage("/exercice2/Croix.png");
        imageO = chargerImage("/exercice2/Rond.png");
        imageVide = chargerImage("/exercice2/Vide.png");


        GridPane grille = new GridPane();

        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                Button bouton = new Button();
                bouton.setPrefSize(100, 100);
                bouton.setGraphic(new ImageView(imageVide));
                bouton.setOnAction(event -> surClicBouton(bouton));
                boutons[ligne][colonne] = bouton;
                grille.add(bouton, colonne, ligne);
            }
        }

        Scene scene = new Scene(grille);
        fenetrePrincipale.setTitle("Tic Tac Toe");
        fenetrePrincipale.setScene(scene);
        fenetrePrincipale.show();
    }

    private Image chargerImage(String chemin) {
        try {
            return new Image(getClass().getResource(chemin).toExternalForm());
        } catch (Exception e) {
            System.err.println("Impossible de charger l'image : " + chemin);
            return null;
        }
    }

    private void surClicBouton(Button bouton) {

        ImageView imageActuelle = (ImageView) bouton.getGraphic();

        if (imageActuelle.getImage().equals(imageVide)) {
            if (tourJoueurX) {
                bouton.setGraphic(new ImageView(imageX));
            } else {
                bouton.setGraphic(new ImageView(imageO));
            }
            tourJoueurX = !tourJoueurX;

            if (verifierVictoire()) {
                afficherAlerte("Le joueur " + (tourJoueurX ? "O" : "X") + " a gagné !");
                reinitialiserJeu();
            } else if (plateauPlein()) {
                afficherAlerte("Match nul !");
                reinitialiserJeu();
            }
        }
    }

    private boolean verifierVictoire() {
        ImageView[][] plateau = new ImageView[3][3];

        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                plateau[ligne][colonne] = (ImageView) boutons[ligne][colonne].getGraphic();
            }
        }

        for (int ligne = 0; ligne < 3; ligne++) {
            if (plateau[ligne][0].getImage().equals(plateau[ligne][1].getImage()) &&
                    plateau[ligne][1].getImage().equals(plateau[ligne][2].getImage()) &&
                    !plateau[ligne][0].getImage().equals(imageVide)) {
                return true;
            }
        }
        for (int colonne = 0; colonne < 3; colonne++) {
            if (plateau[0][colonne].getImage().equals(plateau[1][colonne].getImage()) &&
                    plateau[1][colonne].getImage().equals(plateau[2][colonne].getImage()) &&
                    !plateau[0][colonne].getImage().equals(imageVide)) {
                return true;
            }
        }

        // Vérification de la diagonale principale (de gauche à droite)
        if (plateau[0][0].getImage().equals(plateau[1][1].getImage()) &&
                plateau[1][1].getImage().equals(plateau[2][2].getImage()) &&
                !plateau[0][0].getImage().equals(imageVide)) {
            return true;
        }

        // Vérification de la diagonale secondaire (de droite à gauche)
        if (plateau[0][2].getImage().equals(plateau[1][1].getImage()) &&
                plateau[1][1].getImage().equals(plateau[2][0].getImage()) &&
                !plateau[0][2].getImage().equals(imageVide)) {
            return true;
        }

        return false;
    }

    private boolean plateauPlein() {
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                if (((ImageView) boutons[ligne][colonne].getGraphic()).getImage().equals(imageVide)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void afficherAlerte(String message) {
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Fin de la partie");
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }

    private void reinitialiserJeu() {
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                boutons[ligne][colonne].setGraphic(new ImageView(imageVide));
            }
        }
        tourJoueurX = true;
    }

}
