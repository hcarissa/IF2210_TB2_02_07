package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import java.util.List;
interface IFieldCard {
    // getter
    public int getPosition();
    public List<SpellCard> getActiveSpells();
    public boolean getIsDead();

    // setter
    public void setIsDead(boolean isDead);
}

interface ISpellMonitoring {
    public boolean isPotionAvailable();
    public boolean isSwapAvailable();
    public void addSwapDuration(int plusDuration);
}
