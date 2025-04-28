package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(
                new MenuItem("New"),
                new MenuItem("Open"),
                new SeparatorMenuItem(),
                new MenuItem("Save"),
                new MenuItem("Close")
        );

        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(
                new MenuItem("Cut"),
                new MenuItem("Copy"),
                new SeparatorMenuItem(),
                new MenuItem("Paste")
        );

        Menu helpMenu = new Menu("Help");

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        VBox topBox = new VBox(menuBar, new Separator());

        ////////////////////////////////////////////////////////////////////////////////

        VBox leftButtons = new VBox(10);
        leftButtons.setPadding(new Insets(10));
        leftButtons.getChildren().addAll(
                new Label("Boutons :"),
                new Button("Bouton 1"),
                new Button("Bouton 2"),
                new Button("Bouton 3")
        );

        HBox leftPanel = new HBox();
        leftPanel.getChildren().addAll(leftButtons, new Separator(Orientation.VERTICAL));

        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Name:"), 0, 0);
        form.add(new TextField(), 1, 0);

        form.add(new Label("Email:"), 0, 1);
        form.add(new TextField(), 1, 1);

        form.add(new Label("Password:"), 0, 2);
        form.add(new PasswordField(), 1, 2);

        HBox formButtons = new HBox(10);
        formButtons.getChildren().addAll(new Button("Submit"), new Button("Cancel"));
        form.add(formButtons, 1, 3);


        Label bottomLabel = new Label("Ceci est un label de bas de page");
        VBox bottomBox = new VBox(new Separator(), bottomLabel);
        bottomBox.setPadding(new Insets(5));
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setLeft(leftPanel);
        root.setCenter(form);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}