package fr.amu.iut.exercice3;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import java.io.FileInputStream;



import java.awt.*;

public class MaPremiereFenetreJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image(new FileInputStream("/amuhome/s24019037/IdeaProjects/TpIHM/src/main/resources/exercice3/Bonjour.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(25);
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
        Button buttonOne = new Button("close");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(buttonOne, 0, 5);
        gridPane.add(imageView,0,2);
        buttonOne.setOnAction(e ->primaryStage.close());
        Scene scene = new Scene(gridPane,400,550);
        scene.getStylesheets().add("/exercice3/Bonjour.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("La page d'un Pro de JavaFX");
        primaryStage.show();
    }

}
