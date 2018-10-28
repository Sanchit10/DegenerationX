package freecell.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FreecellModel implements FreecellOperations {

  private int numberOfCascadePiles;
  private int numberOfOpenPiles;
  private int numberOfFoundationPiles;

  private LinkedList[][] gameStacks;

  /**
   * Constructor used in builder class.
   */
  public FreecellModel(int numberOfCascadePiles, int numberOfOpenPiles) {
    this.numberOfCascadePiles = numberOfCascadePiles;
    this.numberOfOpenPiles = numberOfOpenPiles;
    this.numberOfFoundationPiles = 4;
    this.gameStacks = new LinkedList[3][];
  }

  /**
   * Gets the builder for this model.
   */
  public static FreecellModelBuilder getBuilder() {
    return new FreecellModelBuilder();
  }

  /**
   * Nested builder class that implements the FreecellOperationsBuilder
   */
  public static class FreecellModelBuilder implements FreecellOperationsBuilder {

    private int numberOfCascadePiles;
    private int numberOfOpenPiles;
    private int numberOfFoundationPiles;

    /**
     * Constructor sets default game.
     */
    private FreecellModelBuilder() {
      this.numberOfCascadePiles = 8;
      this.numberOfOpenPiles = 4;
      this.numberOfFoundationPiles = 4;
    }

    /**
     * Build function sets cascade piles for game.
     *
     * @param c number of cascade piles to set
     * @return FreecellOperationsBuilder
     */
    public FreecellOperationsBuilder cascades(int c) {
      if (c < 4 || c > 8) {
        throw new IllegalStateException("The game can only have cascade piles between 4 and 8");
      }
      this.numberOfCascadePiles = c;
      return this;
    }

    /**
     * Build function sets open piles for game.
     *
     * @param o number of open piles to set
     * @return FreecellOperationsBuilder
     */
    public FreecellOperationsBuilder opens(int o) {
      if (o < 1 || o > 4) {
        throw new IllegalStateException("The game can only have open piles between 1 and 4");
      }
      this.numberOfOpenPiles = o;
      return this;
    }

    /**
     * Build function for builder class.
     */
    public <Card> FreecellModel build() {
      return new FreecellModel(this.numberOfCascadePiles, this.numberOfOpenPiles);
    }
  }

  @Override
  public List<Card> getDeck() {
    return new Deck().getDeck();
  }

  @Override
  public void startGame(List deck, boolean shuffle) throws IllegalArgumentException {
    if (shuffle) {
      Collections.shuffle(deck);
    }
    //check if the list is valid
    if (!helperIsDeckValid(deck)) {
      throw new IllegalArgumentException("The deck is invalid");
    }
    //cast deck to LL
    LinkedList thisDeck = (LinkedList) deck;

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
    for (int i = 0; !thisDeck.isEmpty(); i++) {
      this.gameStacks[PileType.CASCADE.ordinal()][i % this.numberOfCascadePiles]
          .push(thisDeck.pop());
    }
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException {

  }

  @Override
  public boolean isGameOver() {
    for (LinkedList ll : this.gameStacks[PileType.FOUNDATION.ordinal()]) {
      if (ll.size() != 13) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String getGameState() {
    return null;
  }


  private boolean helperIsDeckValid(List<Card> myList) {
    Set<Card> mySet = new HashSet<>();
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

  private void helper2(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {

    HashMap<Integer, LinkedList<Card>> myMap = new HashMap<>();

    if (source.ordinal() == 0) {
      throw new IllegalArgumentException("A card cannot be moved from the foundation pile");
    }
    if (source.ordinal() == destination.ordinal() && pileNumber == destPileNumber) {
      throw new IllegalArgumentException("You cannot move the card to the same pile");
    }
    if (pileNumber < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException("The pile numbers cannot be negative");
    }
    if (source.ordinal() == 1 && pileNumber > (this.numberOfCascadePiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }
    if (destination.ordinal() == 1 && destPileNumber > (this.numberOfCascadePiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }
    if (source.ordinal() == 1 && destination.ordinal() == 2 && destPileNumber > (
        this.numberOfOpenPiles - 1)) {
      throw new IllegalArgumentException("Invalid destination pile number");
    }
    if (source.ordinal() == 1 && destination.ordinal() == 0 && destPileNumber > (3)) {
      throw new IllegalArgumentException("Invalid destination pile number");
    }
    if (source.ordinal() == 2 && pileNumber > (this.numberOfOpenPiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }
    if (destination.ordinal() == 2 && destPileNumber > (this.numberOfOpenPiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }

    if (this.gameStacks[source.ordinal()][pileNumber].size() - 1 != cardIndex) {
      throw new IllegalArgumentException(
          "The card you're trying to move is not on top of the deck");
    }

    //// think about the conditions in here!!!
    if (source.ordinal() == 1 && destination.ordinal() == 1
        || source.ordinal() == 2 && destination.ordinal() == 1) {

      Card myCard1 = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
      Card myCard2 = (Card) this.gameStacks[destination.ordinal()][destPileNumber].removeLast();

      if (myCard1.getSuit().ordinal() == 1 && myCard2.getSuit().ordinal() == 2) {
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);
        this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);

        throw new IllegalArgumentException("Suits of the same colour");
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
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);


    }

    if (source.ordinal() == 1 && destination.ordinal() == 2) {
      Card myCard1 = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
      if (this.gameStacks[destination.ordinal()][destPileNumber].size() != 0) {
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard1);
        throw new IllegalArgumentException("The open pile is already full");
      }
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);


    }

//    if(source.ordinal()==2&&destination.ordinal()==1){
//
//    }

    if (source.ordinal() == 1 && destination.ordinal() == 0) {
      if (this.gameStacks[destination.ordinal()][destPileNumber].size() == 0) {
        Card myCard1 = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
        if (myCard1.getRank() == 1) {
          LinkedList<Card> myList;
          this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);
          myList = this.gameStacks[destination.ordinal()][destPileNumber];

          myMap.put(myCard1.getSuit().ordinal(), myList);
        }
      }


    }


  }
//  public String check(){
//    Card myCard1 = (Card) this.gameStacks[PileType.CASCADE.ordinal()][2].removeLast();
//   String s= myCard1.toString();
//   return s;
//  }


}
