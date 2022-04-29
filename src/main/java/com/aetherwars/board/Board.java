package com.aetherwars.board;

import com.aetherwars.player.*;

public class Board {
    private int turn;
    private int round; // kalau di spek turn
    private Phase phase;
    private Player p1;
    private Player p2;

    // health point, mana, deck, hand udah ada di masing-masing player
    // deskripsi dari setiap kartu ada di Cards

    public Board(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;

        // masing-masing player ambil 3 kartu dari deck
        this.p1.draw(3);
        this.p2.draw(3);
        p1.viewHand();
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
    }

    public void setPhase(Phase nextPhase) {
        phase = nextPhase;
        action(nextPhase);
    }

    public void action(Phase phase) {
        if (phase.equals(Phase.DRAW) && round != 1) {
            System.out.println("Action draaww");
            this.getActivePlayer().drawCard();
        }
    }

    public static void main(String[] args) {
        Player p = new Player("p");
        p.draw(3);
        p.viewHand();
    }

}
