package com.aetherwars.fieldcard;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.card.Card;
import com.aetherwars.card.CharacterCard;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class BoardCardController {
    @FXML
    private Pane attack, health, expDetail;

    @FXML
    private ImageView img = new ImageView();

    private CharacterCard card;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

    public void setCard(SummonedCharacter sc) {
        this.card = sc.getCharacter();
        this.attack.getChildren().add(new Text(String.valueOf(sc.getAttack())));
        this.health.getChildren().add(new Text(String.valueOf(sc.getHealth())));
        this.expDetail.getChildren().add(new Text(String.valueOf(sc.getExp()) + "/" + String.valueOf(sc.getNeedsExp()) + " [" + String.valueOf(sc.getLvl()) + "]"));
        Image image = new Image(card.getImagePath());
        this.img.setImage(image);
    }

    public Card getCard() {
        return this.card;
    }
}
