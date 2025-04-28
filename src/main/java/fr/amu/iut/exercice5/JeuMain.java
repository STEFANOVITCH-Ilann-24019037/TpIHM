package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

        Obstacles obs = new Obstacles(100,10,100,100);
        ArrayList  <Obstacles> obstacles =new ArrayList<>();
        obstacles.add(obs);
        for (int i =0; i<obstacles.size();i++){jeu.getChildren().add(obstacles.get(i));}

        root.setCenter(jeu);

        scene = new Scene(root);

        deplacer(pacman, fantome,primaryStage,obstacles);

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
    private void deplacer(Personnage j1, Personnage j2,Stage primaryStage,ArrayList <Obstacles> list) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (Partieconte%2 == 0){
                switch (event.getCode() ){
                    case Q:
                        j1.deplacerAGauche(scene.getWidth(),list);
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case D:
                        j1.deplacerADroite(scene.getWidth(),list);
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case Z:
                        j1.deplacerEnHaut(scene.getHeight(),list);
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case S:
                        j1.deplacerEnBas(scene.getHeight(),list);
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case O:
                        j2.deplacerEnHaut(scene.getHeight(),list);
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case L:
                        j2.deplacerEnBas(scene.getHeight(),list);
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case K:
                        j2.deplacerAGauche(scene.getWidth(),list);
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case M:
                        j2.deplacerADroite(scene.getWidth(),list);
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                }
            }
            else{
                switch (event.getCode()) {
                    case K:
                        j1.deplacerAGauche(scene.getWidth(),list);
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case M:
                        j1.deplacerADroite(scene.getWidth(),list);
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case O:
                        j1.deplacerEnHaut(scene.getHeight(),list);
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case L:
                        j1.deplacerEnBas(scene.getHeight(),list);
                        j2.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case Z:
                        j2.deplacerEnHaut(scene.getHeight(),list);
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case S:
                        j2.deplacerEnBas(scene.getHeight(),list);
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case Q:
                        j2.deplacerAGauche(scene.getWidth(),list);
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                    case D:
                        j2.deplacerADroite(scene.getWidth(),list);
                        j1.deplacrcontinue(scene.getWidth(),scene.getHeight(),list);
                        break;
                }
            }
            if (j1.estEnCollision(j2,list) == 1) { // 0 pas de contact 1 contact entre les joueurs  2 contact entre joueur1 et mur 3 J2 et mur
                afficherAlerte();
                Partieconte ++;
            }
            if (j1.estEnCollision(j2,list) == 2){
                j1.reverseDirection();
            }
            if (j1.estEnCollision(j2,list) == 3){
                j2.reverseDirection();
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