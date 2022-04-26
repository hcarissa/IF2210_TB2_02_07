package com.aetherwars.deck;
import com.aetherwars.card.*;
import java.util.*;

public class Deck {
    private Queue<Card> deckOfCards;
    private CardCollection charCollection;
    private CardCollection levelCollection;
    private CardCollection morphCollection;
    private CardCollection potionCollection;
    private CardCollection swapCollection;

    public Deck(CardCollection charCollection, CardCollection levelCollection, CardCollection morphCollection, CardCollection swapCollection, CardCollection potionCollection){
        this.charCollection = charCollection;
        this.levelCollection = levelCollection;
        this.morphCollection = morphCollection;
        this.swapCollection = swapCollection;
        this.potionCollection = potionCollection;
        this.deckOfCards = new LinkedList<Card>();
        fillDeck();
    }

    public Deck() {
        this.deckOfCards = new LinkedList<Card>();
        fillDeck();
    }

    public boolean isEmpty() {
        return (this.deckOfCards.size() == 0);
    }

    public int getSize() {
        return (this.deckOfCards.size());
    }

    public void addCard(Card card) {
        deckOfCards.add(card);
    }

    public Card remove() {
        return (this.deckOfCards.remove());
    }

    public void fillDeck(){
        CardCollection finalCards = new CardCollection();
        Random r = new Random();
        // char 25, level 5, morph 5, swap 10, potion 15
        for(int i = 0; i < 25; i++){
            int rand = r.nextInt(charCollection.getSize());
            finalCards.addCard(charCollection.getCardIdx(rand));
        }
        for(int i = 0; i < 5; i++){
            int rand = r.nextInt(levelCollection.getSize());
            finalCards.addCard(levelCollection.getCardIdx(rand));
        }
        for(int i = 0; i < 5; i++){
            int rand = r.nextInt(morphCollection.getSize());
            finalCards.addCard(morphCollection.getCardIdx(rand));
        }
        for(int i = 0; i < 10; i++){
            int rand = r.nextInt(swapCollection.getSize());
            finalCards.addCard(swapCollection.getCardIdx(rand));
        }
        for(int i = 0; i < 15; i++){
            int rand = r.nextInt(potionCollection.getSize());
            finalCards.addCard(potionCollection.getCardIdx(rand));
        }
        finalCards.shuffle();
        deckOfCards = new LinkedList<Card>();
        for(int i = 0; i < 60; i++){
            deckOfCards.add(finalCards.getCardIdx(i));
        }
    }


}
