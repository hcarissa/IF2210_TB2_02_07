package com.aetherwars.card;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CardCollection {
    private List<Card> CardCollection;

    public CardCollection() { CardCollection = new ArrayList<Card>(); }

    public void addCard(Card cd){
        CardCollection.add(cd);
    }

    public Card getCardIdx(int i) { return CardCollection.get(i); }

    public int getSize() { return CardCollection.size(); }

    public List<Card> getCardCollection() { return CardCollection; }

    public void shuffle(){
        Collections.shuffle(CardCollection);
    }
}
