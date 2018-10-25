package freecell.model;

import java.util.List;

public class FreecellModel implements FreecellOperations {

  private int numberOfCascadePiles;
  private int numberOfOpenPiles;
  private int numberOfFoundationPiles;

  /**
   * Constructor used in builder class.
   */
  public FreecellModel(int numberOfCascadePiles, int numberOfOpenPiles) {
    this.numberOfCascadePiles = numberOfCascadePiles;
    this.numberOfOpenPiles = numberOfOpenPiles;
    this.numberOfFoundationPiles = 4;
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

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination, int destPileNumber) throws IllegalArgumentException, IllegalStateException {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    return null;
  }

}

