package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import com.aetherwars.player.*;
import java.util.List;
import java.util.ArrayList;

// File for testing features
public class FieldMain {
    public static void main(String[] args) {
        List<CharacterCard> characterCards = new ArrayList<CharacterCard>();
        CharacterCard charCard1 = new CharacterCard("Rava", "Deskripsi1","background.jpg", 2, 4, 4, 8, 1, CharacterType.NETHER);
        CharacterCard charCard2 = new CharacterCard("Attar", "Deskripsi2","background.jpg", 4, 8, 8, 10, 2, CharacterType.END);
        characterCards.add(charCard1);
        characterCards.add(charCard2);

        System.out.println("== SUMMONEDCHARACTER ==");

        // initialize spells
        SpellPotion spellPotion = new SpellPotion("Potion1", "", "", 11, 12, 13, 14);
        SpellLevel spellLevel = new SpellLevel("Level1", "", "", LevelSwitch.UP);
        SpellSwap spellSwap = new SpellSwap("Swap1", "", "", 31, 32);
        SpellMorph spellMorph = new SpellMorph("Morph1", "", "", 4, 2, characterCards);

        // initialize SummonedCharacter
        SummonedCharacter summon1 = new SummonedCharacter(1, charCard1);
        SummonedCharacter summon2 = new SummonedCharacter(2, charCard2);

        // spells operation
        // summon1.addSpell(spellPotion);
        // summon1.addSpell(spellLevel);
        // summon1.addSpell(spellSwap);
        // summon1.addSpell(spellMorph);

        // TESTING SPELLSWAP
            // SpellSwap spellSwap1 = new SpellSwap("Swap1", "swap1", "", 10, 3);
            // SpellSwap spellSwap2 = new SpellSwap("Swap2", "swap2", "", 5, 5);
            // System.out.println("- Summon1 Before -");
            // summon1.render();
            
            // // summon1.addSpell(spellSwap1);
            // summon1.addSpell(spellSwap2);
            
            // System.out.println("- Summon1 After -");
            // summon1.render();
        // TESTING SPELLSWAP
        
        // TESTING SPELLPOTION
            // SpellPotion spellPotion1 = new SpellPotion("Potion1", "", "", 10, 5, 2, 5);
            // SpellPotion spellPotion2 = new SpellPotion("Potion2", "", "", 2, -2, -4, 0);
            // System.out.println("- Summon1 Before -");
            // summon1.render();

            // summon1.addSpell(spellPotion1);
            
            // System.out.println("- Summon1 After1 -");
            // summon1.render();
            
            // summon1.addSpell(spellPotion2);
            
            // System.out.println("- Summon1 After2 -");
            // summon1.render();
        // TESTING SPELLPOTION

        // TESTING SPELLMORPH
            // summon1.earnExp(5);
            // System.out.println("- Summon1 Before -");
            // summon1.render();
            
            // // morph to target id = 2
            // summon1.addSpell(spellMorph);
            
            // System.out.println("- Summon1 After -");
            // summon1.render();
        // TESTING SPELLMORPH

        
        
        System.out.println("== DONE ==");
    }
}
