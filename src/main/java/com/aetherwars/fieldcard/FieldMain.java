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

        // TESTING SPELLS
        summon1.addSpell(spellMorph);   // 8/10
        summon1.addSpell(spellPotion);  // 20/23
        summon1.addSpell(spellLevel);   // (should be 12/18 at Level 2); but there's potion 20/23
        summon1.addSpell(spellSwap);    // 23/20
        summon1.render();

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

        // TESTING SPELLLEVEL
            // SpellLevel spellLevel1 = new SpellLevel("Level1", "", "", LevelSwitch.UP);
            // SpellLevel spellLevel2 = new SpellLevel("Level1", "", "", LevelSwitch.DOWN);
            // System.out.println("- Summon1 Before -");
            // summon1.render();

            // summon1.earnExp(5);
            // System.out.println("- Summon1 After1 -");
            // summon1.render();

            // summon1.addSpell(spellLevel1);
            // System.out.println("- Summon1 After2 -");
            // summon1.render();

            // summon1.addSpell(spellLevel2);
            // System.out.println("- Summon1 After3 -");
            // summon1.render();
        // TESTING SPELLLEVEL

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
