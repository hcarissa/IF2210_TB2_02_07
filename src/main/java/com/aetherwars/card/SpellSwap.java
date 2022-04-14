package com.aetherwars.card;

public class SpellSwap extends Spell {
    private int attack;
    private int health;

    
    public SpellSwap() {
        super();
        this.attack = 0;
        this.health = 0;
    }

    public SpellSwap(String name, String description, Type type, String imagePath, int mana, int attack, int health) {
        super(name, description, type, imagePath, mana);
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
        return "com/aetherwars/card/image/spell/swap" + super.getImagePath();
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.SWAP;
    }
    
}