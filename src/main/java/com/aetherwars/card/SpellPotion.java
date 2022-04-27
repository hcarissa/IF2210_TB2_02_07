package com.aetherwars.card;

public class SpellPotion extends SpellCard {
    private double attack;
    private double health;
    private int duration;
    
    public SpellPotion() {
        super();
        this.attack = 0;
        this.health = 0;
        this.duration = 0;
    }

    public SpellPotion(String name, String description, String imagePath, int mana, double attack, double health, int duration) {
        super(name, description, imagePath, mana);
        this.attack = attack;
        this.health = health;
        this.duration = duration;
    }

    public double getAttack() {
        return this.attack;
    }

    public double getHealth() {
        return this.health;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    @Override
    public String getImagePath() {
        return "com/aetherwars/card/image/spell/potion" + super.getImagePath();
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.POTION;
    }
    
}