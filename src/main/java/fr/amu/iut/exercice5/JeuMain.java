package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    private int Partieconte;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        // Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();
        // On positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(20 * 10);
        // Panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        root.setCenter(jeu);
        // On construit une scène 640 * 480 pixels
        scene = new Scene(root);

        // Gestion du déplacement du personnage
        deplacer(pacman, fantome,jeu);

        primaryStage.setTitle("... Pac Man ...");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1 (up, down, right, left), pour le j2 (z, q, s, d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2,Pane jeu) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (Partieconte%2 == 0){
                switch (event.getCode() ){
                    case Q:
                        j1.deplacerAGauche();
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case D:
                        j1.deplacerADroite(scene.getWidth());
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case Z:
                        j1.deplacerEnHaut();
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case S:
                        j1.deplacerEnBas(scene.getHeight());
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case O:
                        j2.deplacerEnHaut();
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case L:
                        j2.deplacerEnBas(scene.getHeight());
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case K:
                        j2.deplacerAGauche();
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case M:
                        j2.deplacerADroite(scene.getWidth());
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                }
            }
            else{
                switch (event.getCode()) {
                    case Q:
                        j2.deplacerAGauche();
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case D:
                        j2.deplacerADroite(scene.getWidth());
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case Z:
                        j2.deplacerEnHaut();
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case S:
                        j2.deplacerEnBas(scene.getHeight());
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case O:
                        j1.deplacerEnHaut();
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case L:
                        j1.deplacerEnBas(scene.getHeight());
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case K:
                        j1.deplacerAGauche();
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                    case M:
                        j1.deplacerADroite(scene.getWidth());
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight());
                        break;
                }
            }
            if (j1.estEnCollision(j2)) {
                afficherAlerte();
                Partieconte ++;


            }
        });
    }

    private void afficherAlerte() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin de partie");
        alert.setHeaderText(null);
        alert.setContentText("Le fantôme a gagné !\n prepare vous au changement des roles");
        alert.showAndWait();
    }




}
