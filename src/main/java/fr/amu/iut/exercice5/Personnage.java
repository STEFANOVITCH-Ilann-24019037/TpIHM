package fr.amu.iut.exercice5;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

class Personnage extends Group {
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 10;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Circle corps;
    private String direction;
    private Bounds Pos = null;

    public Personnage(String direction, Color couleurContour, Color couleurRemplissage) {
        this.direction = direction;
        corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, couleurContour);
        corps.setFill(couleurRemplissage);
        getChildren().add(corps);
        Pos = getBoundsInParent();
    }


    public void deplacerAGauche(double largeurJeu ,ArrayList<Obstacles> list) {

        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("gauche")) {
            direction = "gauche";
        }

    }

    public void deplacerADroite(double largeurJeu,ArrayList<Obstacles> list) {

        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("droite")) {
            direction = "droite";
        }
    }

    public void deplacerEnBas(double hauteurJeu,ArrayList<Obstacles> list) {

        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("bas")) {
            direction = "bas";
        }
    }

    public void deplacerEnHaut(double hauteurJeu,ArrayList<Obstacles> list) {

        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("haut")) {
            direction = "haut";
        }

    }

    public void deplacrcontinue(double largeurJeu,double hauteurJeu,ArrayList<Obstacles> list){
        switch (direction){
            case "gauche":
                deplacerAGauche(largeurJeu,list);
                break;
            case "droite":
                deplacerADroite(largeurJeu,list);
                break;
            case "bas":
                deplacerEnBas(hauteurJeu, list);
                break;
            case"haut":
                deplacerEnHaut(hauteurJeu,list);


        }
    }

    public String getDirection(){
        return direction;
    }

    public void reverseDirection(){
        switch (this.direction){
            case "gauche":
                this.direction = "droite";
                break;
            case "droite":
                this.direction="gauche";
                break;
            case "bas":
                this.direction="haut";
                break;
            case "haut":
                this.direction="bas";
                break;
        }

    }

    public double estEnCollision(Personnage autrePersonnage, ArrayList <Obstacles> list) {
        double R;
        R = 0; // 0 pas de contact 1 contact entre les joueurs et 2 contact entre joueur  Qui lance la commende et mur et 3 si l'autrePersonnage est en contact avec le mur

        for (Obstacles obstacles : list) {
            if (getBoundsInParent().contains(obstacles.getBoundsInParent()) || obstacles.getBoundsInParent().contains(getBoundsInParent())) {
                R = 2;
            }
            if (autrePersonnage.getBoundsInParent().contains(obstacles.getBoundsInParent()) || obstacles.getBoundsInParent().contains(autrePersonnage.getBoundsInParent())) {
                R = 3;
            }
        }
        if (getBoundsInParent().contains(autrePersonnage.getBoundsInParent()) || autrePersonnage.getBoundsInParent().contains(getBoundsInParent())){
            R =1;
        }

        return R;

    }

    public Boolean contactMur( ArrayList <Obstacles> list){
        Boolean R = false;
        for (Obstacles obstacles : list) {
            if (getBoundsInParent().contains(obstacles.getBoundsInParent()) || obstacles.getBoundsInParent().contains(getBoundsInParent())) {
                R = true;
                break;
            }
        }
        return R;
    }


}