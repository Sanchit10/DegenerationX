import java.util.Collections;
import java.util.Stack;

public class StandardDeck {
  private Stack<PlayingCard> deckOfCards;
  private final int CARDS_PER_SUIT = 13;
  private final int SUITS_PER_DECK = 4;

  /**
   * Constructor - make a a 52 card deck.
   */
  StandardDeck() {
    this.deckOfCards = new Stack<>();
    for (int i = 0; i < CARDS_PER_SUIT; i++) {
      for (int j = 0; j < SUITS_PER_DECK; j++) {
        this.deckOfCards.add(new PlayingCard(i, j));
      }
    }
  }

  /**
   * Pops a card from the top of the deck.
   *
   * @return Card
   */
  public PlayingCard pullCard() throws Exception {
    if (!this.deckOfCards.isEmpty()) {
      return this.deckOfCards.pop();
    } else throw new Exception("Deck is empty");
  }

  /**
   * Shuffles the deck.
   */
  public void shuffle() {
    Collections.shuffle(this.deckOfCards);
  }
}
