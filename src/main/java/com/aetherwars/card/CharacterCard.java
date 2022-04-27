package com.aetherwars.card;

public class CharacterCard extends Card {
    private int id;
    private double attackUp;
    private double healthUp;
    private double baseAttack;
    private double baseHealth;
    private CharacterType characterType;

    public CharacterCard() {
        super();
        this.id = 0;
        this.attackUp = 0;
        this.healthUp = 0;
        this.baseAttack = 0;
        this.baseHealth = 0;
        this.characterType = CharacterType.OVERWORLD;
    }

    public CharacterCard(String name, String description, String imagePath, int attackUp, int healthUp, int baseAttack, int baseHealth, int id, CharacterType characterType) {
        super(name, description, CardType.CHARACTER, imagePath);
        this.id = id;
        this.attackUp = attackUp;
        this.healthUp = healthUp;
        this.baseAttack = baseAttack;
        this.baseHealth = baseHealth;
        this.characterType = characterType;
    }

    public int getId() {
        return this.id;
    }
    
    public double getAttackUp() {
        return attackUp;
    }

    public void setAttackUp(double attackUp) {
        this.attackUp = attackUp;
    }

    public double getHealthUp() {
        return healthUp;
    }

    public void setHealthUp(double healthUp) {
        this.healthUp = healthUp;
    }

    public double getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(double baseAttack) {
        this.baseAttack = baseAttack;
    }

    public double getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(double baseHealth) {
        this.baseHealth = baseHealth;
    }

    public CharacterType getCharacterType() {
        return characterType;
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
        System.out.println("Id: " + this.id);
        System.out.println("Attack Up: " + this.attackUp);
        System.out.println("Health Up: " + this.healthUp);  
        System.out.println("Base Attack: " + this.baseAttack);
        System.out.println("Base Health: " + this.baseHealth);
    }


}
