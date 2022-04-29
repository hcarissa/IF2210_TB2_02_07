package com.aetherwars.deck;
import com.aetherwars.card.*;
import com.aetherwars.util.*;
import java.util.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Deck {
    private Queue<Card> deckOfCards;
    private IntegerProperty neff;
    private int size;

    public int getSize(){
        return this.size;
    }

    public Deck(){
        this.deckOfCards = new LinkedList<>();
        this.neff = new SimpleIntegerProperty(0);
        this.size = 0;
        fillDeck();
    }

    public boolean isNotEmpty(){
        return (this.neff.getValue()!=0);
    }

    public Card remove(){
        this.neff.setValue(this.neff.getValue()-1);
        return this.deckOfCards.remove();
    }

    public void addCard(Card card){
        this.deckOfCards.add(card);
        this.neff.setValue(this.neff.getValue()+1);
    }

    public void fillDeck(){
        int min = 5;
        Random r = new Random();
        CardReader cards = CardReader.getInstance();
        int levelint = r.nextInt(7-min)+min;
        int swapint = r.nextInt(10-min)+min;
        int potionint = r.nextInt(12-min)+min;
        int morphint = r.nextInt(13-min)+min;
        int charint = 60-levelint-swapint-potionint-morphint;
        CardCollection chars = cards.getCharacterCardCollection();
        CardCollection lev = cards.getLevelCardCollection();
        CardCollection swap = cards.getSwapCardCollection();
        CardCollection potion = cards.getPotionCardCollection();
        CardCollection morph = cards.getMorphCardCollection();
        addSpecificCard(chars, charint);
        addSpecificCard(lev, levelint);
        addSpecificCard(swap, swapint);
        addSpecificCard(potion, potionint);
        addSpecificCard(morph, morphint);
    }

    public void addSpecificCard(CardCollection x, int j){
        Random rnd = new Random();
        for(int i = 0; i < j; i++){
            this.deckOfCards.add(x.getCardIdx(rnd.nextInt(x.getSize())));
        }
    }


}
