package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int y=0 ; y<3;y++) {
                Label label = new Label();
                int nombre = random.nextInt(3);
                switch (nombre){
                    case 0:
                        label.setGraphic( new ImageView("/exercice2/Croix.png"));
                        break;
                    case 1:
                        label.setGraphic(new ImageView("/exercice2/Vide.png"));
                        break;
                    case 2:
                        label.setGraphic(new ImageView("/exercice2/Rond.png"));
                        break;
                }

                BorderPane borderPane = new BorderPane(label);
                borderPane.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

                gridPane.add(borderPane, i , y);
            }
        }
        Scene scene = new Scene(gridPane, 200, 200);


        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}