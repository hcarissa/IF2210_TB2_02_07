package com.aetherwars;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.aetherwars.card.Card;
import com.aetherwars.card.CardController;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.aetherwars.board.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

public class Controller {
    private Board board;

    @FXML
    private Rectangle drawTab, planTab, attackTab, endTab;
    @FXML
    private Text health1, health2, turn, deckCount, mana;
    @FXML
    private ProgressBar healthBar1, healthBar2;
    @FXML
    private HBox hand;

    @FXML
    private Pane hoverPane, cardDetail, cardDescription;

    @FXML
    private Color active = new Color(1.0, 0.2431, 0.1216, 1.0);
    @FXML
    private Color inactive = new Color(0.5216, 0.6353, 0.8314, 1.0);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void changePhase(ActionEvent event) {
        if (board.getPhase() == Phase.DRAW) {
            // deactivate draw label, activate plan label
            this.drawTab.setFill(inactive);
            this.planTab.setFill(active);
            board.setPhase(Phase.PLAN);
        }
        else if (board.getPhase() == Phase.PLAN) {
            // deactivate plan label, activate attack label
            this.planTab.setFill(inactive);
            this.attackTab.setFill(active);
            board.setPhase(Phase.ATTACK);
        }
        else if (board.getPhase() == Phase.ATTACK) {
            // deactivate attack label, activate end label
            this.attackTab.setFill(inactive);
            this.endTab.setFill(active);
            board.setPhase(Phase.END);
        }
        else {
            // deactivate end label, change turn, activate draw label
            this.endTab.setFill(inactive);
            this.drawTab.setFill(active);
            board.setPhase(Phase.DRAW);
            board.switchTurn();
            hand.getChildren().clear();
            updateTurn(board.getRound());
        }
    }

    @FXML
    void initialize() {
        this.drawTab.setFill(active);
        this.healthBar1.setStyle("-fx-accent: #ff3e1f");
        this.healthBar2.setStyle("-fx-accent: #ff3e1f");
        cardDetail.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
        cardDescription.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
        hoverPane.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
    }

    public void setBoard(Board b) {
        this.board = b;

    }

    public void updateHP(int i, int hp) {
        if (i == 1) {
            this.health1.setText(String.valueOf(hp));
            this.healthBar1.setProgress((double)hp/80);
            //this.healthBar1.setWidth((hp/80) * 350);
        }
        else {
            this.health2.setText(String.valueOf(hp));
            this.healthBar2.setProgress((double)hp/80);
            //this.healthBar2.setWidth((hp/80) * 350);
        }
    }

    public void updateTurn(int i) {
        this.turn.setText(String.valueOf(i));
    }

    public void updateDeck() {
        this.deckCount.setText(String.valueOf(board.getActivePlayer().getDeckCount() + "/60"));
    }

    public void updateMana() {
        this.mana.setText(String.valueOf(board.getActivePlayer().getMana()) + "/");
    }

    public void loadHand() {
        this.hand.getChildren().clear();
        List<Card> handCards = board.getActivePlayer().getHand();
        try {
            for (int i = 0; i < handCards.size(); i++) {
                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("CardView.fxml"));
                Pane cardPane = cardloader.load();
                CardController cardController = cardloader.getController();

                cardController.setCard(handCards.get(i));
                cardPane.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
                cardPane.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                    if (newValue) {
                        showHovered(cardController.getCard());
                    } else {
                        hoverPane.getChildren().clear();
                    }
                });
                this.hand.getChildren().add(cardPane);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void showHovered(Card c) {
        Scale scale = new Scale(0.2, 0.2);
        Image image = new Image(c.getImagePath());
        ImageView img = new ImageView();
        img.setImage(image);
        img.getTransforms().add(scale);
        this.hoverPane.getChildren().add(img);

        Text desc = new Text("Ini Creeper, saya kurang tau juga sih dia siapa");
        desc.setFont(Font.font ("Gadugi", 10));
        desc.setFill(Color.BLACK);
        cardDescription.getChildren().add(desc);

        // Card Detail belum
    }
}
