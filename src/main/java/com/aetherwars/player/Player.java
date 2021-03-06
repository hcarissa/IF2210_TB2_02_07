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
import com.aetherwars.card.*;
import com.aetherwars.deck.Deck;
import com.aetherwars.fieldcard.SummonedCharacter;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
  // attributes
  private String name;
  private double hp;
  private int mana;
  private Deck deck;
  private List<Card> hand;
  private Card[] tempCard;
  private SummonedCharacter[] bCards;
  
  // constructor
  public Player(String name) {
    this.name = name;
    this.hp = 80;
    this.mana = 1;
    this.deck = new Deck();
    this.hand = new ArrayList<Card>();
    this.tempCard = new Card[3];
    this.bCards = new SummonedCharacter[5];
  }
  // methods

  // Mengambil kartu baru
  public void draw(int n) {
    // method untuk mengambil n buah kartu dari deck dan menambahkan ke hand
    // bila deck habis atau hand penuh, tidak terjadi apa-apa
    for (int i = 0; i < n && this.deck.isNotEmpty(); i++) {
      this.hand.add(this.deck.remove());
    }
  }

  public void drawCard() {
    // method untuk mengambil n buah kartu dari deck dan menambahkan ke hand
    // bila deck habis atau hand penuh, tidak terjadi apa-apa
    for (int i = 0; i < 3 && this.deck.isNotEmpty(); i++) {
      this.tempCard[i] = this.deck.remove();
    }
    this.viewTempCard();
  }

  // Memilih kartu yang di-draw
  public void chooseCard(int n) {
    // memilih kartu dengan indeks n
    if (n < 0 || n > 2) { // indeks harus 0-2
      return;
    }
    this.hand.add(tempCard[n]);
    for (int i = 0; i < tempCard.length; i++) {
      if (i != n && tempCard[i] != null) {
        this.deck.addCard(tempCard[i]); // balikin kartu yang ga dipilih ke deck
        tempCard[i] = null;
      }
    }
    while (this.hand.size()>5) {
      // buang 1 kartu random (dari 5 yang lama, jangan buang yang baru, sayang wkwk)
      Random r = new Random();
      this.discardCard(r.nextInt(5));
    }
  }

  // Melihat 3 kartu yang di-draw
  public void viewTempCard() {
    System.out.println("Choose 1 out of these three cards: ");
    for (Card card : this.tempCard) {
      if (card != null) {
        card.printInfo();
      }
    }
  }

  // Melihat deskripsi dan atribut kartu hand maupun board
  public void viewHand() {
    // method untuk melihat kartu yang ada di hand
    // menampilkan deskripsi kartu yang ada di hand
    System.out.println("Your hand: ");
    for (Card card : this.hand) {
      if (card != null) {
        card.printInfo();
      }
    }
  }

  // Mengeluarkan kartu / memindahkan kartu dari hand ke board
  public void playCard(int index, int bIdx, Pane position) {
    // method untuk mengeluarkan kartu dari hand ke board
    // index harus antara 0 - 4
    // bila index tidak valid, tidak terjadi apa-apa
    if (index < 0 || index > 4) {
      return;
    }
    if (this.hand.get(index).getCardType().equals(CardType.CHARACTER)) {
      SummonedCharacter sc = new SummonedCharacter(position, (CharacterCard) this.hand.remove(index));
      this.bCards[bIdx] = sc;
    }
  }

  public void addBoardCard(SummonedCharacter sc, int index) {
    if (bCards[index] != null) {
      this.bCards[index] = sc;
    }
  }

  public void discardBoardCard(Pane position) {
    // method untuk membuang kartu di board pada phase plan
    boolean found = false;
    for (int i=0; i<5 && !found; i++) {
      if (bCards[i].getPosition() == position) {
        found = true;
        bCards[i] = null;
      }
    }
  }

  public void discardCard(int index) {
    // method untuk membuang kartu (digunakan bila di akhir draw phase jumlah kartu > 5)
    if (index < 0 || index > 4) {
      return;
    }
    this.hand.remove(index);
  }

  public int getDeckCount() {
    return this.deck.getSize();
  }

  public int getMana() {
    return this.mana;
  }

  public void setMana(int mana) {
    this.mana = mana;
  }

  public List<Card> getHand() {
    return this.hand;
  }

  public Card[] getTemp() {
    return this.tempCard;
  }

  public double getHp() {
    return this.hp;
  }

  public SummonedCharacter[] getbCards() {
    return this.bCards;
  }

  public void setHp(double hp) { this.hp = hp; }

  public String getName() { return this.name; }

  // driver
  public static void main(String[] args) {
    Player p = new Player("Player 1");
    p.draw(4);
    p.viewHand();
    System.out.println(p.deck.isNotEmpty());
    p.drawCard();
    p.viewTempCard();
    p.chooseCard(1);
    p.viewHand();
    p.drawCard();
    p.viewTempCard();
    p.chooseCard(1);
    System.out.println(p.hand.size());
  }
}
