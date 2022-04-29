package com.aetherwars.card;

import java.net.URL;
import java.util.ResourceBundle;

import com.aetherwars.util.CardReader;
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

    public void settingCard(Card c) {
        try {
            this.name.setText(c.getName());
            this.card = c;
            this.attr.setText(getAttributes(c, c.getCardType()));
            Image image = new Image(c.getImagePath());
            this.img.setImage(image);
        }
        catch (Exception e) {
            System.out.println(c.getName());
            System.out.println(c.getImagePath());
            System.out.println("Exception in CardController " + e);
        }

    }

    public String getAttributes(Card c, CardType t){
        String att = "";
        if(t == CardType.CHARACTER){
            if(c instanceof CharacterCard){
                CharacterCard kartukarakter = (CharacterCard) c;
                att = "ATK " + kartukarakter.getBaseAttack() + "/HP " + kartukarakter.getBaseHealth();
            }
        }else{
            if(c instanceof SpellCard) {
                SpellCard kartuspell = (SpellCard) c;
                if (kartuspell.getSpellType() == SpellType.MORPH) {
                    att = "MANA " + kartuspell.getmana() + "\n" + kartuspell.getSpellType();
                }else if(kartuspell.getSpellType() == SpellType.SWAP){
                    att = "MANA " + kartuspell.getmana() + "\n" + kartuspell.getSpellType();
                }else if(kartuspell.getSpellType() == SpellType.POTION){
                    if(kartuspell instanceof SpellPotion){
                        SpellPotion kartupotion = (SpellPotion) kartuspell;
                        att = "MANA " + kartupotion.getmana() + "\nATK +" + kartupotion.getAttack() + "/HP " + kartupotion.getHealth();
                    }
                }else if(kartuspell.getSpellType() == SpellType.LEVEL){
                    if(kartuspell instanceof SpellLevel){
                        SpellLevel krtulevel = (SpellLevel) kartuspell;
                        att = "LEVEL " + krtulevel.getLevelSwitch();
                    }
                }
            }
        }
        return att;
    }

    public Card getCard() {
        return this.card;
    }

}
