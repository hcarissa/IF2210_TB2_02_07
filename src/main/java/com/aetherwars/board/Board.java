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
        for (int i = 0; i < 3; i++) {
            this.p1.draw(3);
            this.p2.draw(3);
        }
        turn = 1; // dimulai dari giliran player1
        round = 1;
        phase = Phase.DRAW;
    }

    public int getTurn() {
        return turn;
    }

    public Phase getPhase() {
        return phase;
    }

    public Player getActivePlayer() {
        if (turn == 1) {
            return p1;
        }
        else {
            return p2;
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
    }

}
