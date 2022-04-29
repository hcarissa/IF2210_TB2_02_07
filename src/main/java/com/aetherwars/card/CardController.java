package com.aetherwars.card;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CardController {
    private Card card;
    @FXML
    private ImageView img = new ImageView();
    @FXML
    private Text name, attr;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }

    public void setCard(Card c) {
        try {
            this.card = c;
            Image image = new Image(c.getImagePath());
            this.img.setImage(image);
            this.name.setText(c.getName());
            //this.attr.setText(attr);
        }
        catch (Exception e) {
            System.out.println("Exception in CardController " + e);
        }
    }

    public Card getCard() {
        return this.card;
    }

}
