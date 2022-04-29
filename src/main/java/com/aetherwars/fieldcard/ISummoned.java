package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import com.aetherwars.player.*;

// Implements on SummonedCharacter
interface ISummoned {
    // getter
    public CharacterCard getCharacter();
    public double getAttack();
    public double getHealth();
    public int getExp();
    public int getNeedsExp();
    public int getTotalExp();
    public int getLvl();

    // setter
    public void setAttack(double attack);
    public void setHealth(double health);

    // operation
    public void earnExp(int exp);
    public void render();
}

interface ISpellEffect {
    public void PotionEffect(SpellPotion spellPotion);
    public void LevelEffect(SpellLevel spellLevel);
    public void SwapEffect(SpellSwap spellSwap);
    public void MorphEffect(SpellMorph spellMorph, CardCollection characterCollection);

    public void PotionExpired(SpellPotion spellPotion);
    public void SwapExpired();
}

interface ISummonedBattle {
    // operation
    public double attackModifier(SummonedCharacter enemy);
    public void attackToCharacter(SummonedCharacter enemy);
    public void attackToHp(Player enemy);
}

