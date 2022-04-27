package com.aetherwars.fieldcard;

public class FieldCard implements IFieldCard {
    protected int position;
    protected boolean isDead;

    public FieldCard(int position) {
        this.position = position;
        this.isDead = false;
    }

    // getter
    public int getPosition() {
        return this.position;
    }
    public boolean getIsDead() {
        return this.isDead;
    }

    // setter
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
}
