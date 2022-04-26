package com.aetherwars.card;

public class SpellSwap extends SpellCard {
    private int duration;
    
    public SpellSwap() {
        super();
        this.duration = 0;
    }

    public SpellSwap(String name, String description, String imagePath, int mana, int duration) {
        super(name, description, imagePath, mana);
        this.duration = duration;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
