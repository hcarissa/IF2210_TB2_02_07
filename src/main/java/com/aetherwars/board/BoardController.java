package com.aetherwars.board;

import com.aetherwars.board.Board;
import com.aetherwars.card.*;
import com.aetherwars.deck.*;
import com.aetherwars.fieldcard.*;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;

public class BoardController {
    @ FXML
    private Label p1_name, p2_name, turn;
    @ FXML
    private Button next;
    @ FXML
    private GridPane handCard, boardA, boardB, phases;
    @ FXML
    private ProgressBar healthA, healthB;
    private Board board;

    @ FXML
    public void initialize() {
        this.p1_name = new Label("Player 1");
        this.p2_name = new Label("Player 2");
        this.turn = new Label("Turn 1");
        this.next = new Button(">>");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                changePhase();
            }
        };
        next.setOnAction(event);

        this.handCard = new GridPane();
        handCard.setVgap(5);
        handCard.setHgap(5);
        handCard.setPadding(new Insets(5,5,5,5));
        for (int i = 0; i < 5; i++) {
            Rectangle emptycard = new Rectangle();
            emptycard.setStyle("-fx-background-fill: black");
            emptycard.setWidth(50);
            emptycard.setHeight(75);
            handCard.add(emptycard, i, 0);
        }
        this.boardA = new GridPane();
        boardA.setVgap(5);
        boardA.setHgap(5);
        boardA.setPadding(new Insets(5,5,5,5));
        for (int i = 0; i < 5; i++) {
            Rectangle emptycard = new Rectangle();
            emptycard.setStyle("-fx-background-fill: black");
            emptycard.setWidth(50);
            emptycard.setHeight(75);
            boardA.add(emptycard, i, 0);
        }
        this.boardB = new GridPane();
        boardB.setVgap(5);
        boardB.setHgap(5);
        boardB.setPadding(new Insets(5,5,5,5));
        for (int i = 0; i < 5; i++) {
            Rectangle emptycard = new Rectangle();
            emptycard.setStyle("-fx-background-fill: black");
            emptycard.setWidth(50);
            emptycard.setHeight(75);
            boardB.add(emptycard, i, 0);
        }
        this.phases = new GridPane();
        this.healthA = new ProgressBar(1.0);
        this.healthB = new ProgressBar(1.0);
    }

    public void changePhase() {
        if (board.getPhase() == Phase.DRAW) {
            // deactivate draw label, activate plan label
            board.setPhase(Phase.PLAN);
        }
        else if (board.getPhase() == Phase.PLAN) {
            // deactivate plan label, activate attack label
            board.setPhase(Phase.ATTACK);
        }
        else if (board.getPhase() == Phase.ATTACK) {
            // deactivate attack label, activate end label
            board.setPhase(Phase.END);
        }
        else {
            // deactivate end label, change turn, activate draw label
            board.setPhase(Phase.DRAW);
            board.switchTurn();
        }
    }
}
