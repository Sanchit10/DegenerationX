package interfaces;

import classses.CardRank;
import classses.CardSuit;

public interface Card {
  /**
   * Method returns the Rank class of the desired card.
   *
   * @return Rank
   */
  public CardRank getRank();

  /**
   * Method returns the pip of the desired card.
   *
   * @return int
   */
  public CardSuit getSuit();
}
