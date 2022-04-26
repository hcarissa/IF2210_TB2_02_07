/* SPEK
  Setiap pemain memiliki:
  - Nama yang di hardcode (contoh: Player 1, Player 2, Steve, Alex)
  - Atribut health points (HP) sebesar 80,
  - Mana yang jumlahnya bertambah setiap gilirannya,
  - Deck yang berisi 40 - 60 kartu,
  - Kartu hand sejumlah maksimal 5 kartu.
  Ketika bermain, pemain dapat melakukan beberapa aksi, yakni:
  - Mengambil kartu baru,
  - Melihat deskripsi dan atribut kartu hand maupun board,
  - Mengeluarkan kartu / memindahkan kartu dari hand ke board,
  - Menyerang musuh, dan
  - Beralih ke fase selanjutnya.
  Beberapa aksi terbatas pada fase tertentu. Lihat bagian Gameplay untuk lebih jelasnya.
*/
package com.aetherwars.player;
import com.aetherwars.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class Player {
  // attributes
  private String name;
  private int hp;
  private int mana;
  Queue<Card> deck; // berisi 40-60 kartu
  private int deckSize;
  private List<Card> hand;
  
  // constructor
  public Player(String name, int deckSize) {
    if (deckSize < 40 || deckSize > 60) {
      System.out.println("Deck size must be between 40 and 60"); // TODO: might use exception
      return;
    } 
    this.name = name;
    this.hp = 80;
    this.mana = 0;
    this.deck = new LinkedList<>();
    this.deckSize = deckSize;
    this.hand = new ArrayList<Card>();
  }
  // methods
  public void addCard(Card card) {
    // menambahkan kartu ke deck
    if (deck.size() >= deckSize) {
      System.out.println("Deck is full"); // TODO: might use exception
    } else {
      deck.add(card);
    }
  }

  // Mengambil kartu baru
  public void draw(int n) {
    // method untuk mengambil n buah kartu dari deck dan menambahkan ke hand
    // bila deck habis atau hand penuh, tidak terjadi apa-apa
    for (int i = 0; i < n && this.deck.size() > 0 && this.hand.size() < 5; i++) {
      this.hand.add(this.deck.remove());
    }
  }

  // Melihat deskripsi dan atribut kartu hand maupun board
  public void viewHand() {
    // method untuk melihat kartu yang ada di hand
    // menampilkan deskripsi kartu yang ada di hand
    System.out.println("Your hand: ");
    for (Card card : this.hand) {
      card.printInfo();
    }
  }

  // Mengeluarkan kartu / memindahkan kartu dari hand ke board
  public void playCard(int index) {
    // method untuk mengeluarkan kartu dari hand ke board
    // index harus antara 0 - 4
    // bila index tidak valid, tidak terjadi apa-apa
    if (index < 0 || index > 4) {
      return;
    }
    this.hand.remove(index); // TODO: belum ditaro di board, baru dibuang
  }

  // Menyerang musuh
  public void attack() {
    // TODO: implement
  }

  // Beralih ke fase selanjutnya
  public void nextPhase() {
    // TODO: implement
  }

//  public static void main(String[] args) {
//    Player p = new Player("Player 1", 40);
//    p.addCard(new Card());
//    p.addCard(new Card());
//    p.addCard(new Card());
//    p.addCard(new Card());
//    p.addCard(new Card());
//    p.addCard(new Card());
//    System.out.println(p.deck.size());
//    System.out.println(p.hand.size());
//    p.draw(6);
//    System.out.println(p.deck.size());
//    System.out.println(p.hand.size());
//    // System.out.println(p.deck.size());
//    // p.draw(1);
//  }
}
