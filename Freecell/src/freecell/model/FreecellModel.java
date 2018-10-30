package freecell.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FreecellModel implements FreecellOperations {


  private HashMap<Integer, LinkedList<Card>> myMap = new HashMap<>();
  private boolean isGameStarted;

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
      if (c < 4) {
        throw new IllegalArgumentException("The game can only have cascade piles between 4 and 8");
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
      if (o < 1) {
        throw new IllegalArgumentException("The game can only have cascade piles between 4 and 8");
      }
      this.numberOfOpenPiles = o;
      return this;
    }

    /**
     * Build function for builder class.
     */
    public FreecellModel build() {
      return new FreecellModel(this.numberOfCascadePiles, this.numberOfOpenPiles);
    }
  }

  @Override
  public List getDeck() {
    LinkedList myList = (LinkedList) new Deck().getDeck();
    if(myList.size()!=52)throw new IllegalArgumentException("Isdjnfsdf");
    return myList;
  }

  @Override
  public void startGame(List deck, boolean shuffle) throws IllegalArgumentException {
    LinkedList myList = new LinkedList();
    for(int i=0;i<deck.size();i++){
      myList.addLast(deck.get(i));
    }


    if (shuffle) {
      Collections.shuffle(myList);
    }

//    if(!isGameStarted){
//      throw new IllegalArgumentException("afafad");
//    }

    //check if the list is valid
    if (!helperIsDeckValid(myList)) {
     int a = myList.size();
      throw new IllegalArgumentException("The deck is invalid"+a);
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
          .push(myList.pop());
    }
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException {

    helper2(source, pileNumber, cardIndex, destination, destPileNumber);

  }

  @Override
  public boolean isGameOver() {
//    for (LinkedList ll : this.gameStacks[PileType.FOUNDATION.ordinal()]) {
//      if (ll.size() != 13) {
//        return false;
//      }
//    }


////    isGameStarted=false;
//    return true;

//    int count =0;
//    for(int i=0;i<4;i++){
//      LinkedList myList = this.gameStacks[PileType.FOUNDATION.ordinal()][i];
//      count++;
//      }
//      if(count==4)return true;
//      return false;

    if(!isGameStarted)return false;

    for(int i=0;i<this.numberOfOpenPiles;i++){
     if(this.gameStacks[PileType.OPEN.ordinal()][i].size()!=0)return false;
    }

    for(int i=0;i<this.numberOfCascadePiles;i++){
     if(this.gameStacks[PileType.CASCADE.ordinal()][i].size()!=0)return false;
    }
    for(int i=0;i<this.numberOfFoundationPiles;i++){
      if(this.gameStacks[PileType.FOUNDATION.ordinal()][i].size()!=13) return false;

    }
    return true;


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
      if(this.gameStacks[PileType.FOUNDATION.ordinal()][i].size() > 0){
        sb.append(" ");
      }
      for (int j = this.gameStacks[PileType.FOUNDATION.ordinal()][i].size() - 1; j >= 0; j--) {
        Card card = (Card) this.gameStacks[PileType.FOUNDATION.ordinal()][i].get(j);
        if (j == 0) {
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
      if(this.gameStacks[PileType.OPEN.ordinal()][i].size() > 0){
        sb.append(" ");
      }
      for (int j = this.gameStacks[PileType.OPEN.ordinal()][i].size() - 1; j >= 0; j--) {
        Card card = (Card) this.gameStacks[PileType.OPEN.ordinal()][i].get(j);
        if (j == 0) {
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
      if(this.gameStacks[PileType.CASCADE.ordinal()][i].size() > 0){
        sb.append(" ");
      }
      for (int j = this.gameStacks[PileType.CASCADE.ordinal()][i].size() - 1; j >= 0; j--) {
        Card card = (Card) this.gameStacks[PileType.CASCADE.ordinal()][i].get(j);
        if (j == 0) {
          sb.append(card.toString());
        } else {
          sb.append(card.toString() + ", ");
        }
      }
      sb.append("\n");
    }
    return sb.toString().trim();

  }

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

  private void helper2(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {


//    if(destination.ordinal()==PileType.FOUNDATION.ordinal()&&destPileNumber>3){
//      throw new IllegalArgumentException("sfdsfdsf");
//    }

//    if(this.gameStacks[source.ordinal()][pileNumber].size()==0){
//
//
//      throw new IllegalArgumentException("daft punk!");
//    }

    // check if the card to be moved is one from the foundation piles
    if (source.ordinal() == PileType.FOUNDATION.ordinal()) {
      if (source.ordinal() == destination.ordinal() && pileNumber == destPileNumber) {
        Card myCard = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
        this.gameStacks[source.ordinal()][pileNumber].addLast(myCard);
      } else {

        throw new IllegalArgumentException("A card cannot be moved from the foundation pile");
      }
    }
    //check if the card to be moved is moving from the same source pile to the same destination pile

    //pile numbers negative
    if (pileNumber < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException("The pile numbers cannot be negative");
    }
    //pile numbers not equal to the actual number of piles
    if (source.ordinal() == PileType.CASCADE.ordinal() && pileNumber > (this.numberOfCascadePiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }
    //pile numbers not equal to the actual number of piles
    if (destination.ordinal() == PileType.CASCADE.ordinal() && destPileNumber > (this.numberOfCascadePiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }

    //invalid destination pile number
    if (source.ordinal() == PileType.CASCADE.ordinal() && destination.ordinal() == PileType.OPEN.ordinal() && destPileNumber > (
        this.numberOfOpenPiles - 1)) {
      throw new IllegalArgumentException("Invalid destination pile number");
    }


    if (source.ordinal() == PileType.CASCADE.ordinal() && destination.ordinal() == PileType.FOUNDATION.ordinal()&& destPileNumber > (3)) {
      throw new IllegalArgumentException("Invalid destination pile number");
    }
    if (source.ordinal() == PileType.OPEN.ordinal() && pileNumber > (this.numberOfOpenPiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }
    if (destination.ordinal() == PileType.OPEN.ordinal() && destPileNumber > (this.numberOfOpenPiles - 1)) {
      throw new IllegalArgumentException("Invalid pile number");
    }

    //check if the card to be moved is on top of the pile or not

    if ((this.gameStacks[source.ordinal()][pileNumber].size()) - 1 != cardIndex) {
      throw new IllegalArgumentException(
          "The card you're trying to move is not on top of the deck");
    }


    // cascade to cascade
    //open to cascade
    if (source.ordinal() == PileType.CASCADE.ordinal() && destination.ordinal() == PileType.CASCADE.ordinal()
        || source.ordinal() == PileType.OPEN.ordinal() && destination.ordinal() == PileType.CASCADE.ordinal()) {

      // take the cards from the source and the destination piles to compare on the basis of the game rules
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
        throw new IllegalArgumentException("Same suits!!");
      }

      if (myCard1.getSuit().ordinal() == 3 && myCard2.getSuit().ordinal() == 4) {
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard1.getSuit().ordinal() == 2 && myCard2.getSuit().ordinal() == 1) {
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard1.getSuit().ordinal() == 4 && myCard2.getSuit().ordinal() == 3) {
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard1.getRank() != myCard2.getRank() - 1) {

        throw new IllegalArgumentException("Ranks are not in accordance to the game rules");
      }

      // add the cards to the destination pile
      //move is complete!!
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);


    }

    //cascade to open move

    if (source.ordinal() == PileType.CASCADE.ordinal() && destination.ordinal() == PileType.OPEN.ordinal()) {
      Card myCard1 = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast(); // remove the card on top of the source pile
      if (this.gameStacks[destination.ordinal()][destPileNumber].size() != 0) { //check if open pile is empty or not

        throw new IllegalArgumentException("The open pile is already full");
      }
      this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);


    }

    //cascade to foundation!!!
    //open to foundation

    if (source.ordinal() == PileType.CASCADE.ordinal() && destination.ordinal() == PileType.FOUNDATION.ordinal()
        || source.ordinal() == PileType.OPEN.ordinal() && destination.ordinal() == PileType.FOUNDATION.ordinal()) {

      int h = this.gameStacks[source.ordinal()][pileNumber].size();
      int b =this.gameStacks[destination.ordinal()][destPileNumber].size();
      Card myCard = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
      int a = myCard.getRank();

      int c = pileNumber;
      int d = cardIndex;
      int e = destPileNumber;
      int f = source.ordinal();
      int g = destination.ordinal();
      if(myCard.getRank()==1) {
        if (myCard.getRank() == 1
            && this.gameStacks[destination.ordinal()][destPileNumber].size() != 0) {
          throw new IllegalArgumentException(
              a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h);

        } else {
          this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard);
        }
      }

      if(myCard.getRank()!=1){
        if(this.gameStacks[destination.ordinal()][destPileNumber].size()!=0) {
          Card myCard1 = (Card) this.gameStacks[destination.ordinal()][destPileNumber].removeLast();
          if (myCard.getSuit().ordinal() == myCard1.getSuit().ordinal()
              && myCard.getRank() == myCard1.getRank() + 1) {
            this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard1);
            this.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard);

          }
        }
//        else {
//          throw new IllegalArgumentException("lets roll babe");
//        }
      }
    }

    if (source.ordinal() == destination.ordinal() && pileNumber == destPileNumber) {
      Card myCard = (Card) this.gameStacks[source.ordinal()][pileNumber].removeLast();
      this.gameStacks[source.ordinal()][pileNumber].addLast(myCard);

    }


  }



}
