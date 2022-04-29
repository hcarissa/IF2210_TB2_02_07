package com.aetherwars.fieldcard;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.card.Card;
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

    private SummonedCharacter card;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

    public void setCard(SummonedCharacter c) {
        this.card = c;
        this.attack.getChildren().add(new Text(String.valueOf(c.getAttack())));
        this.health.getChildren().add(new Text(String.valueOf(c.getHealth())));
        this.expDetail.getChildren().add(new Text(String.valueOf(c.getExp()) + "/" + String.valueOf(c.getNeedsExp()) + " [" + String.valueOf(c.getLvl()) + "]"));
        Image image = new Image(c.getCharacter().getImagePath());
        this.img.setImage(image);
    }
}
