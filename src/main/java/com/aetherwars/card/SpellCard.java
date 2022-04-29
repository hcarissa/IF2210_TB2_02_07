package com.aetherwars.card;

public abstract class SpellCard extends Card {
    private int mana;

    public SpellCard() {
        super();
        this.mana = 0;
    }

    public SpellCard(String name, String description, String imagePath, int mana) {
        super(name, description, CardType.SPELL, imagePath);
        this.mana = mana;
    }

    public int getmana() {
        return this.mana;
    }

    public void setmana(int mana) {
        this.mana = mana;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Mana: " + this.mana);
    }

    @Override
    public CardType getCardType() {
        return CardType.SPELL;
    }

    public abstract SpellType getSpellType();
}