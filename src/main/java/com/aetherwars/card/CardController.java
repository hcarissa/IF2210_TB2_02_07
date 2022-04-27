package com.aetherwars.card;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CardController {
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

    public void setCard(String url, String name, String attr) {
        Image image = new Image(url);
        this.img.setImage(image);
        this.name.setText(name);
        this.attr.setText(attr);
    }
}
