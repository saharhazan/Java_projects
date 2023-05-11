package sample;
/* Author: Sahar Hazan
 * Date: 16.12.2022
 * Maman 13 - Question 1
 */

import java.io.FileNotFoundException;

//import javax.swing.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Line;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;



public class HangManController{
    @FXML
    private Canvas canv;
    @FXML
    private GridPane grid;
    @FXML
    private Label labelGuess;
    @FXML
    private Label labelWord;

    private final int SIZE = 6;
    private HangManLogic game;
    private Vocabulary dict;
    private Drawer parts;
    private GraphicsContext gc;
    private Button[] btns;

    public void initialize(){
        parts = new Drawer();
        dict = new Vocabulary();
        game = new HangManLogic();

        gc = canv.getGraphicsContext2D();
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(6);

        parts.draw();
        drawBase();
        dict.fileToArrayList();
        game.setWord(dict.getRandomizeWord());
        labelGuess.setText("" + game.getLives());
        labelWord.setText(game.printLines(game.getWord()));
        restartButtons();     
    }

    public void restartGame() {
        gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
        drawBase();
        game.setWord(dict.getRandomizeWord());
        labelGuess.setText("" + game.getLives());
        labelWord.setText(game.printLines(game.getWord()));
        grid.getChildren().clear();
        restartButtons();
    }

    public void restartButtons(){
        final char FIRST_LETTER = 'a';
        final char LAST_LETTER = 'z';
        btns = new Button[SIZE * SIZE];
        char letter = FIRST_LETTER;
        for (int i = 0; i < btns.length; i++) {
            btns[i] = new Button(String.valueOf(letter));
            btns[i].setPrefSize(grid.getPrefWidth() / SIZE, grid.getPrefHeight() / SIZE);
            grid.add(btns[i], i % SIZE, i / SIZE);
            btns[i].setDisable(false);
            btns[i].setOnAction(event -> handleButton(event));
            letter++;
            if (letter > LAST_LETTER) {
                break;
            }
            letter++;
        }
    }

    private void handleButton(ActionEvent event) {
        Button currBtn = (Button) event.getSource();
        String letter = currBtn.getText();
        System.out.println(letter);
        game.checkGuess(letter);
        System.out.println(game.getLives());
        labelGuess.setText("" + game.getLives());
        labelWord.setText(game.getCheck());
        currBtn.setDisable(true);
        hangManDraw(gc, parts);
    }

    public void hangManDraw(GraphicsContext gc, Drawer parts){
        if(game.getLives() == 5 && !game.currGuess){ // print the base -> mistake number 1
            Line head = parts.hangmanParts.get(5);
            gc.strokeOval(head.getStartX(),head.getStartY(),head.getEndX(),head.getEndY());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Incorrect Guess");
            alert.show();
        }
        if(game.getLives() == 4 && !game.currGuess){
            Line body = parts.hangmanParts.get(6);
            gc.strokeLine(body.getStartX(),body.getStartY(),body.getEndX(),body.getEndY());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Incorrect Guess");
            alert.show();
        }
        if(game.getLives() == 3 && !game.currGuess){
            Line hands1 = parts.hangmanParts.get(7);
            gc.strokeLine(hands1.getStartX(),hands1.getStartY(),hands1.getEndX(),hands1.getEndY());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Incorrect Guess");
            alert.show();
        }
        if(game.getLives() == 2 && !game.currGuess){
            Line hands2 = parts.hangmanParts.get(8);
            gc.strokeLine(hands2.getStartX(),hands2.getStartY(),hands2.getEndX(),hands2.getEndY());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Incorrect Guess");
            alert.show();
        }
        if(game.getLives() == 1 && !game.currGuess){
            Line legs1 = parts.hangmanParts.get(9);
            gc.strokeLine(legs1.getStartX(),legs1.getStartY(),legs1.getEndX(),legs1.getEndY());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Incorrect Guess");
            alert.show();
        }
        if(game.getLives() == 0 && !game.currGuess){
            Line legs2 = parts.hangmanParts.get(10);
            gc.strokeLine(legs2.getStartX(),legs2.getStartY(),legs2.getEndX(),legs2.getEndY());
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION,"You Lost! Do you want to play a new game? ");
            alert1.showAndWait();
            if(alert1.getResult() == ButtonType.OK){
                game.restart();
                restartGame();
            }
            else{System.exit(0);}
        }
    }
    public void drawBase(){
        for (Line line : parts.hangmanParts) {
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
    }

    @FXML
    void onPressed(ActionEvent event) {
        game.restart();
        restartGame();  
    }
}
