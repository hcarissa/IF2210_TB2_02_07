package com.aetherwars.card;

public class CharacterCard extends Card {
    private int attackUp;
    private int healthUp;
    private int baseAttack;
    private int baseHealth;

    public CharacterCard() {
        super();
        this.attackUp = 0;
        this.healthUp = 0;
        this.baseAttack = 0;
        this.baseHealth = 0;
    }

    public CharacterCard(String name, String description, Type type, String imagePath, int attack, int health, int attackUp, int healthUp, int baseAttack, int baseHealth) {
        super(name, description, type, imagePath);
        this.attackUp = attackUp;
        this.healthUp = healthUp;
        this.baseAttack = baseAttack;
        this.baseHealth = baseHealth;
    }

    public int getAttackUp() {
        return attackUp;
    }

    public void setAttackUp(int attackUp) {
        this.attackUp = attackUp;
    }

    public int getHealthUp() {
        return healthUp;
    }

    public void setHealthUp(int healthUp) {
        this.healthUp = healthUp;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
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
        System.out.println("Attack Up: " + this.attackUp);
        System.out.println("Health Up: " + this.healthUp);  
        System.out.println("Base Attack: " + this.baseAttack);
        System.out.println("Base Health: " + this.baseHealth);
    }


}
