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

    public SummonedCharacter(int position, CharacterCard character) {
        super(position);
        this.character = character;
        this.activeSpells = new ArrayList<SpellCard>();
    }

    public List<SpellCard> getActiveSpells() {
        return this.activeSpells;
    }

    public void addSpell(SpellCard spell) {
        this.activeSpells.add(spell);
    }

    public void render() {
        System.out.printf("Position: %d\n", this.position);
        System.out.printf("Name: %s\n", this.character.getName());
        System.out.printf("Desc: %s\n", this.character.getDescription());
        System.out.printf("Type: %s\n", this.character.getType());
        System.out.printf("Path: %s\n", this.character.getImagePath());
        // System.out.printf("Attack: %d\n", this.character.getAttack());
        // System.out.printf("Health: %d\n", this.character.getHealth());

        System.out.printf("Active Spells:\n");
        for(SpellCard spell : this.activeSpells) {
            System.out.printf("- %s (%s)\n", spell.getName(), spell.getSpellType());
        }
        System.out.printf("Status: %d/%d [%d]\n", this.exp, this.needsExp, this.lvl);
    }

    public static void main(String[] args) {
        System.out.println("- SummonedCharacter -");

        // initialize spells
        SpellPotion spellPotion = new SpellPotion("Potion1", "", Type.END, "", 11, 12, 13, 14);
        SpellLevel spellLevel = new SpellLevel("Level1", "", Type.END, "", 21, 22, 23);
        SpellSwap spellSwap = new SpellSwap("Swap1", "", Type.END, "", 31, 32, 33, 34);
        SpellMorph spellMorph = new SpellMorph("Morph1", "", Type.END, "", 41);

        // initialize SummonedCharacter
        CharacterCard charCard = new CharacterCard("Rava", "Ini Deskripsi", Type.OVERWORLD,"background.jpg", 20, 80);
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
