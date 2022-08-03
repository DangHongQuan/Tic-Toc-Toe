package com.example.tictactoeapp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;

public class TicTacToeApplicartionFULL extends Application {

    private char whoseTurn = 'X';

    private Cell[][] cell =  new Cell[3][3];

    private Label lblStatus = new Label("X's turn to play");

    @Override
    public void start(Stage primaryStage) {

        Button xButton = new Button("X");
        Button oButton = new Button("O");
        Button exit = new Button("Exit");
        Button play = new Button("Play Again?");
        xButton.setStyle("-fx-font-size: 15pt;");
        oButton.setStyle("-fx-font-size: 15pt;");
        exit.setStyle("-fx-font-size: 15pt;");
        play.setStyle("-fx-font-size: 15pt;");

        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });

        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                pane.add(cell[i][j] = new Cell(), j, i);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);

        HBox buttonBar = new HBox();
        buttonBar.setSpacing(87.0);
        buttonBar.getChildren().addAll(xButton, oButton, play,exit);

        borderPane.setTop(buttonBar);


        Scene scene = new Scene(borderPane, 550, 470);
        primaryStage.setTitle("Wild TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);


    }

    /** Determine if the cell are all occupied */
    public boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (cell[i][j].getToken() == ' ')
                    return false;

        return true;
    }

    /** Determine if the player with the specified token wins */
    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++)
            if (cell[i][0].getToken() == token
                    && cell[i][1].getToken() == token
                    && cell[i][2].getToken() == token) {
                return true;
            }

        for (int j = 0; j < 3; j++)
            if (cell[0][j].getToken() ==  token
                    && cell[1][j].getToken() == token
                    && cell[2][j].getToken() == token) {
                return true;
            }

        if (cell[0][0].getToken() == token
                && cell[1][1].getToken() == token
                && cell[2][2].getToken() == token) {
            return true;
        }

        if (cell[0][2].getToken() == token
                && cell[1][1].getToken() == token
                && cell[2][0].getToken() == token) {
            return true;
        }

        return false;
    }


    public class Cell extends Pane {

        private char token = ' ';

        public Cell() {
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        /** Return token */
        public char getToken() {
            return token;
        }

        /** Set a new token */
        public void setToken(char c) {
            token = c;

            if (token == 'X') {
                Line line1 = new Line(10, 10,
                        this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10,
                        this.getWidth() - 10, 10);
                line2.startYProperty().bind(
                        this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));

                // Add the lines to the pane
                this.getChildren().addAll(line1, line2);
            }
            else if (token == 'O') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2,
                        this.getHeight() / 2, this.getWidth() / 2 - 10,
                        this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(
                        this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(
                        this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(
                        this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(
                        this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.WHITE);

                getChildren().add(ellipse); // Add the ellipse to the pane
            }
        }

        /* Handle a mouse click event */
        private void handleMouseClick() {
            // If cell is empty and game is not over
            if (token == ' ' && whoseTurn != ' ') {
                setToken(whoseTurn); // Set token in the cell

                // Check game status
                if (isWon(whoseTurn)) {
                    lblStatus.setText(whoseTurn + " won! The game is over. Would you like to play again?");
                    whoseTurn = ' '; // Game is over
                }
                else if (isFull()) {
                    lblStatus.setText("Draw! The game is over. Would you like to play again?");
                    whoseTurn = ' '; // Game is over
                }
                else {
                    // Change the turn
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                    // Display whose turn
                    lblStatus.setText(whoseTurn + "'s turn.");
                }
            }
        }
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
