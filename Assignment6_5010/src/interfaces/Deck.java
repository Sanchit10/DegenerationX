package interfaces;

public interface Deck {
  /**
   * this method shuffles the interfaces.Deck.
   */
  public void shuffle();

  /**
   * This method sorts the deck by suit and/or rank;
   *
   * @param bySuitOrRankorBoth
   */
  public void sort(String bySuitOrRankorBoth);

  /**
   * This method sorts the deck.
   *
   * @param cutIndex
   */
  public void cut(int cutIndex);

  /**
   * This method pulls a card from somewhere, but I'm not really sure.
   *
   * @return interfaces.Card
   */
  public Card pullCard() throws Exception;

  /**
   * This method clears out the deck and returns a boolean.
   *
   * @return Boolean
   */
  public Boolean emptyDeck();
}
