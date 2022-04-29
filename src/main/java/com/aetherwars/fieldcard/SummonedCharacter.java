package com.aetherwars.fieldcard;
import com.aetherwars.card.*;
import com.aetherwars.player.*;
import java.util.ArrayList;

// TODO
// - Attack to another card/enemy's HP  - DONE
// - Spells effect to card
    // Potion (TEMP)
    // Level (PERM)
    // Swap (TEMP)  - DONE
    // Morph (PERM) - DONE
// - attackUp & healthUp if level up    - DONE
// - kalo health = 0 (dead) gimana?     - DONE

public class SummonedCharacter extends FieldCard implements ISummoned, ISpellEffect, ISummonedBattle {
    private CharacterCard character;
    private double attack;
    private double health;
    private int exp;
    private int needsExp;
    private int lvl;

    public SummonedCharacter(int position, CharacterCard character) {
        super(position);
        this.character = character;
        this.attack = character.getBaseAttack();
        this.health = character.getBaseHealth();
        this.exp = 0;
        this.needsExp = 1;
        this.lvl = 1;
    }

    // getter
    public CharacterCard getCharacter() {
        return this.character;
    }
    public double getAttack() {
        return this.attack;
    }
    public double getHealth() {
        return this.health;
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

    // setter
    public void setAttack(double attack) {
        this.attack = attack;
    }
    public void setHealth(double health) {
        this.health = health;
    }

    // SummonedCharacter earn exp when (kill enemy's or given mana from player)
    // this method including:
    // - level up transformation
    // - attack & health transformation
    public void earnExp(int exp) {
        int totalExp = getTotalExp();
        int lvlBefore = getLvl();
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

        if (lvlBefore != getLvl()) {
            double modifier = getLvl() - 1;
            setAttack(this.character.getBaseAttack() + (modifier * this.character.getAttackUp()));
            setHealth(this.character.getBaseHealth() + (modifier * this.character.getHealthUp()));
        }
    }
    // adding spell to spellActives with condition
    public <T extends SpellCard> void addSpell(T spell) {
        if(spell.getSpellType() == SpellType.POTION) {
            SpellPotion spellPotion = (SpellPotion)spell;
            if(spellPotion.getDuration() != 0) {
                this.activeSpells.add(spellPotion);
            }
            PotionEffect(spellPotion);
        }
        else if(spell.getSpellType() == SpellType.LEVEL) {
            SpellLevel spellLevel = (SpellLevel)spell;
            LevelEffect(spellLevel);
        }
        else if(spell.getSpellType() == SpellType.SWAP) {
            SpellSwap spellSwap = (SpellSwap)spell;
            if(isSwapAvailable()) {
                // only add the SpellSwap duration if SwapAvailable
                addSwapDuration(spellSwap.getDuration());
            }
            else {
                this.activeSpells.add(spellSwap);
                SwapEffect(spellSwap);
            }
        }
        else {
            SpellMorph spellMorph = (SpellMorph)spell;
            MorphEffect(spellMorph);
        }
    }

    // ISpellEffect Implementation
    // Potion effect to summonedcharacter
    public void PotionEffect(SpellPotion spellPotion){
        this.setHealth(this.getHealth() + spellPotion.getHealth());
        this.setAttack(this.getAttack() + spellPotion.getAttack());

        if(getHealth() <= 0) {
            setIsDead(true);
        }
    }
    // Level effect to summonedcharacter
    public void LevelEffect(SpellLevel spellLevel){
        double lvlBefore = getLvl();
        if (spellLevel.getLevelSwitch() == LevelSwitch.UP && getLvl() < 10) {
            this.lvl++;
        }
        else if (spellLevel.getLevelSwitch() == LevelSwitch.DOWN && getLvl() > 1) {
            this.lvl--;
        } 
        if (lvlBefore != getLvl()) {
            double modifier = getLvl() - 1;
            setAttack(this.character.getBaseAttack() + (modifier * this.character.getAttackUp()));
            setHealth(this.character.getBaseHealth() + (modifier * this.character.getHealthUp()));
        }
        this.exp = 0;
    }
    // Swap effect to summonedcharacter
    public void SwapEffect(SpellSwap spellSwap){
        // Swap attack <-> health for each SpellPotion
        for(SpellCard spell : getActiveSpells()) {
            if(spell.getSpellType() == SpellType.POTION) {
                double temp1 = ((SpellPotion)spell).getAttack();
                ((SpellPotion)spell).setAttack(((SpellPotion)spell).getHealth());
                ((SpellPotion)spell).setHealth(temp1);
            }
        }
        // Swap attack <-> health SummonedCharacter
        double temp2 = getAttack();
        setAttack(getHealth());
        setHealth(temp2);
        // SummonedCharacter will be dead 
        // if after swap health = 0
        if(getHealth() <= 0) {
            setIsDead(true);
        }
    }
    // Morph effect to summonedcharacter
    public void MorphEffect(SpellMorph spellMorph){
        this.character = spellMorph.getCharacter();
        this.activeSpells = new ArrayList<SpellCard>();
        this.attack = spellMorph.getCharacter().getBaseAttack();
        this.health = spellMorph.getCharacter().getBaseHealth();
        this.exp = 0;
        this.needsExp = 1;
        this.lvl = 1;
    }

    // ISummonedBattle Implementation, 
    // return attackModifier (0.5 or 1 or 2) depends on CharacterType
    public double attackModifier(SummonedCharacter enemy) {
        if (this.character.getCharacterType() == CharacterType.OVERWORLD) {
            if (enemy.getCharacter().getCharacterType() == CharacterType.NETHER) {
                return 0.5;
            }
            else if (enemy.getCharacter().getCharacterType() == CharacterType.END) {
                return 2;
            }
        }
        else if (this.character.getCharacterType() == CharacterType.END) {
            if (enemy.getCharacter().getCharacterType() == CharacterType.OVERWORLD) {
                return 0.5;
            }
            else if (enemy.getCharacter().getCharacterType() == CharacterType.NETHER) {
                return 2;
            }
        }
        else if (this.character.getCharacterType() == CharacterType.NETHER) {
            if (enemy.getCharacter().getCharacterType() == CharacterType.END) {
                return 0.5;
            }
            else if (enemy.getCharacter().getCharacterType() == CharacterType.OVERWORLD) {
                return 2;
            }
        }
        return 1;
    }
    // Method to attack enemy summonedcharacter
    public void attackToCharacter(SummonedCharacter enemy) {
        double attackValue = this.attack * this.attackModifier(enemy);
        enemy.setHealth(enemy.getHealth() - attackValue);
        
        if(enemy.getHealth() <= 0) {
            this.earnExp(enemy.getLvl());
            enemy.setIsDead(true);
        }
    }
    // Method to attack enemy Player's HP (not yet synchronize with Player)
    public void attackToHp(Player enemy) {
        enemy.setHp(enemy.getHp() - this.getAttack());
    }

    // rendering SummonedCharacter info for debugging
    public void render() {
        System.out.printf("Position: %d\n", this.position);
        System.out.printf("ID: %s\n", this.character.getId());
        System.out.printf("Name: %s\n", this.character.getName());
        // System.out.printf("Desc: %s\n", this.character.getDescription());
        // System.out.printf("CardType: %s\n", this.character.getType());
        // System.out.printf("Path: %s\n", this.character.getImagePath());

        // System.out.printf("Attack Up: %.2f\n", this.character.getAttackUp());
        // System.out.printf("Health Up: %.2f\n", this.character.getHealthUp());
        // System.out.printf("Base Attack: %.2f\n", this.character.getBaseAttack());
        // System.out.printf("Base Health: %.2f\n", this.character.getBaseHealth());

        System.out.printf("Attack: %.2f\n", this.getAttack());
        System.out.printf("Health: %.2f\n", this.getHealth());
        System.out.printf("CharacterType: %s\n", this.character.getCharacterType());

        System.out.printf("Active Spells:\n");
        for(SpellCard spell : this.activeSpells) {
            System.out.printf("- %s (%s) | ", spell.getName(), spell.getSpellType());
            if(spell.getSpellType() == SpellType.MORPH) {
                SpellMorph spellMorph = (SpellMorph)spell;
                System.out.printf("Target ID - %d\n", spellMorph.getTargetId());
            }
            else if(spell.getSpellType() == SpellType.SWAP) {
                SpellSwap spellSwap = (SpellSwap)spell;
                System.out.printf("Duration - %d\n", spellSwap.getDuration());
            }
        }
        System.out.printf("Status: %d/%d [%d]\n", this.exp, this.needsExp, this.lvl);
        System.out.printf("Is Dead: %s\n", this.isDead);
    }
}
