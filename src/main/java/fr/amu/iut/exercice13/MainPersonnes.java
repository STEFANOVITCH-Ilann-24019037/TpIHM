package fr.amu.iut.exercice13;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {
        lesPersonnes = FXCollections.observableArrayList(personne -> new Observable[]{personne.ageProperty()});

        unChangementListener = change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(personne ->
                            System.out.println(personne.getNom() + " a été ajouté.")
                    );
                }
                if (change.wasRemoved()) {
                    change.getRemoved().forEach(personne ->
                            System.out.println(personne.getNom() + " a été supprimé.")
                    );
                }
                if (change.wasUpdated()) {
                    change.getList().forEach(personne ->
                            System.out.println(personne.getNom() + " a maintenant " + personne.getAge() + " ans.")
                    );
                }
            }
        };

        // Ajout du listener à la liste
        lesPersonnes.addListener(unChangementListener);

        // Test avec question1()
        question1();
        System.out.println("");
        question2();
        System.out.println("");
        question3();
        System.out.println("");
        question5();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        paul.setAge(5);
    }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes)
            p.setAge(p.getAge() + 10);
        lesPersonnes.removeAll(paul, pierre);
    }
}
