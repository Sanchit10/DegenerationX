public class PlayingCard implements Card {

  // card rank
  private rankEnum rank;
  // card suit
  private String suit;
  // card symbol
  private char symbol;

  /**
   * Enumeration represents the ranks in a standard deck of cards.
   */
  protected enum rankEnum {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
    EIGHT, NINE, TEN, JACK, QUEEN, KING
  }

  /**
   * Constructor.
   */
  PlayingCard(int rank, int suit) throws IllegalArgumentException {
    // if inputs are valid
    if ((rank >= 0 && rank <= 12)
       &&
       (suit >= 0 && suit <= 3)) {

      // init arrays of potential values
      String[] suitArray = {"Hearts", "Diamonds", "Clubs", "Spades"};
      char[] uniArray = {'\u2764', '\u2666', '\u2663', '\u2660'};
      rankEnum[] enumArray = {rankEnum.ACE, rankEnum.TWO, rankEnum.THREE, rankEnum.FOUR,
              rankEnum.FIVE, rankEnum.SIX, rankEnum.SEVEN, rankEnum.EIGHT, rankEnum.NINE,
              rankEnum.TEN, rankEnum.JACK, rankEnum.QUEEN, rankEnum.KING};

      // set fields
      this.rank = enumArray[rank];
      this.suit = suitArray[suit];
      this.symbol = uniArray[suit];
    } else throw new IllegalArgumentException("Illegal input PlayingCard.PlayingCard()");
  }


  @Override
  public rankEnum getRank() {
    return this.rank;
  }

  @Override
  public String getSuit() {
   return this.suit;
  }

  @Override
  public char getSymbol() {
    return this.symbol;
  }
}
