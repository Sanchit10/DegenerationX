package freecell.model;

import java.util.Stack;

public class Card {


  // static because we need this deck in the getdeck method, change if you have a better alternative.

  static Stack<Card> deckOfCards;

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
  Card(int rank, int suit) throws IllegalArgumentException {
    // if inputs are valid
    if ((rank >= 1 && rank <= 13)
        &&
        (suit >= 1 && suit <= 4)) {

      // init arrays of potential values
      int[] rankArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

      suits[] myArray = {suits.DEFAULT, suits.HEARTS, suits.DIAMONDS, suits.CLUBS, suits.SPADES};
      char[] uniArray = {'0', '\u2764', '\u2666', '\u2663', '\u2660'};

      // set fields
      this.rank = rankArray[rank];
      this.cardSuit = myArray[suit];
      this.symbol = uniArray[suit];
    } else {
      throw new IllegalArgumentException("Invalid playing card");
    }


  }

  /**
   * A method that creates a valid deck of 52 cards, 13 in each suit and stores the deck as a stack.
   * of card objects
   */
  private void createDeck() {
    deckOfCards = new Stack<>();
    int CARDS_PER_SUIT = 13;
    for (int i = 1; i <= CARDS_PER_SUIT; i++) {
      int SUITS_PER_DECK = 4;
      for (int j = 1; j <= SUITS_PER_DECK; j++) {
        deckOfCards.add(new Card(i, j));
      }
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




// still need to think about all the getters !





  Enum<suits> getSuit() {
    return this.cardSuit;
  }

  int getRank() {
    return this.rank;
  }

//  char getSymbol(){
//    return this.symbol;
//  }


}





