package freecell.model;


import java.util.LinkedList;
import java.util.List;


public class FreecellMultiMoveModel extends AbstractModel {

  public LinkedList[][] gameStacks;

  /**
   * Concrete builder for this class.
   */
  public static class Builder extends AbstractModel.Builder<Builder> {

    @Override
    public FreecellMultiMoveModel build() {
      return new FreecellMultiMoveModel(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }

  /**
   * Builder constructor.
   */
  private FreecellMultiMoveModel(Builder builder) {
    super(builder);
  }

  /**
   * Gets the builder for this model.
   */
  public static Builder getBuilder() {
    return new Builder();
  }


  public List getDeck() {
    return super.getDeck();
  }

  public void startGame(List deck, boolean shuffle) throws IllegalArgumentException {
    super.startGame(deck, shuffle);
  }


  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException {

    if (cardIndex == super.gameStacks[source.ordinal()][pileNumber].size() - 1) {

      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    } else {

      if (cardIndex != super.gameStacks[source.ordinal()][pileNumber].size() - 1
          && source.ordinal() != PileType.CASCADE.ordinal()
          || cardIndex != super.gameStacks[source.ordinal()][pileNumber].size() - 1
          && destination.ordinal() != PileType.CASCADE.ordinal()) {
        throw new IllegalArgumentException(
            "Multiple cards can only be moved amongst cascade piles!");
      }

      if (source.ordinal() == PileType.CASCADE.ordinal()
          && destination.ordinal() == PileType.CASCADE
          .ordinal() && cardIndex != super.gameStacks[source.ordinal()][pileNumber].size() - 1
      ) {
        if (cardIndex < 0
            || cardIndex >= super.gameStacks[source.ordinal()][pileNumber].size() - 1) {
          throw new IllegalArgumentException("Invalid card index");
        }

        helper1(source, pileNumber, cardIndex, destination, destPileNumber);
        helper2(source, pileNumber, cardIndex, destination, destPileNumber);
        helper3(source, pileNumber, cardIndex, destination, destPileNumber);
        helper4(source, pileNumber, cardIndex, destination, destPileNumber);
      }
    }
  }


  public boolean isGameOver() {
    return super.isGameOver();
  }

  public String getGameState() {
    return super.getGameState();
  }


  /**
   * A helper method to check if the number of cards to be moved is less than the number of free.
   * open piles and the number of empty cascade piles, specifically (N+1)*2^K where N is the numeber
   * of free open piles and K is the number of Empty cascade piles
   *
   * @param source the type of pile from where you wish to move the card
   * @param pileNumber the pile number of the source file
   * @param cardIndex the cardIndex of the card that we wish to move
   * @param destination the type of pile where we wish to add the card
   * @param destPileNumber the pile number of our destination pile type
   */
  private void helper1(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {

    int numberOfCardsToBeMoved =
        super.gameStacks[source.ordinal()][pileNumber].size() - cardIndex;
    int numberOfEmptyCascadePiles = 0;
    for (int i = 0; i < super.numberOfCascadePiles; i++) {
      if (super.gameStacks[PileType.CASCADE.ordinal()][i].size() == 0) {
        numberOfEmptyCascadePiles += 1;
      }
    }
    int numberOfFreeOpenPiles = 0;
    for (int i = 0; i < super.numberOfOpenPiles; i++) {
      if (super.gameStacks[PileType.OPEN.ordinal()][i].size() == 0) {
        numberOfFreeOpenPiles += 1;
      }
    }

    if (numberOfCardsToBeMoved > ((numberOfFreeOpenPiles + 1) * Math
        .pow(2, numberOfEmptyCascadePiles))) {
      throw new IllegalArgumentException("Not enough free open piles/empty cascade piles");
    }
  }

  /**
   * A helper method to check if the card that we wish to move from the source cascade pile to the.
   * destination cascade pile obeys the game rules or not
   *
   * * @param source the type of pile from where you wish to move the card * @param pileNumber the
   * pile number of the source file * @param cardIndex the cardIndex of the card that we wish to
   * move * @param destination the type of pile where we wish to add the card * @param
   * destPileNumber the pile number of our destination pile type
   */
  private void helper2(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {
    Card myCard1 = (Card) super.gameStacks[source.ordinal()][pileNumber].get(cardIndex);
    Card myCard2 = (Card) super.gameStacks[destination.ordinal()][destPileNumber].removeLast();

    if (myCard1.getSuit().ordinal() == 1 && myCard2.getSuit().ordinal() == 2) {
      super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      throw new IllegalArgumentException("Suits of the same colour");
    }
    //compare suits, red cannot move on red and black cannot move on black
    if (myCard1.getSuit().ordinal() == myCard2.getSuit().ordinal()) {
      super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      throw new IllegalArgumentException("Same suits!!");
    }

    if (myCard1.getSuit().ordinal() == 3 && myCard2.getSuit().ordinal() == 4) {
      super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      throw new IllegalArgumentException("Suits of the same colour");
    }
    if (myCard1.getSuit().ordinal() == 2 && myCard2.getSuit().ordinal() == 1) {
      super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      throw new IllegalArgumentException("Suits of the same colour");
    }
    if (myCard1.getSuit().ordinal() == 4 && myCard2.getSuit().ordinal() == 3) {
      super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      throw new IllegalArgumentException("Suits of the same colour");
    }
    if (myCard1.getRank() != myCard2.getRank() - 1) {
      super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);
      throw new IllegalArgumentException("Ranks are not in accordance to the game rules");
    }

    super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);


  }

  /**
   * A helper function that checks if the pile of multiple cards that we wish to move obeys the.
   * game rules or not
   *
   * @param source the type of pile from where you wish to move the card
   * @param pileNumber the pile number of the source file
   * @param cardIndex the cardIndex of the card that we wish to move
   * @param destination the type of pile where we wish to add the card
   * @param destPileNumber the pile number of our destination pile type
   */
  private void helper3(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {
    Card myCard2 = (Card) super.gameStacks[destination.ordinal()][destPileNumber].removeLast();

    for (int i = cardIndex; i < super.gameStacks[source.ordinal()][pileNumber].size() - 1;
        i++) {
      Card myCard3 = (Card) super.gameStacks[source.ordinal()][pileNumber].get(i);
      Card myCard4 = (Card) super.gameStacks[source.ordinal()][pileNumber].get(i + 1);
      if (myCard3.getSuit().ordinal() == 1 && myCard4.getSuit().ordinal() == 2) {
        throw new IllegalArgumentException("Suits of the same colour");
      }
      //compare suits, red cannot move on red and black cannot move on black
      if (myCard3.getSuit().ordinal() == myCard4.getSuit().ordinal()) {
        throw new IllegalArgumentException("Same suits!!");
      }

      if (myCard3.getSuit().ordinal() == 3 && myCard4.getSuit().ordinal() == 4) {
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard3.getSuit().ordinal() == 2 && myCard4.getSuit().ordinal() == 1) {
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard3.getSuit().ordinal() == 4 && myCard4.getSuit().ordinal() == 3) {
        throw new IllegalArgumentException("Suits of the same colour");
      }
      if (myCard3.getRank() != myCard4.getRank() + 1) {

        throw new IllegalArgumentException("Ranks are not in accordance to the game rules");
      }
    }

    super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard2);

  }

  /**
   * A helper function that adds the pile of multiple cards to the destination piles if all.
   * conditions are met
   *
   * @param source the type of pile from where you wish to move the card
   * @param pileNumber the pile number of the source file
   * @param cardIndex the cardIndex of the card that we wish to move
   * @param destination the type of pile where we wish to add the card
   * @param destPileNumber the pile number of our destination pile type
   */
  private void helper4(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {
    for (int i = cardIndex; i < super.gameStacks[source.ordinal()][pileNumber].size(); i++) {
      Card myCard3 = (Card) super.gameStacks[source.ordinal()][pileNumber].get(i);
      super.gameStacks[destination.ordinal()][destPileNumber].addLast(myCard3);
    }

    for (int i = cardIndex; i < super.gameStacks[source.ordinal()][pileNumber].size(); i++) {
      super.gameStacks[source.ordinal()][pileNumber].remove(i);
      i--;
    }

  }


}
