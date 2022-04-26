package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import java.util.List;
interface ISummoned {
    // getter
    public List<SpellCard> getActiveSpells();

    // operation
    public void addSpell(SpellCard spell);
}
