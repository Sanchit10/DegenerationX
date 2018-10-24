package classses;

import interfaces.Card;

/**
 * This class provides the implementation associated with the interfaces.Card interface.
 *
 * @author James Borzillieri
 */
public class PlayingCard implements Card {
  private final CardRank rank;
  private final CardSuit suit;

  /**
   * Constructor.
   *
   * @param suit
   * @param rank
   */
  public PlayingCard(CardSuit suit, CardRank rank){
    this.suit = suit;
    this.rank = rank;
  }

  /**
   * Method returns the Rank class of the desired card.
   *
   * @return Rank
   */
  public CardRank getRank(){
    return this.rank;
  }

  /**
   * Method returns the pip of the desired card.
   *
   * @return Suite
   */
  public CardSuit getSuit(){
    return this.suit;
  }
}
