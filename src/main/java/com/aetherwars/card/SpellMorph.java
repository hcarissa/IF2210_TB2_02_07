package com.aetherwars.card;
import java.util.List;

public class SpellMorph extends SpellCard {
    private int targetId;
    private CharacterCard character;

    public SpellMorph() {
        super();
        this.targetId = 0;
        this.character = new CharacterCard();
    }

    public SpellMorph(String name, String description, String imagePath, int mana, int targetId, List<CharacterCard> characters) {
        super(name, description, imagePath, mana);
        this.targetId = targetId;

        for(CharacterCard character : characters) {
            if(character.getId() == targetId) {
                this.character = character;
                break;
            }
        }
    }

    public int getTargetId() {
        return this.targetId;
    }

    public CharacterCard getCharacter() {
        return this.character;
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