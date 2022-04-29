package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.ArrayList;
abstract class FieldCard implements IFieldCard, ISpellMonitoring {
    protected Pane position;
    protected List<SpellCard> activeSpells;
    protected boolean isDead;

    public FieldCard(Pane position) {
        this.position = position;
        this.activeSpells = new ArrayList<SpellCard>();
        this.isDead = false;
    }

    // getter
    public Pane getPosition() {
        return this.position;
    }
    public List<SpellCard> getActiveSpells() {
        return this.activeSpells;
    }
    public boolean getIsDead() {
        return this.isDead;
    }

    // setter
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    abstract <T extends SpellCard> void addSpell(T spell, CardCollection characterCollection);

    abstract void adjustSpellDuration();

    public boolean isPotionAvailable() {
        for(SpellCard spell : getActiveSpells()) {
            if(spell.getSpellType() == SpellType.POTION) {
                return true;
            }
        }
        return false;
    }

    public boolean isSwapAvailable() {
        for(SpellCard spell : getActiveSpells()) {
            if(spell.getSpellType() == SpellType.SWAP) {
                return true;
            }
        }
        return false;
    }

    public void addSwapDuration(int plusDuration) {
        for(SpellCard spell : getActiveSpells()) {
            if(spell.getSpellType() == SpellType.SWAP) {
                ((SpellSwap)spell).setDuration(((SpellSwap)spell).getDuration() + plusDuration);
                break;
            }
        }
    }
}
