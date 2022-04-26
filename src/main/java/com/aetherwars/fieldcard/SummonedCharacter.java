package com.aetherwars.fieldcard;

public class SummonedCharacter {
    private int position;
    private int currExp;
    private int needsExp;
    private int currLvl;

    public SummonedCharacter(int position) {
        this.position = position;
        this.currExp = 0;
        this.needsExp = 1;
        this.currLvl = 1;
    }

    public int getPosition() {
        return this.position;
    }
    public int getCurrExp() {
        return this.currExp;
    }
    public int getNeedsExp() {
        return this.needsExp;
    }
    public int getCurrLvl() {
        return this.currLvl;
    }

    public earnExp(int expEarned) {
        this.currExp += expEarned;
        if (getCurrExp() >= getNeedsExp()) {
            this.currExp -= getNeedsExp();
            this.currLvl++;
        }
    }
}
