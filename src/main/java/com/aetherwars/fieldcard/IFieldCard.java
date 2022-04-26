package com.aetherwars.fieldcard;

interface IFieldCard {
    // getter
    public int getPosition();
    public int getExp();
    public int getNeedsExp();
    public int getTotalExp();
    public int getLvl();

    // operation
    public void earnExp(int exp);
}
