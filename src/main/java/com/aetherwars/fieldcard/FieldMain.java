package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import com.aetherwars.player.*;
import java.util.List;
import java.util.ArrayList;

public class FieldMain {
    public static void main(String[] args) {
        List<CharacterCard> characterCards = new ArrayList<CharacterCard>();
        CharacterCard charCard1 = new CharacterCard("Rava", "Ini Deskripsi","background.jpg", 2, 4, 4, 8, 1, CharacterType.NETHER);
        CharacterCard charCard2 = new CharacterCard("Attar", "Ini Deskripsi","background.jpg", 2, 4, 4, 8, 2, CharacterType.END);
        characterCards.add(charCard1);
        characterCards.add(charCard2);

        System.out.println("- SummonedCharacter -");

        // initialize spells
        SpellPotion spellPotion = new SpellPotion("Potion1", "", "", 11, 12, 13, 14);
        SpellLevel spellLevel = new SpellLevel("Level1", "", "", LevelSwitch.UP);
        SpellSwap spellSwap = new SpellSwap("Swap1", "", "", 31, 32);
        SpellMorph spellMorph = new SpellMorph("Morph1", "", "", 41, 1, characterCards);

        // initialize SummonedCharacter
        SummonedCharacter summon1 = new SummonedCharacter(1, charCard1);
        SummonedCharacter summon2 = new SummonedCharacter(2, charCard2);
        
        // spells operation
        // summon1.addSpell(spellPotion);
        // summon1.addSpell(spellLevel);
        // summon1.addSpell(spellSwap);
        summon1.addSpell(spellMorph);

        // battles
        // summon1.attackToCharacter(summon2);

        System.out.println("- Summon1 -");
        summon1.render();

        // earn exp operation
        // summon1.earnExp(6); // 2/5 [3]
        // summon1.earnExp(7); // 4/7 [4]

        // rendering SummonedCharacter
        // summon1.earnExp(1);
        // attack: 6
        // health: 12
        // lvl: 0/3 [2]
        // System.out.println("- Summon1 -");
        // summon1.render();
        
        // summon1.earnExp(7);
        // attack: 8
        // health: 16
        // lvl: 4/5 [3]
        // System.out.println("- Summon1 -");
        // summon1.render();
        // System.out.println("- Summon2 -");
        // summon2.render();

        System.out.println("- Done -");
    }
}
