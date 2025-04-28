package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Pacman extends Personnage {

    private Line bouche;


    public Pacman() {
        super("droite", Color.BLACK, Color.YELLOW);
        bouche = new Line(LARGEUR_MOITIE_PERSONNAGE, LARGEUR_MOITIE_PERSONNAGE, (LARGEUR_MOITIE_PERSONNAGE * 2) - .5, LARGEUR_MOITIE_PERSONNAGE);
        bouche.setFill(Color.BLACK);
        super.getChildren().add(bouche);
    }

    @Override
    public void deplacerAGauche(double largeurJeu,ArrayList<Obstacles> list) {
        super.deplacerAGauche(largeurJeu,list);
        if (contactMur(list)) super.deplacerADroite(largeurJeu, list);
        bouche.setEndX(bouche.getStartX() - LARGEUR_MOITIE_PERSONNAGE + .5);
        bouche.setEndY(bouche.getStartY());
    }

    @Override
    public void deplacerADroite(double largeurJeu,ArrayList<Obstacles> list) {
        super.deplacerADroite(largeurJeu,list);
        if (contactMur(list)) super.deplacerAGauche(largeurJeu, list);
        bouche.setEndX(bouche.getStartX() + LARGEUR_MOITIE_PERSONNAGE - .5);
        bouche.setEndY(bouche.getStartY());
    }

    @Override
    public void deplacerEnBas(double hauteurJeu,ArrayList<Obstacles> list) {
        super.deplacerEnBas(hauteurJeu,list);
        if (contactMur(list)) super.deplacerEnHaut(hauteurJeu, list);
        bouche.setEndX(bouche.getStartX() );
        bouche.setEndY(bouche.getStartY()+ LARGEUR_MOITIE_PERSONNAGE - .5);
    }

    @Override
    public void deplacerEnHaut(double hauteurJeu,ArrayList<Obstacles> list) {
        super.deplacerEnHaut(hauteurJeu,list);
        if (contactMur(list)) super.deplacerEnBas(hauteurJeu, list);
        bouche.setEndX(bouche.getStartX() );
        bouche.setEndY(bouche.getStartY()-LARGEUR_MOITIE_PERSONNAGE + .5);
    }
    @Override
    public void deplacrcontinue(double largeurJeu,double hauteurJeu,ArrayList<Obstacles> list){
        super.deplacrcontinue(largeurJeu,hauteurJeu,list);
    }
}