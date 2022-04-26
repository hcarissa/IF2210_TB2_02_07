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
        this.charCollection = new CardCollection();
        this.levelCollection = new CardCollection();
        this.morphCollection = new CardCollection();
        this.swapCollection = new CardCollection();
        this.potionCollection = new CardCollection();
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
            if(!deckOfCards.isEmpty()){
                finalCards.addCard(charCollection.getCardIdx(r.nextInt(charCollection.getSize())));
            }
        }
        for(int i = 0; i < 5; i++){
            if(!deckOfCards.isEmpty()){
                finalCards.addCard(levelCollection.getCardIdx(r.nextInt(levelCollection.getSize())));
            }

        }
        for(int i = 0; i < 5; i++){
            if(!deckOfCards.isEmpty()){
                finalCards.addCard(morphCollection.getCardIdx(r.nextInt(morphCollection.getSize())));
            }
        }
        for(int i = 0; i < 10; i++){
            if(!deckOfCards.isEmpty()){
                finalCards.addCard(swapCollection.getCardIdx(r.nextInt(swapCollection.getSize())));
            }
        }
        for(int i = 0; i < 15; i++){
            if(!deckOfCards.isEmpty()){
                finalCards.addCard(potionCollection.getCardIdx(r.nextInt(potionCollection.getSize())));
            }
        }
        finalCards.shuffle();
        deckOfCards = new LinkedList<Card>();
        for(int i = 0; i < 60; i++){
            if(!deckOfCards.isEmpty()){
                deckOfCards.add(finalCards.getCardIdx(i));
            }
        }
    }


}
