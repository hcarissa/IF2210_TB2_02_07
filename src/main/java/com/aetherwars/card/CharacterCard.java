package com.aetherwars.card;

public class CharacterCard extends Card {
    private int id;
    private int attackUp;
    private int healthUp;
    private int baseAttack;
    private int baseHealth;
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
