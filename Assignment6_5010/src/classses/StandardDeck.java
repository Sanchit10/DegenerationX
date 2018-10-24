package classses;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import interfaces.Card;
import interfaces.Deck;

public class StandardDeck implements Deck {

  private LinkedList<Card> deckOfCards;
  private int currentSize = 52;
  private final int DECK_SIZE = 52;
  private final int CARDS_PER_SUIT = 13;
  private final int SUITS_PER_DECK = 4;

  /**
   * Constructor.
   */
  StandardDeck() {
    this.deckOfCards = new LinkedList<>();
    for (int i = 0; i < CARDS_PER_SUIT; i++) {
      for (int j = 0; j < SUITS_PER_DECK; j++) {
        this.deckOfCards.add(new PlayingCard(new CardSuit(j), new CardRank(i)));
      }
    }
    currentSize = this.DECK_SIZE;
  }

  /**
   * Method returns int to used to compare suites. This int is used in the insertion sort used to
   * sort the deck.
   *
   * @return integer value associated with suite
   * @throws IllegalArgumentException if input is invalid.
   */
  @SuppressWarnings("Duplicates")
  private int intFromSuite(String suite) throws IllegalArgumentException {
    switch (suite.toLowerCase()) {
      case "spades":
        return 0;
      case "hearts":
        return 1;
      case "diamonds":
        return 2;
      case "clubs":
        return 3;
      default:
        throw new IllegalArgumentException("Invalid input for VDECK. INT_FROM_SUITE().");
    }
  }

  /**
   * Method returns int to used to compare rank. This int is used in the insertion sort used to sort
   * the deck.
   *
   * @return integer value associated with rank
   * @throws IllegalArgumentException if input is invalid.
   */
  @SuppressWarnings("Duplicates")
  private int intFromRank(String rank) throws IllegalArgumentException {
    switch (rank.toLowerCase()) {
      case "two":
        return 2;
      case "three":
        return 3;
      case "four":
        return 4;
      case "five":
        return 5;
      case "six":
        return 6;
      case "seven":
        return 7;
      case "eight":
        return 8;
      case "nine":
        return 9;
      case "ten":
        return 10;
      case "jack":
        return 11;
      case "queen":
        return 12;
      case "king":
        return 13;
      case "ace":
        return 14;
      default:
        throw new IllegalArgumentException("Invalid input for VDECK. INT_FROM_RANK().");
    }
  }

  /**
   * This method shuffles the interfaces.Deck.
   */
  @Override
  public void shuffle() {
    Collections.shuffle(this.deckOfCards);
  }

  /**
   * This method cuts the deck.
   */
  @SuppressWarnings("Duplicates")
  @Override
  public void cut(int cutIndex) {
    LinkedList<Card> cutDeck = new LinkedList<>();
    for (int i = 0; i < cutIndex; i++) {
      cutDeck.add(this.deckOfCards.pop());
    }
    for (int i = this.DECK_SIZE - cutIndex; i < this.DECK_SIZE; i++) {
      this.deckOfCards.add(i, cutDeck.pop());
    }
  }

  /**
   * This method pulls a card from somewhere, but I'm not really sure.
   *
   * @return interfaces.Card
   */
  @Override
  public Card pullCard() throws Exception {
    if (!this.deckOfCards.isEmpty()) {
      this.currentSize--;
      return this.deckOfCards.removeFirst();
    } else throw new Exception("SDeck is empty");
  }

  /**
   * This method clears out the deck and returns a boolean.
   *
   * @return Boolean
   */
  @Override
  public Boolean emptyDeck() {
    return this.deckOfCards.isEmpty();
  }

  /**
   * This method sorts the deck by suit and/or rank;
   */
  public void sort(String bySuitOrRankorBoth) {
    switch (bySuitOrRankorBoth.toLowerCase()) {
      case "suite":
        suiteSort();
        break;
      case "rank":
        rankSort();
        break;
      case "both":
        bothSort();
        break;
      default:
        throw new IllegalArgumentException("Invalid argument: VDECK SORT");
    }
  }

  /**
   * Insertion sort used to sort deck.
   *
   * @return LinkedList<classses.PlayingCard>
   */
  @SuppressWarnings("Duplicates")
  private void rankSort() {
    //convert list of cards to array of cards
    this.deckOfCards.sort(Comparator.comparingInt(o -> intFromRank(o.getRank().getName().toLowerCase())));
  }

  /**
   * Insertion sort used to sort deck.
   *
   * @return LinkedList<classses.PlayingCard>
   */
  @SuppressWarnings("Duplicates")
  private void suiteSort() {
    this.deckOfCards.sort(Comparator.comparing(o -> o.getSuit().getName().toLowerCase()));
  }

  /**
   * Insertion sort used to sort deck. Sorry about the mess.
   *
   * @return LinkedList<classses.PlayingCard>
   */
  @SuppressWarnings("Duplicates")
  private void bothSort() {
    this.deckOfCards.sort((o1, o2) -> {
      int compare = o1.getSuit().getName().toLowerCase().compareTo(o2.getSuit().getName().toLowerCase());
      if(compare == 0){
        return Integer.compare(intFromRank(o1.getRank().getName().toLowerCase()), intFromRank(o2.getRank().getName().toLowerCase()));
      }
      return compare;
    });
  }
}
