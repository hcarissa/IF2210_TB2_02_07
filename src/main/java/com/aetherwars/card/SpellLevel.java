package com.aetherwars.card;

public class SpellLevel extends SpellCard {
    private int level;
    private int exp;

    
    public SpellLevel() {
        super();
        this.level = 0;
        this.exp = 0;
    }

    public SpellLevel(String name, String description, CardType type, String imagePath, int mana, int level, int exp) {
        super(name, description, type, imagePath, mana);
        this.level = level;
        this.exp = exp;
    }

    public int getlevel() {
        return this.level;
    }

    public int getexp() {
        return this.exp;
    }

    public void setlevel(int level) {
        this.level = level;
    }

    public void setexp(int exp) {
        this.exp = exp;
    }


    @Override
    public String getImagePath() {
        return "com/aetherwars/card/image/spell/level" + super.getImagePath();
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.LEVEL;
    }
    
}