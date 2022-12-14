package com.example.tictactoeapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class tictoctoeApplicationHT1 extends Application {
    private Parent createContent(){
        Pane root=new Pane();
        root.setPrefSize(600,600);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Title tile=new Title();
                tile.setTranslateX(j*200);
                tile.setTranslateY(i*200);

                root.getChildren().add(tile);
            }

        }

        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
       primaryStage.show();
    }
    private class Title extends StackPane{

        private Text text =new Text();

        public Title(){
            Rectangle border =new Rectangle(200,200);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            text.setFont(Font.font(80));
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
            setOnMouseClicked(envent ->{
                if (envent.getButton()==MouseButton.PRIMARY){
                    drawX();
                }
                else if (envent.getButton()==MouseButton.SECONDARY){
                    drawO();
                }
            });

        }

        private void drawX(){
            text.setText("X");
        }
        private void drawO(){
            text.setText("O");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
