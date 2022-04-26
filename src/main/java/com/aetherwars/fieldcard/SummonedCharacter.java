package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import java.util.List;
import java.util.ArrayList;

// TODO
// - Attack to another card/enemy's HP
// - Spells effect to card
    // Potion (TEMP)
    // Level (PERM)
    // Swap (TEMP)
    // Morph (PERM)
// - attackUp & healthUp if level up
// - kalo health=0 (dead) gimana?

public class SummonedCharacter extends FieldCard implements ISummoned {
    private CharacterCard character;
    private List<SpellCard> activeSpells;
    private int attack;
    private int health;

    public SummonedCharacter(int position, CharacterCard character) {
        super(position);
        this.character = character;
        this.activeSpells = new ArrayList<SpellCard>();
        this.attack = character.getBaseAttack();
        this.health = character.getBaseHealth();
    }

    public int getAttack() {
        return this.attack;
    }

    public int getHealth() {
        return this.health;
    }

    public List<SpellCard> getActiveSpells() {
        return this.activeSpells;
    }

    public void addSpell(SpellCard spell) {
        this.activeSpells.add(spell);
    }

    public void render() {
        System.out.printf("Position: %d\n", this.position);
        System.out.printf("ID: %s\n", this.character.getId());
        System.out.printf("Name: %s\n", this.character.getName());
        System.out.printf("Desc: %s\n", this.character.getDescription());
        System.out.printf("CardType: %s\n", this.character.getType());
        System.out.printf("Path: %s\n", this.character.getImagePath());

        System.out.printf("Attack Up: %d\n", this.character.getAttackUp());
        System.out.printf("Health Up: %d\n", this.character.getHealthUp());
        System.out.printf("Base Attack: %d\n", this.character.getBaseAttack());
        System.out.printf("Base Health: %d\n", this.character.getBaseHealth());
        System.out.printf("CharacterType: %s\n", this.character.getCharacterType());

        System.out.printf("Active Spells:\n");
        for(SpellCard spell : this.activeSpells) {
            System.out.printf("- %s (%s)\n", spell.getName(), spell.getSpellType());
        }
        System.out.printf("Status: %d/%d [%d]\n", this.exp, this.needsExp, this.lvl);
        System.out.printf("Is Dead: %s\n", this.isDead);
    }

    public static void main(String[] args) {
        System.out.println("- SummonedCharacter -");

        // initialize spells
        SpellPotion spellPotion = new SpellPotion("Potion1", "", "", 11, 12, 13, 14);
        SpellLevel spellLevel = new SpellLevel("Level1", "", "", LevelSwitch.UP);
        SpellSwap spellSwap = new SpellSwap("Swap1", "", "", 31, 32);
        SpellMorph spellMorph = new SpellMorph("Morph1", "", "", 41, 1);

        // initialize SummonedCharacter
        CharacterCard charCard = new CharacterCard("Rava", "Ini Deskripsi","background.jpg", 2, 4, 4, 8, 1, CharacterType.OVERWORLD);
        SummonedCharacter sumChar = new SummonedCharacter(1, charCard);
        
        // spells operation
        sumChar.addSpell(spellPotion);
        sumChar.addSpell(spellLevel);
        sumChar.addSpell(spellSwap);
        sumChar.addSpell(spellMorph);

        // earn exp operation
        sumChar.earnExp(6); // 2/5 [3]
        sumChar.earnExp(7); // 4/7 [4]

        // rendering SummonedCharacter
        sumChar.render();

        System.out.println("- Done -");
    }
}
