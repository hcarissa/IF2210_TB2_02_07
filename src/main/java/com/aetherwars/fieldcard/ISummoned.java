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
    // setter
    public void setAttack(double attack);
    public void setHealth(double health);

    // operation
    public void addSpell(SpellCard spell);
    public void render();
}

interface ISummonedBattle {
    // operation
    public double attackModifier(SummonedCharacter enemy);
    public void attackToCharacter(SummonedCharacter enemy);
    public void attackToHp(Player enemy);
}
