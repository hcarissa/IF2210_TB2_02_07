package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import javafx.scene.layout.Pane;

import java.util.List;

// Implements on FieldCard
interface IFieldCard {
    // getter
    public Pane getPosition();
    public List<SpellCard> getActiveSpells();
    public boolean getIsDead();

    // setter
    public void setIsDead(boolean isDead);
}

interface ISpellMonitoring {
    public boolean isPotionAvailable();
    public boolean isSwapAvailable();
    // Only add duration SpellSwap if SpellSwap does exist
    public void addSwapDuration(int plusDuration);
}
