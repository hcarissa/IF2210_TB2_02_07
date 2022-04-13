package com.aetherwars.board;

import com.aetherwars.model.Player;

public class Board{
    private int turn;
    private Phase phase;
    private Player p1;
    private Player p2;
    private int deck; // jumlah kartu yang ada di deck player aktif
    private int mana; // value mana dari player yang sedang aktif

    // health point, mana, deck, hand udah ada di masing-masing player
    // deskripsi dari setiap kartu ada di Cards
    //private PlayerBoard b1;
    //private PlayerBoard b2;

    public Board(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;

        // masing-masing player ambil 3 kartu dari deck
        for (int i = 0; i < 3; i++) {
            this.p1.draw();
            this.p2.draw();
        }
        turn = 1; // dimulai dari giliran player1
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

    public void setDeck() {
        // TO DO
        // this.deck = getActivePlayer().getDeck();
    }

    public void setMana() {
        // TO DO
        // this.mana = getActivePlayer().getMana();
    }

    public void switchTurn() {
        if (turn == 1) {
            turn = 2;
        }
        else {
            turn = 1;
        }
    }

    public void setPhase(Phase nextPhase) {
        phase = nextPhase;
    }

    // display controller menyusul
}
