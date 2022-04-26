package com.aetherwars.card;

public class SpellLevel extends SpellCard {
    private LevelSwitch levelSwitch;

    public SpellLevel() {
        super();
        this.levelSwitch = LevelSwitch.UP;
    }

    public SpellLevel(String name, String description, String imagePath, LevelSwitch levelSwitch) {
        super(name, description, imagePath, 0);
        this.levelSwitch = levelSwitch;
    }

    public LevelSwitch getLevelSwitch() {
        return this.levelSwitch;
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