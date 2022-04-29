package com.aetherwars;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.aetherwars.card.Card;
import com.aetherwars.card.CardController;
import com.aetherwars.card.CardType;
import com.aetherwars.card.CharacterCard;
import com.aetherwars.fieldcard.BoardCardController;
import com.aetherwars.fieldcard.SummonedCharacter;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import com.aetherwars.board.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;



public class Controller {
    private Board board;

    private CharacterCard dragged_char;
    private int draggedCharIdx;

    private static final DataFormat dformat = new DataFormat("javafx.scene.layout.VBox");

    private SummonedCharacter sc;
    private String CARD_DEFAULT = "-fx-background-color: #efeaea; -fx-border-color: BLACK;";
    private String CARD_FOCUS = "-fx-background-color: PALETURQUOISE; -fx-border-color: ROYALBLUE; -fx-border-width: 3px";
    private String CARD_HOVER = "-fx-background-color: PALETURQUOISE; -fx-border-color: ROYALBLUE; -fx-border-width: 3px; -fx-margin-bottom: 5px";


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

    private Pane[] paneA = {cBoard1A, cBoard1B, cBoard1C, cBoard1D, cBoard1E};
    private Pane[] paneB = {cBoard2A, cBoard2B, cBoard2C, cBoard2D, cBoard2E};

    @FXML
    private Text description;

