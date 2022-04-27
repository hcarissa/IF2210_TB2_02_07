package com.aetherwars.fieldcard;

interface IFieldCard {
    // getter
    public int getPosition();
    public int getExp();
    public int getNeedsExp();
    public int getTotalExp();
    public int getLvl();
    public boolean getIsDead();

    // setter
    public void setIsDead(boolean isDead);

    // operation
    public void earnExp(int exp);
}
