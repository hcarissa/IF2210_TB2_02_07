package com.aetherwars;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.aetherwars.card.Card;
import com.aetherwars.card.CardController;
import com.aetherwars.fieldcard.BoardCardController;
import com.aetherwars.fieldcard.SummonedCharacter;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import com.aetherwars.board.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    private HBox tempCards;
    @FXML
    private Pane[] pBoard1, pBoard2;
    @FXML
    private Pane cBoard1A, cBoard1B, cBoard1C, cBoard1D, cBoard1E;
    @FXML
    private Pane cBoard2A, cBoard2B, cBoard2C, cBoard2D, cBoard2E;

    @FXML
    private Pane hoverPane, cardDetail, cardDescription;
    @FXML
    private Pane hand1, hand2, hand3, hand4, hand5;

    @FXML
    private Color active = new Color(1.0, 0.2431, 0.1216, 1.0);
    @FXML
    private Color inactive = new Color(0.5216, 0.6353, 0.8314, 1.0);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nextBtn;

    @FXML
    void changePhase() {
        if (board.getPhase() == Phase.DRAW) {
            // deactivate draw label, activate plan label
            reload();
            this.drawTab.setFill(inactive);
            this.planTab.setFill(active);
            nextBtn.setDisable(false);
            board.setPhase(Phase.PLAN);
        }
        else if (board.getPhase() == Phase.PLAN) {
            // deactivate plan label, activate attack label
            reload();
            this.planTab.setFill(inactive);
            this.attackTab.setFill(active);
            board.setPhase(Phase.ATTACK);
        }
        else if (board.getPhase() == Phase.ATTACK) {
            // deactivate attack label, activate end label
            reload();
            this.attackTab.setFill(inactive);
            this.endTab.setFill(active);
            board.setPhase(Phase.END);
        }
        else {
            // deactivate end label, change turn, activate draw label
            reload();
            this.endTab.setFill(inactive);
            this.drawTab.setFill(active);
            board.switchTurn();
            hand.getChildren().clear();
            updateTurn(board.getRound());
            reload();
            board.setPhase(Phase.DRAW);
            loadTemp();
            nextBtn.setDisable(true);
        }
    }

    @FXML
    void initialize() {
        this.drawTab.setFill(active);
        this.pBoard1 = new Pane[]{cBoard1A, cBoard1B, cBoard1C, cBoard1D, cBoard1E};
        this.pBoard2 = new Pane[]{cBoard2A, cBoard2B, cBoard2C, cBoard2D, cBoard2E};
        this.healthBar1.setStyle("-fx-accent: #ff3e1f");
        this.healthBar2.setStyle("-fx-accent: #ff3e1f");
        cardDetail.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
        cardDescription.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
        hoverPane.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
    }

    public void setBoard(Board b) {
        this.board = b;
        reload();
    }

    public void reload() {
        loadHand();
        updateDeck();
        updateMana();
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
        this.mana.setText(String.valueOf(board.getActivePlayer().getMana()) + "/" + String.valueOf(Math.min(board.getRound(), 10)));
    }

    public void loadTemp() {
        this.tempCards.getChildren().clear();
        Card[] in = board.getActivePlayer().getTemp();
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("Card i loaded");
                FXMLLoader cardloader = new FXMLLoader(getClass().getResource("CardView.fxml"));
                Pane cardPane = cardloader.load();
                CardController cardController = cardloader.getController();

                cardController.setCard(in[i]);
                cardPane.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
                cardPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardPane.setStyle("-fx-background-color: #efeaea; -fx-border-color: CYAN; -fx-border-width: 5px");
                    }
                });
                cardPane.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardPane.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
                    }
                });
//                cardPane.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
//                    if (newValue) {
//                        showHovered(cardController.getCard());
//                    } else {
//                        hoverPane.getChildren().clear();
//                    }
//                });
                final int idx = i;
                cardPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 1){
                                System.out.println("clicked");
                                board.getActivePlayer().chooseCard(idx);
                                tempCards.getChildren().clear();
                                changePhase();
                            }
                        }
                    }
                });
                this.tempCards.getChildren().add(cardPane);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadHand() {
        this.hand.getChildren().clear();
        List<Card> handCards = board.getActivePlayer().getHand();
        System.out.println("handCount : " + handCards.size());
        board.getActivePlayer().getName();

        try {
            System.out.println(handCards.size());
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
                cardPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 2){
                                System.out.println("Double clicked");
                            }
                        }
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

    public void addToBoard(SummonedCharacter sc) {
        try {
            FXMLLoader bCardloader = new FXMLLoader(getClass().getResource("BoardCard.fxml"));
            Pane bCardPane = bCardloader.load();
            BoardCardController bCardController = bCardloader.getController();

            bCardController.setCard(sc);
            bCardPane.setStyle("-fx-background-color: #efeaea; -fx-border-color: BLACK;");
            // cara masukin ke board masing-masing Player gmn?
            if (board.getTurn() == 1) {
                // tambahin ke pBoard1
            }
            else {
                // tambahin ke pBoard2
            }
        }
        catch (IOException e) {
        }

    }
}