    @FXML
    private Text details;

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
    private Button nextBtn;

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
        for (Pane pane : this.pBoard1) {
            pane.setOnDragOver(e -> {
                if (board.getTurn() == 1) {
                    System.out.println("Drag over detected");
                    Dragboard db = e.getDragboard();
                    if (db.hasContent(dformat)) {
                        e.acceptTransferModes(TransferMode.ANY);
                    }
                }
                else {
                    System.out.println("Invalid drop target");
                }
            });
            pane.setOnDragDropped (e -> {
                if (board.getTurn() == 1) {
                    System.out.println("Drag released");
                    Dragboard db = e.getDragboard();
                    if (db.hasContent(dformat)) {
                        addToBoard(dragged_char, pane);
                        this.board.getActivePlayer().discardCard(draggedCharIdx);
                        dragged_char = null;
                        loadHand();
                    }
                }
                else {
                    System.out.println("Invalid drop target");
                }
            });
        }
        for (Pane pane : this.pBoard2) {
            pane.setOnDragOver(e -> {
                if (board.getTurn() == 2) {
                    System.out.println("Drag over detected");
                    Dragboard db = e.getDragboard();
                    if (db.hasContent(dformat)) {
                        e.acceptTransferModes(TransferMode.ANY);
                    }
                }
                else {
                    System.out.println("Invalid drop target");
                }
            });
            pane.setOnDragDropped (e -> {
                if (board.getTurn() == 2) {
                    System.out.println("Drag released");
                    Dragboard db = e.getDragboard();
                    if (db.hasContent(dformat)) {
                        addToBoard(dragged_char, pane);
                        this.board.getActivePlayer().discardCard(draggedCharIdx);
                        dragged_char = null;
                        loadHand();
                    }
                }
                else {
                    System.out.println("Invalid drop target");
                }
            });
        }
    }

    @FXML
    void changePhase() {
        if (board.getPhase() == Phase.DRAW) {
            // deactivate draw label, activate plan label
            // check active player deck
            if (board.isWinner(board.getActivePlayer())) {
                board.setFinished();
            }
            else {
                reload();
                this.drawTab.setFill(inactive);
                this.planTab.setFill(active);
                nextBtn.setDisable(false);
                board.setPhase(Phase.PLAN);
            }
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
            if (board.isWinner(board.getActivePlayer())) {
                board.setFinished();
            }
            else {
                this.endTab.setFill(inactive);
                this.drawTab.setFill(active);
                board.switchTurn();
                hand.getChildren().clear();
                reload();
                board.setPhase(Phase.DRAW);
                if (board.getRound() >= 1) {
                    loadTemp();
                    nextBtn.setDisable(true);
                }
            }
        }
    }

    public void setBoard(Board b) {
        this.board = b;
        reload();
    }

    public void reload() {
        loadHand();
        updateDeck();
        updateMana();
        updateTurn();
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

    public void updateTurn() {
        this.turn.setText(String.valueOf(board.getRound()));
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
                cardPane.setStyle(CARD_DEFAULT);
                cardPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardPane.setStyle(CARD_HOVER);
                    }
                });
                cardPane.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardPane.setStyle(CARD_DEFAULT);
                    }
                });

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
                cardPane.setStyle(CARD_DEFAULT);
                cardPane.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                    // hover event
                    if (newValue) {
                        showHovered(cardController.getCard());
                    } else {
                        hoverPane.getChildren().clear();
                        details.setVisible(false);
                        description.setVisible(false);
                    }
                });
                final int idx = i;
                cardPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    // double click event, mungkin bisa dipake buat throw
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 1 && board.getPhase().equals(Phase.PLAN)) {
                                for (javafx.scene.Node node : hand.getChildren()) {
                                    node.setStyle(CARD_DEFAULT);
                                };
                                cardPane.setStyle(CARD_FOCUS);
                            }
                            if(mouseEvent.getClickCount() == 2) {
                                System.out.println("Double clicked");
                                if (board.getPhase().equals(Phase.PLAN)) {
                                    board.getActivePlayer().discardCard(idx);
                                    loadHand();
                                }
                            }
                        }
                    }
                });

                cardPane.setOnDragDetected(new EventHandler <MouseEvent>() {
                    // perlu dicek apakah kartu udah masuk di board atau belum
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Drag detected");
                        if (board.getPhase() == Phase.PLAN) {
                            // fase plan, kartu belum ada di board, tambahkan kartu ke board

                            Dragboard db = cardPane.startDragAndDrop(TransferMode.ANY);
                            db.setDragView(cardPane.snapshot(null, null));
                            ClipboardContent cc = new ClipboardContent();
                            cc.put(dformat, "Character Card");
                            db.setContent(cc);
                            setDraggged((CharacterCard) cardController.getCard(), idx);
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

        description.setVisible(true);
        description.setFont(Font.font("Gadugi", 8));
        description.setText(c.getDescription());

        details.setVisible(true);
        details.setFont(Font.font("Gadugi", 8));
        details.setText(c.getName());
//        Text desc = new Text("Ini Creeper, saya kurang tau juga sih dia siapa");
//        desc.setFont(Font.font ("Gadugi", 10));
//        desc.setFill(Color.WHITE);
//        cardDescription.getChildren().add(desc);

        // Card Detail belum
    }

    public void loadBoard() {
        try {
            for (Pane pane : Arrays.asList(paneA)) {
                // clear
                for (SummonedCharacter sc : board.getActivePlayer().getbCards()) {
                    addToBoard(sc.getCharacter(), sc.getPosition());
                }
            }
            for (Pane pane : Arrays.asList(paneB)) {
                // clear
                for (SummonedCharacter sc : board.getActivePlayer().getbCards()) {
                    addToBoard(sc.getCharacter(), sc.getPosition());
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addToBoard(CharacterCard cc, Pane target) {
        // ubah summoned character ke board character
        // terus diadd
        try {
            FXMLLoader cardloader = new FXMLLoader(getClass().getResource("BoardCard.fxml"));
            Pane bCardPane = cardloader.load();
            BoardCardController cardController = cardloader.getController();
            SummonedCharacter sc = new SummonedCharacter(target, cc);
            cardController.setCard(sc);
            target.getChildren().add(bCardPane);
            bCardPane.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                // hover event
                if (newValue) {
                    showHovered(cardController.getCard());
                } else {
                    hoverPane.getChildren().clear();
                    cardDetail.getChildren().clear();
                    cardDescription.getChildren().clear();
                }
            });
            final SummonedCharacter schar = sc;
            bCardPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            // double click event, mungkin bisa dipake buat throw
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 1 && board.getPhase().equals(Phase.PLAN)) {
                        cBoard1A.setStyle(CARD_DEFAULT);
                        cBoard1B.setStyle(CARD_DEFAULT);
                        cBoard1C.setStyle(CARD_DEFAULT);
                        cBoard1D.setStyle(CARD_DEFAULT);
                        cBoard1E.setStyle(CARD_DEFAULT);
                        cBoard2A.setStyle(CARD_DEFAULT);
                        cBoard2B.setStyle(CARD_DEFAULT);
                        cBoard2C.setStyle(CARD_DEFAULT);
                        cBoard2D.setStyle(CARD_DEFAULT);
                        cBoard2E.setStyle(CARD_DEFAULT);
                        bCardPane.setStyle(CARD_FOCUS);
                        board.setFocus(sc);
                    }
                    if (mouseEvent.getClickCount() == 2) {
                        System.out.println("Double clicked");
                        if (board.getPhase().equals(Phase.PLAN)) {
                            board.getActivePlayer().discardBoardCard(schar.getPosition());
                            loadBoard();
                        }
                    }
                }
            }
        });
            target.setStyle(CARD_DEFAULT);
            target.getChildren().add(bCardPane);
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setDraggged(CharacterCard c, int i) {
        this.dragged_char = c;
        this.draggedCharIdx = i;
    }
}