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

import java.util.ArrayList;
import java.util.List;

public class tictoctoeApplicationHT2 extends Application {
    private  boolean playable = true; // hiển thị trạng thái đang chơi vị trí nào
    private boolean turnX= true; // khoa chuot trai phai theo thu tu x truoc o sau
    private Title[][] board=new Title[3][3];
    private List<Combo> combos=new ArrayList<>();
    private Parent createContent(){
        Pane root=new Pane();
        root.setPrefSize(600,600);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Title tile=new Title();
                tile.setTranslateX(j*200);
                tile.setTranslateY(i*200);

                root.getChildren().add(tile);
                board[j][i] = tile;
            }

        }
        // horizontal
        for (int y = 0; y<3 ; y++){
            combos.add(new Combo(board[0][y], board[1][y],board[2][y]));
        }

        //vertical
        for (int x=0; x<3 ; x++){
            combos.add(new Combo(board[x][0],board[x][1],board[x][2]));
        }
        //disgonals
        combos.add(new Combo(board[0][0],board[1][1],board[2][2]));
        combos.add(new Combo(board[2][0],board[1][1],board[0][2]));
        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }
    private void checkState(){
    for (Combo combo : combos){
        if (combo.isComplete()){
            playable = false;
            break;
        }
    }
    }

    private class Combo {
        private Title[] titles;
        private Combo(Title... titles){
            this.titles=titles;
        }
        public boolean isComplete() {
            if (titles[0].getValue().isEmpty())
                return false;

            return titles[0].getValue().equals(titles[1].getValue())
                    & titles[0].getValue().equals(titles[2].getValue());
        }

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
                if (!playable)
                    return;
                if (envent.getButton()==MouseButton.PRIMARY){
                    if (!turnX)
                        return;
                    drawX();
                    turnX = false;
                    checkState();
                }
                else if (envent.getButton()==MouseButton.SECONDARY){
                    if (turnX)
                        return;
                    drawO();
                    turnX = true;
                    checkState();
                }
            });

        }
        public  String getValue() {
            return text.getText();
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
