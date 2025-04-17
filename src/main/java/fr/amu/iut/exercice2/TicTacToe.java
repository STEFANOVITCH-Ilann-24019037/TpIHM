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

    private Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    private Image xImage;
    private Image oImage;
    private Image emptyImage;

    @Override
    public void start(Stage primaryStage) {
        // Charger les images depuis les ressources
        xImage = loadImage("/exercice2/Croix.png");
        oImage = loadImage("/exercice2/Rond.png");
        emptyImage = loadImage("/exercice2/Vide.png");


        GridPane gridPane = new GridPane();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setGraphic(new ImageView(emptyImage));
                button.setOnAction(event -> onButtonClick(button));
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResource(path).toExternalForm());
        } catch (Exception e) {
            System.err.println("Could not load image: " + path);
            return null;
        }
    }

    private void onButtonClick(Button button) {
        ImageView currentImageView = (ImageView) button.getGraphic();
        if (currentImageView.getImage().equals(emptyImage)) {
            if (playerXTurn) {
                button.setGraphic(new ImageView(xImage));
            } else {
                button.setGraphic(new ImageView(oImage));
            }
            playerXTurn = !playerXTurn;

            if (checkForWin()) {
                showAlert("Player " + (playerXTurn ? "O" : "X") + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                showAlert("It's a draw!");
                resetGame();
            }
        }
    }

    private boolean checkForWin() {
        ImageView[][] board = new ImageView[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = (ImageView) buttons[row][col].getGraphic();
            }
        }

        for (int row = 0; row < 3; row++) {
            if (board[row][0].getImage().equals(board[row][1].getImage()) &&
                    board[row][1].getImage().equals(board[row][2].getImage()) &&
                    !board[row][0].getImage().equals(emptyImage)) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col].getImage().equals(board[1][col].getImage()) &&
                    board[1][col].getImage().equals(board[2][col].getImage()) &&
                    !board[0][col].getImage().equals(emptyImage)) {
                return true;
            }
        }

        if (board[0][0].getImage().equals(board[1][1].getImage()) &&
                board[1][1].getImage().equals(board[2][2].getImage()) &&
                !board[0][0].getImage().equals(emptyImage)) {
            return true;
        }

        if (board[0][2].getImage().equals(board[1][1].getImage()) &&
                board[1][1].getImage().equals(board[2][0].getImage()) &&
                !board[0][2].getImage().equals(emptyImage)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (((ImageView) buttons[row][col].getGraphic()).getImage().equals(emptyImage)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setGraphic(new ImageView(emptyImage));
            }
        }
        playerXTurn = true;
    }


}
