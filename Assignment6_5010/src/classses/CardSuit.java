package classses;

public class CardSuit {

  private final cardSuit suit;
  private final char cardSymbol;

  /**
   * Enumeration provides a set of constants to represent card suites to a standard deck of cards.
   */
  private enum cardSuit{
    SPADES,
    HEARTS,
    DIAMONDS,
    CLUBS
  }

  /**
   * Constructor.
   * @param suit
   */
  public CardSuit(int suit){
    switch(suit){
      case 0:
        this.suit = cardSuit.HEARTS;
        this.cardSymbol = '\u2764';
        break;
      case 1:
        this.suit = cardSuit.DIAMONDS;
        this.cardSymbol = '\u2666';
        break;
      case 2:
        this.suit = cardSuit.CLUBS;
        this.cardSymbol = '\u2663';
        break;
      case 3:
        this.suit = cardSuit.SPADES;
        this.cardSymbol = '\u2660';
        break;
      default:
        throw new IllegalArgumentException("Invaid input for CARDSUIT constructor.");
    }
  }

  /**
   * Getter for name of the suite.
   *
   * @return String
   */
  public String getName() {
    return this.suit.name();
  }

  /**
   * Getter for the symbol of the suite.
   *
   * @return char
   */
  public char getSymbol() {
    return this.cardSymbol;
  }
}
