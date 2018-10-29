package freecell.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Deck {
  private LinkedList<Card> deckOfCards;

  /**
   * Constructor.
   */
  public Deck() {
    this.deckOfCards = new LinkedList<>();
    int CARDS_PER_SUIT = 13;
<<<<<<< HEAD
    for (int i =CARDS_PER_SUIT; i > 0; i--) {
=======
    for (int i = CARDS_PER_SUIT; i >0; i--) {
>>>>>>> 0db79d5a64ee0dd8afbf6fef670b2ebf61246fd1
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
