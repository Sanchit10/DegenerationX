package freecell.model;

public class Card {

  private int rank;
  private char symbol;
  private suits cardSuit;

  /**
   * Enumeration represents the suits in a standard deck of cards.
   */
  private enum suits {
    DEFAULT, HEARTS, DIAMONDS, CLUBS, SPADES
  }

  /**
   * Constructor.
   */
  public Card(int rank, int suit) throws IllegalArgumentException {
    // if inputs are valid
    if ((rank >= 1 && rank <= 13)
        &&
        (suit >= 1 && suit <= 4)) {

      // init arrays of potential values
      int[] rankArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};//not necessary
      suits[] myArray = {suits.DEFAULT, suits.HEARTS, suits.DIAMONDS, suits.CLUBS, suits.SPADES};
      char[] uniArray = {'0', '\u2665', '\u2666', '\u2663', '\u2660'};

      // set fields
      this.rank = rankArray[rank];
      this.cardSuit = myArray[suit];
      this.symbol = uniArray[suit];
    } else {
      throw new IllegalArgumentException("Invalid playing card");
    }
  }

  /**
   * A method that is used to return the card object in the form of a string.
   *
   * @return returns the card object in the form a string consisting of its rank and suit symbol
   */
  public String toString() {
    if (this.rank == 1) {
      return "A" + this.symbol;
    }
    if (this.rank == 11) {
      return "J" + this.symbol;
    }
    if (this.rank == 12) {
      return "Q" + this.symbol;
    }
    if (this.rank == 13) {
      return "K" + this.symbol;
    }
    return this.rank + "" + this.symbol;

  }

  /**
   * Getter for the suit of this card.
   */
  public Enum<suits> getSuit() {
    return this.cardSuit;
  }

  /**
   * Getter for the rank of this card.
   */
  public int getRank() {
    return this.rank;
  }

  /**
   * Getter for the symbol of this card.
   */
  char getSymbol() {
    return this.symbol;
  }
}





