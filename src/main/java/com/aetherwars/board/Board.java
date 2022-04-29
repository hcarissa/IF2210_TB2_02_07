package com.aetherwars.board;

import com.aetherwars.player.*;
import com.aetherwars.Controller.*;

public class Board {
    private int turn;
    private int round; // kalau di spek turn
    private Phase phase;
    private Player p1;
    private Player p2;
    private boolean gameFin;

    // health point, mana, deck, hand udah ada di masing-masing player
    // deskripsi dari setiap kartu ada di Cards

    public Board(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.gameFin = false;

        // masing-masing player ambil 3 kartu dari deck
        this.p1.draw(3);
        this.p2.draw(3);
        turn = 1; // dimulai dari giliran player1
        round = 1;
        phase = Phase.DRAW;
    }

    public int getTurn() {
        return turn;
    }

    public int getRound() { return round; }

    public Phase getPhase() {
        return phase;
    }

    public Player getActivePlayer() {
        if (this.turn == 1) {
            System.out.println("p1");
            return this.p1;
        }
        else {
            System.out.println("p2");
            return this.p2;
        }
    }

    public void switchTurn() {
        if (turn == 1) {
            turn = 2;
        }
        else {
            turn = 1;
            round++;
        }
        this.getActivePlayer().setMana(Math.min(round, 10));
    }

    public void setPhase(Phase nextPhase) {
        phase = nextPhase;
        action(nextPhase);
    }

    public void action(Phase phase) {
        if (phase.equals(Phase.DRAW) && round != 1) {
            this.getActivePlayer().drawCard();
        } else if (phase.equals(Phase.PLAN)) {

        } else if (phase.equals(Phase.ATTACK)) {

        }
    }

    public boolean isWinner(Player p) {
        if (p == p1) {
            if (p1.getDeckCount() == 0 || p2.getHp() <= 0) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (p2.getDeckCount() == 0 || p1.getHp() <= 0) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public boolean isFinished() {
        return this.gameFin;
    }

    public void setFinished() {
        this.gameFin = true;
    }

    public static void main(String[] args) {
        Player p = new Player("p");
        p.draw(3);
        p.viewHand();
    }

}
