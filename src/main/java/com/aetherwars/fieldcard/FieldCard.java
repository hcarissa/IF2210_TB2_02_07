package com.aetherwars.fieldcard;

public class FieldCard implements IFieldCard {
    protected int position;
    protected int exp;
    protected int needsExp;
    protected int lvl;
    protected boolean isDead;

    public FieldCard(int position) {
        this.position = position;
        this.exp = 0;
        this.needsExp = 1;
        this.lvl = 1;
        this.isDead = false;
    }

    // getter
    public int getPosition() {
        return this.position;
    }
    public int getExp() {
        return this.exp;
    }
    public int getNeedsExp(){
        return this.needsExp;
    }
    public int getTotalExp() {
        if(this.lvl == 1) {
            return (0 + this.exp);
        }
        else if (this.lvl == 2) {
            return (1 + this.exp);
        }
        else if (this.lvl == 3) {
            return (4 + this.exp);
        }
        else if (this.lvl == 4) {
            return (9 + this.exp);
        }
        else if (this.lvl == 5) {
            return (16 + this.exp);
        }
        else if (this.lvl == 6) {
            return (25 + this.exp);
        }
        else if (this.lvl == 7) {
            return (36 + this.exp);
        }
        else if (this.lvl == 8) {
            return (49 + this.exp);
        }
        else if (this.lvl == 9) {
            return (64 + this.exp);
        }
        else if (this.lvl == 10) {
            return (81 + this.exp);
        }
        else {
            return -1;
        }
    }
    public int getLvl(){
        return this.lvl;
    }
    public boolean getIsDead() {
        return this.isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void earnExp(int exp) {
        int totalExp = getTotalExp();
        totalExp += exp;

        if(totalExp >= 1 && totalExp < 4) {
            this.exp = totalExp - 1;
            this.needsExp = 3;
            this.lvl = 2;
        }
        else if (totalExp >= 4 && totalExp < 9) {
            this.exp = totalExp - 4;
            this.needsExp = 5;
            this.lvl = 3;
        }
        else if (totalExp >= 9 && totalExp < 16) {
            this.exp = totalExp - 9;
            this.needsExp = 7;
            this.lvl = 4;
        }
        else if (totalExp >= 16 && totalExp < 25) {
            this.exp = totalExp - 16;
            this.needsExp = 9;
            this.lvl = 5;
        }
        else if (totalExp >= 25 && totalExp < 36) {
            this.exp = totalExp - 25;
            this.needsExp = 11;
            this.lvl = 6;
        }
        else if (totalExp >= 36 && totalExp < 49) {
            this.exp = totalExp - 36;
            this.needsExp = 13;
            this.lvl = 7;
        }
        else if (totalExp >= 49 && totalExp < 64) {
            this.exp = totalExp - 49;
            this.needsExp = 15;
            this.lvl = 8;
        }
        else if (totalExp >= 64 && totalExp < 81) {
            this.exp = totalExp - 64;
            this.needsExp = 17;
            this.lvl = 9;
        }
        else if (totalExp >= 81 && totalExp < 100) {
            this.exp = totalExp - 81;
            this.needsExp = 19;
            this.lvl = 10;
        }
        else if (totalExp >= 100){
            this.exp = 19;
            this.needsExp = 19;
            this.lvl = 10;
        }
    }
}
