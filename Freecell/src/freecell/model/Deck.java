package freecell.model;

import java.util.List;
import java.util.Stack;

public class Deck {
  private Stack<Card> deckOfCards;

  /**
   * Constructor.
   */
  public Deck() {
    this.deckOfCards = new Stack<>();
    int CARDS_PER_SUIT = 13;
    for (int i = 1; i <= CARDS_PER_SUIT; i++) {
      int SUITS_PER_DECK = 4;
      for (int j = 1; j <= SUITS_PER_DECK; j++) {
        this.deckOfCards.add(new Card(i, j));
      }
    }
  }

  /**
   * Returns the deck.
   *
   * @return List
   */
  public List<Card> getDeck() {
    return this.deckOfCards;
  }
}
