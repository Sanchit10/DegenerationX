package freecell.model;

import java.util.LinkedList;
import java.util.List;


public class Deck {

  private LinkedList<Card> deckOfCards;

  /**
   * Constructor.
   */
  public Deck() {
    this.deckOfCards = new LinkedList<>();
    int cardPerSuit = 13;
    for (int i = 1; i <= cardPerSuit; i++) {
      int suitsPerDeck = 4;
      for (int j = 1; j <= suitsPerDeck; j++) {
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
