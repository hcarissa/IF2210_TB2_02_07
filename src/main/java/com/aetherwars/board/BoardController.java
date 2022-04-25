package com.aetherwars.board;

import com.aetherwars.board.Board;
import com.aetherwars.card.*;
import com.aetherwars.deck.*;
import com.aetherwars.fieldcard.*;

import java.io.IOException;
import java.util.List;

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
        this.handCard = new GridPane();
        this.boardA = new GridPane();
        this.boardB = new GridPane();
        this.phases = new GridPane();
        this.healthA = new ProgressBar(1.0);
        this.healthB = new ProgressBar(1.0);
    }
}
