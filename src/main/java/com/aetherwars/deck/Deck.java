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
        return this.deckOfCards.size();
    }

    public Deck(){
        this.deckOfCards = new LinkedList<>();
        this.neff = new SimpleIntegerProperty(0);
        this.size = 0;
        fillDeck();
    }

    public boolean isNotEmpty(){
        return (this.deckOfCards.size()!=0);
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
        int levelint = r.nextInt(2)+min;
        int swapint = r.nextInt(5)+min;
        int potionint = r.nextInt(7)+min;
        int morphint = r.nextInt(8)+min;
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
            if (x.getSize() > 0) {
                this.deckOfCards.add(x.getCardIdx(rnd.nextInt(x.getSize())));
            } else {
                System.out.println(x.getClass());
            }
        }
    }

    public static void main(String args[]) {
        Random r = new Random();
        int levelint = r.nextInt(2)+5;
        Deck d = new Deck();
    }
}
