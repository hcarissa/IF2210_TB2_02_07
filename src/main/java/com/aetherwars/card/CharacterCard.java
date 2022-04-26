package com.aetherwars.card;

public class CharacterCard extends Card {
    private int attack;
    private int health;

    public CharacterCard() {
        super();
        this.attack = 0;
        this.health = 0;
    }

    public CharacterCard(String name, String description, Type type, String imagePath, int attack, int health) {
        super(name, description, type, imagePath);
        this.attack = attack;
        this.health = health;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getHealth() {
        return this.health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String getImagePath() {
        return "com/aetherwars/card/image/character/" + super.getImagePath();
    }

    @Override
    public CardType getCardType() {
        return CardType.CHARACTER;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Attack: " + this.attack);
        System.out.println("Health: " + this.health);
    }


}
