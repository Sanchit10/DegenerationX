package freecell.model;

import java.util.LinkedList;
import java.util.List;

import freecell.abstracts.AbstractModel;

/**
 * A class that that implements the FreeCellOperations interface i.e. the entire functionality as.
 * required for the game of freecell to be working correctly
 */
public class FreecellModel extends AbstractModel {

  private LinkedList[][] gameStacks;

  /**
   * Constructor used in builder class.
   */
  public FreecellModel(int numberOfCascadePiles, int numberOfOpenPiles) {
   super(numberOfCascadePiles, numberOfOpenPiles);
  }

  /**
   * Gets the builder for this model.
   */
  public static FreecellModelBuilder getBuilder() {
    return new FreecellModelBuilder();
  }

  public List getDeck() {
    return super.getDeck();
  }

  public void startGame(List deck, boolean shuffle) throws IllegalArgumentException {
    super.startGame(deck, shuffle);
  }

  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) throws IllegalArgumentException {
    super.move(source,pileNumber,cardIndex,destination,destPileNumber);

  }

  public boolean isGameOver() {
    return super.isGameOver();
  }

  public String getGameState() {
    return super.getGameState();
  }

}
