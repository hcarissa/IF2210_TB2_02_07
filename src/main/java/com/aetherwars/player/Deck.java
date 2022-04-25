/* SPEK
  Setiap pemain akan diberikan sebuah deck berisi maksimal 60 kartu dan minimal 40 kartu. Kartu pada deck tidak harus unik, jadi bisa saja memiliki 30 Karakter “Creeper” dan 10 Spell di dalam satu deck. Penyusunan deck ini dilakukan oleh program Anda secara bebas, sehingga alokasi deck dibebaskan pada implementasi Anda. Alokasi deck yang baik adalah deck yang memiliki karakter cukup banyak dengan mana yang cukup variatif, sama halnya dengan alokasi spell dan berbagai jenis spell. Silakan berkreasi untuk membuat penyusunan deck yang baik.
*/
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
  // attributes
  private int deckSize; // jumlah kartu di deck, 40-60
  Queue<Card> deck = new LinkedList<>();

  // constructor
  public Deck(int deckSize) {
    if (deckSize < 40 || deckSize > 60) {
      System.out.println("Deck size must be between 40 and 60"); // TODO: might use exception
      return;
    } else {
      this.deckSize = deckSize;
    }
  }

  // methods
  public void addCard(Card card) {
    if (deck.size() < deckSize) {
      deck.add(card);
    } else {
      System.out.println("Deck is full"); // TODO: might use exception
    }
  }

  public Card draw() {
    return this.deck.remove();
  }

  // public static void main(String[] args) {
  //   Deck d = new Deck(40);
  //   d.addCard(new Card());
  //   System.out.println(d.deck.size());
  //   System.out.println(d.draw().getId());
  //   System.out.println(d.deck.size());
  //   System.out.println(d.draw().getId());
  // }
}
