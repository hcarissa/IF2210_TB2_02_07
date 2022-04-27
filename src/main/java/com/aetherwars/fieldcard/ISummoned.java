package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import com.aetherwars.player.*;
import java.util.List;
interface ISummoned {
    // getter
    public CharacterCard getCharacter();
    public List<SpellCard> getActiveSpells();
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
    public <T extends SpellCard> void addSpell(T spell);
    public void render();
}

interface ISpellEffect {
    public void PotionEffect(SpellPotion spellPotion);
    public void LevelEffect(SpellLevel spellLevel);
    public void SwapEffect(SpellSwap spellSwap);
    public void MorphEffect(SpellMorph spellMorph);
}

interface ISummonedBattle {
    // operation
    public double attackModifier(SummonedCharacter enemy);
    public void attackToCharacter(SummonedCharacter enemy);
    public void attackToHp(Player enemy);
}

