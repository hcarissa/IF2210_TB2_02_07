package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import java.util.List;
interface ISummoned {
    // getter
    public int getPosition();
    public List<SpellCard> getActiveSpells();
    public int getExp();
    public int getNeedsExp();
    public int getTotalExp();
    public int getLvl();

    // operation
    public void earnExp(int exp);
    public void addSpell(SpellCard spell);
}
