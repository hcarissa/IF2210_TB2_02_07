package com.aetherwars.card;

public class SpellMorph extends SpellCard {
    private int targetId;

    public SpellMorph() {
        super();
        this.targetId = 0;
    }

    public SpellMorph(String name, String description, String imagePath, int mana, int targetId) {
        super(name, description, imagePath, mana);
        this.targetId = targetId;
    }

    public int getTargetId() {
        return this.targetId;
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