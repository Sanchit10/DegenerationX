public interface Card {

  /**
   * Method returns the Rank class of the desired card.
   *
   * @return String
   */
  public PlayingCard.rankEnum getRank();

  /**
   * Method returns the pip of the desired card.
   *
   * @return String
   */
  public String getSuit();

  /**
   * Returns the unicode symbol of the card.
   *
   * @return char unicode symbol
   */
  public char getSymbol();
}