// Card class for the sake of testing Deck, do not use this in production.
public class Card {
  private int id;
  public Card() {
    this.id = 0;
  }
  public int getId() {
    return this.id;
  }
  public void printInfo() {
    System.out.println("Card id: " + this.id);
  }
}
