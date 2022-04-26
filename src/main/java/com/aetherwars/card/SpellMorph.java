package com.aetherwars.card;

public class SpellMorph extends SpellCard {

    public SpellMorph() {
        super();
    }

    public SpellMorph(String name, String description, String imagePath, int mana) {
        super(name, description, imagePath, mana);
    }

    @Override
    public String getImagePath() {
        return "com/aetherwars/card/image/spell/morph" + super.getImagePath();
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.MORPH;
    }
    
}