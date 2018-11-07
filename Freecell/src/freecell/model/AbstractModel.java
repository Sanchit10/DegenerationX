package freecell.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class AbstractModel implements FreecellOperations {

  private boolean isGameStarted = false;
  private boolean moveMade = false;
  int numberOfCascadePiles;
  int numberOfOpenPiles;
  private int numberOfFoundationPiles;

  LinkedList[][] gameStacks;

  //abstract builder
  abstract static class Builder<T extends Builder<T>> implements FreecellOperationsBuilder{
    //builder fields
    int cascades = 8;
    int opens = 4;

    //add
    public T cascades(int c) {
      if (c < 4) {
        throw new IllegalArgumentException("The game can only have cascade piles between 4 and 8");
      }
      this.cascades = c;
      return self();
    }

    //add
    public T opens(int o) {
      if (o < 1) {
        throw new IllegalArgumentException("The game can only have cascade piles between 4 and 8");
      }
      this.opens = o;
      return self();
    }

    //abstract build declaration
    public abstract AbstractModel build();

    //subclasses must override
    protected abstract T self();
  }

  AbstractModel(Builder<?> builder){
    this.numberOfCascadePiles = builder.cascades;
    this.numberOfOpenPiles = builder.opens;
    this.numberOfFoundationPiles = 4;
    this.gameStacks = new LinkedList[3][];
  }



//  /**
//   * Constructor used in builder class.
//   */
//  public AbstractModel(int numberOfCascadePiles, int numberOfOpenPiles) {
//    this.numberOfCascadePiles = numberOfCascadePiles;
//    this.numberOfOpenPiles = numberOfOpenPiles;
//    this.numberOfFoundationPiles = 4;
//    this.gameStacks = new LinkedList[3][];
//  }
//
//  /**
//   * Gets the builder for this model.
//   */
//  public static FreecellModelBuilder getBuilder() {
//    return new FreecellModelBuilder();
//  }
//
//  public static FreecellModelBuilder1 getBuilder1() {
//    return new FreecellModelBuilder1();
//  }
//
//  /**
//   * Nested builder class that implements the FreecellOperationsBuilder.
//   */
//  public static class FreecellModelBuilder implements FreecellOperationsBuilder {
//
//    private int numberOfCascadePiles;
//    private int numberOfOpenPiles;
//    private int numberOfFoundationPiles;
//
//    /**
//     * Constructor sets default game.
//     */
//    public FreecellModelBuilder() {
//      this.numberOfCascadePiles = 8;
//      this.numberOfOpenPiles = 4;
//      this.numberOfFoundationPiles = 4;
//    }
//
//
//    /**
//     * Build function sets cascade piles for game.
//     *
//     * @param c number of cascade piles to set
//     * @return FreecellOperationsBuilder
//     */
//    public FreecellOperationsBuilder cascades(int c) {
//      if (c < 4) {
//        throw new IllegalArgumentException("The game can only have cascade piles between 4 and 8");
//      }
//      this.numberOfCascadePiles = c;
//      return this;
//    }
//
//    /**
//     * Build function sets open piles for game.
//     *
//     * @param o number of open piles to set
//     * @return FreecellOperationsBuilder
//     */
//    public FreecellOperationsBuilder opens(int o) {
//      if (o < 1) {
//        throw new IllegalArgumentException("The game can only have cascade piles between 4 and 8");
//      }
//      this.numberOfOpenPiles = o;
//      return this;
//    }
//
//    /**
//     * Build function for builder class.
//     */
//    public FreecellModel build() {
//      return new FreecellModel(this.numberOfCascadePiles, this.numberOfOpenPiles) {
//      };
//    }
//
//  }


//  /**
//   * Nested builder class that implements the FreecellOperationsBuilder1.
//   */
//  public static class FreecellModelBuilder1 implements FreecellOperationsMultiMoveBuilder {
//
//    private int numberOfCascadePiles;
//    private int numberOfOpenPiles;
//    private int numberOfFoundationPiles;
//
//    /**
//     * Constructor sets default game.
//     */
//    public FreecellModelBuilder1() {
//      this.numberOfCascadePiles = 8;
//      this.numberOfOpenPiles = 4;
//      this.numberOfFoundationPiles = 4;
//    }
//
//
//    /**
//     * Build function sets cascade piles for game.
//     *
//     * @param c number of cascade piles to set
//     * @return FreecellOperationsBuilder
//     */
//    public FreecellOperationsMultiMoveBuilder cascades(int c) {
//      if (c < 4) {
//        throw new IllegalArgumentException("The game can only have cascade piles between 4 and 8");
//      }
//      this.numberOfCascadePiles = c;
//      return this;
//    }
//
//    /**
//     * Build function sets open piles for game.
//     *
//     * @param o number of open piles to set
//     * @return FreecellOperationsBuilder
//     */
//    public FreecellOperationsMultiMoveBuilder opens(int o) {
//      if (o < 1) {
//        throw new IllegalArgumentException("The game can only have cascade piles between 4 and 8");
//      }
//      this.numberOfOpenPiles = o;
//      return this;
//    }

//    /**
//     * Build function for builder class.
//     */
//    public FreecellMultiMoveModel build() {
//      return new FreecellMultiMoveModel(this.numberOfCascadePiles, this.numberOfOpenPiles) {
//      };
//    }



  @Override
  public List getDeck() {
    LinkedList myList = (LinkedList) new Deck().getDeck();
    if (myList.size() != 52) {
      throw new IllegalArgumentException("Size of the deck is not 52");
    }
    return myList;
  }

  @Override
  public void startGame(List deck, boolean shuffle) throws IllegalArgumentException {
    LinkedList myList = new LinkedList();
    for (int i = 0; i < deck.size(); i++) {
      myList.addLast(deck.get(i));
    }

    if (shuffle) {
      Collections.shuffle(myList);
    }
    if (!helperIsDeckValid(myList)) {
      throw new IllegalArgumentException("The deck is invalid");
    }

    isGameStarted = true;

    //cast deck to LL

    // populate the second dimensions of arrays
    this.gameStacks[PileType.FOUNDATION.ordinal()] = new LinkedList[4];
    for (int i = 0; i < 4; i++) {
      this.gameStacks[PileType.FOUNDATION.ordinal()][i] = new LinkedList<Card>();
    }

    this.gameStacks[PileType.CASCADE.ordinal()] = new LinkedList[this.numberOfCascadePiles];
    for (int i = 0; i < this.numberOfCascadePiles; i++) {
      this.gameStacks[PileType.CASCADE.ordinal()][i] = new LinkedList<Card>();
    }

    this.gameStacks[PileType.OPEN.ordinal()] = new LinkedList[this.numberOfOpenPiles];
    for (int i = 0; i < this.numberOfOpenPiles; i++) {
      this.gameStacks[PileType.OPEN.ordinal()][i] = new LinkedList<Card>();
    }

    // deal cards to cascade piles
    for (int i = 0; !myList.isEmpty(); i++) {
      this.gameStacks[PileType.CASCADE.ordinal()][i % this.numberOfCascadePiles]
          .addLast(myList.pop());//gamestack[][].push(mylist.pop())
    }

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException {
    if (!isGameStarted) {
      throw new IllegalStateException("The game has not started");
    }
    helper2(source, pileNumber, cardIndex, destination, destPileNumber);
    this.moveMade = true;
  }

  @Override
  public boolean isGameOver() {
    if (!isGameStarted) {
      return true;
    }
    return this.gameStacks[PileType.FOUNDATION.ordinal()][0].size() == 13
        && this.gameStacks[PileType.FOUNDATION.ordinal()][1].size() == 13
        && this.gameStacks[PileType.FOUNDATION.ordinal()][2].size() == 13
        && this.gameStacks[PileType.FOUNDATION.ordinal()][3].size() == 13;

  }

  @Override
  public String getGameState() {
    if (!isGameStarted) {
      return "";
    }
    StringBuilder sb = new StringBuilder();

    //foundation piles
    for (int i = 0; i < this.numberOfFoundationPiles; i++) {
      sb.append("F" + (i + 1) + ":");
      if (this.gameStacks[PileType.FOUNDATION.ordinal()][i].size() > 0) {
        sb.append(" ");
      }
      for (int j = 0; j < this.gameStacks[PileType.FOUNDATION.ordinal()][i].size(); j++) {
        Card card = (Card) this.gameStacks[PileType.FOUNDATION.ordinal()][i].get(j);
        if (j == this.gameStacks[PileType.FOUNDATION.ordinal()][i].size() - 1) {
          sb.append(card.toString());
        } else {
          sb.append(card.toString() + ", ");
        }
      }
      sb.append("\n");
    }
    //open piles
    for (int i = 0; i < this.numberOfOpenPiles; i++) {
      sb.append("O" + (i + 1) + ":");
      if (this.gameStacks[PileType.OPEN.ordinal()][i].size() > 0) {
        sb.append(" ");
      }
      for (int j = 0; j < this.gameStacks[PileType.OPEN.ordinal()][i].size(); j++) {
        Card card = (Card) this.gameStacks[PileType.OPEN.ordinal()][i].get(j);
        if (j == this.gameStacks[PileType.OPEN.ordinal()][i].size() - 1) {
          sb.append(card.toString());
        } else {
          sb.append(card.toString() + ", ");
        }
      }
      sb.append("\n");
    }
    //cascade piles
    for (int i = 0; i < this.numberOfCascadePiles; i++) {
      sb.append("C" + (i + 1) + ":");
      if (this.gameStacks[PileType.CASCADE.ordinal()][i].size() > 0) {
        sb.append(" ");
      }
      for (int j = 0; j < this.gameStacks[PileType.CASCADE.ordinal()][i].size(); j++) {
        Card card = (Card) this.gameStacks[PileType.CASCADE.ordinal()][i].get(j);
        if (j == this.gameStacks[PileType.CASCADE.ordinal()][i].size() - 1) {
          sb.append(card.toString());
        } else {
          sb.append(card.toString() + ", ");
        }
      }
      sb.append("\n");
    }
    return sb.toString().trim();
  }

  /**
   * A helper function to check if the deck that is being used to start the game is a valid one.
   *
   * @param myList the deck of card objects that is used in dealing and starting the game
   * @return returns true if the deck is valid, else otherwise
   */
  private boolean helperIsDeckValid(List<Card> myList) {
    Set mySet = new HashSet<>();
    if (myList.size() != 52) {
      return false;
    }
    for (Card aMyList : myList) {
      if (mySet.contains(aMyList)) {
        return false;
      }
      if (!mySet.contains(aMyList)) {
        mySet.add(aMyList);
      }
      if (aMyList.getRank() < 1 || aMyList.getRank() > 13) {
        return false;
      }
      if (aMyList.getSuit().ordinal() <= 1 && aMyList.getSuit().ordinal() >= 4) {
        return false;
      }
    }
    return true;
  }

  /**
   * A helper function for the move method in the game.
   *
   * @param source the type of pile from where you wish to move the card
   * @param pileNumber the pile number of the source file
   * @param cardIndex the cardIndex of the card that we wish to move
   * @param destination the type of pile where we wish to add the card
   * @param destPileNumber the pile number of our destination pile type
   */
  private void helper2(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {

    // check if the card to be moved is one from the foundation piles
    if (source.ordinal() == PileType.FOUNDATION.ordinal()) {
      if (source.ordinal() == destination.ordinal() && pileNumber == destPileNumber) {
        Card myCard = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard);
      }
    }
    if (this.gameStacks[source.ordinal()][pileNumber].size() == 0) {
      throw new IllegalArgumentException("The pile from which you want to move the card is empty");
    }
    //check if the card to be moved is moving from the same source pile to the same destination pile

    //pile numbers negative
    if (pileNumber < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException("The pile numbers cannot be negative");
    }
    //pile numbers not equal to the actual number of piles
    if (source.ordinal() == PileType.CASCADE.ordinal() && pileNumber > (this.numberOfCascadePiles
        - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }
    //pile numbers not equal to the actual number of piles
    if (destination.ordinal() == PileType.CASCADE.ordinal() && destPileNumber > (
        this.numberOfCascadePiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }

    //invalid destination pile number
    if (source.ordinal() == PileType.CASCADE.ordinal() && destination.ordinal() == PileType.OPEN
        .ordinal() && destPileNumber > (
        this.numberOfOpenPiles - 1)) {
      throw new IllegalArgumentException("Invalid destination pile number");
    }

    if (source.ordinal() == PileType.CASCADE.ordinal()
        && destination.ordinal() == PileType.FOUNDATION.ordinal() && destPileNumber > (3)) {
      throw new IllegalArgumentException("Invalid destination pile number");
    }
    if (source.ordinal() == PileType.OPEN.ordinal() && pileNumber > (this.numberOfOpenPiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }
    if (destination.ordinal() == PileType.OPEN.ordinal() && destPileNumber > (this.numberOfOpenPiles
        - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }

    //check if the card to be moved is on top of the pile or not

    if (cardIndex < 0 || cardIndex >= this.gameStacks[source.ordinal()][pileNumber].size()) {
      throw new IllegalArgumentException(
          "The card you're trying to move is not on top of the deck");
    }

    // cascade to cascade
    //open to cascade
    if (source.ordinal() == PileType.CASCADE.ordinal() && destination.ordinal() == PileType.CASCADE
        .ordinal()
        || source.ordinal() == PileType.OPEN.ordinal() && destination.ordinal() == PileType.CASCADE
        .ordinal() || source.ordinal() == PileType.FOUNDATION.ordinal()
        && destination.ordinal() == PileType.CASCADE.ordinal()
    ) {

      // take the cards from the source and the destination piles to compare on
      // the basis of the game rules
      Card myCard1 = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
      Card myCard2 = (Card) this.gameStacks[destination.ordinal()][destPileNumber].removeLast();
      //compare suits, red cannot move on red and black cannot move on black
      if (myCard1.getSuit().ordinal() == 1 && myCard2.getSuit().ordinal() == 2) {
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);
        this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);

        throw new IllegalArgumentException("Suits of the same colour");
      }
      //compare suits, red cannot move on red and black cannot move on black
      if (myCard1.getSuit().ordinal() == myCard2.getSuit().ordinal()) {
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);
        this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
        throw new IllegalArgumentException("Same suits!!");
      }

      if (myCard1.getSuit().ordinal() == 3 && myCard2.getSuit().ordinal() == 4) {
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);
        this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard1.getSuit().ordinal() == 2 && myCard2.getSuit().ordinal() == 1) {
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);
        this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard1.getSuit().ordinal() == 4 && myCard2.getSuit().ordinal() == 3) {
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);
        this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard1.getRank() != myCard2.getRank() - 1) {
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);
        this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
        throw new IllegalArgumentException("Ranks are not in accordance to the game rules");
      }

      // add the cards to the destination pile
      //move is complete!!
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);


    }

    //cascade to open move
    if (source.ordinal() == PileType.CASCADE.ordinal() && destination.ordinal() == PileType.OPEN
        .ordinal() || source.ordinal() == PileType.FOUNDATION.ordinal()
        && destination.ordinal() == PileType.OPEN
        .ordinal()) {
      Card myCard1 = (Card) this.gameStacks[source.ordinal()][pileNumber]
          .removeLast(); // remove the card on top of the source pile
      if (this.gameStacks[destination.ordinal()][destPileNumber].size()
          != 0) { //check if open pile is empty or not
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);

        throw new IllegalArgumentException("The open pile is already full");
      }
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);


    }

    //cascade to foundation!!!
    //open to foundation

    if (source.ordinal() == PileType.CASCADE.ordinal()
        && destination.ordinal() == PileType.FOUNDATION.ordinal()
        || source.ordinal() == PileType.OPEN.ordinal()
        && destination.ordinal() == PileType.FOUNDATION.ordinal()) {

      Card myCard = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
      if (myCard.getRank() == 1) {
        if (myCard.getRank() == 1
            && this.gameStacks[destination.ordinal()][destPileNumber].size() != 0) {
          this.gameStacks[source.ordinal()][pileNumber].addLast(myCard);
          throw new IllegalArgumentException("Invalid move");

        } else {
          this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard);
        }
      }

      if (myCard.getRank() != 1) {
        if (this.gameStacks[destination.ordinal()][destPileNumber].size() != 0) {
          Card myCard1 = (Card) this.gameStacks[destination.ordinal()][destPileNumber].removeLast();
          if (myCard.getSuit().ordinal() == myCard1.getSuit().ordinal()
              && myCard.getRank() == myCard1.getRank() + 1) {
            this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);
            this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard);

          }
        }

      }
    }

    if (source.ordinal() == destination.ordinal() && pileNumber == destPileNumber) {
      Card myCard = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
      this.gameStacks[source.ordinal()][pileNumber].addLast(myCard);

    }
  }

  /**
   * Getter method for gamestacks.
   */
  protected LinkedList getGameStacks(int pileType, int pileNumber) {
    return this.gameStacks[pileType][pileNumber];
  }


}
