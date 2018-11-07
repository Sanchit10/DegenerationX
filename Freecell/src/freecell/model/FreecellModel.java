package freecell.model;

import java.util.List;

/**
 * A class that that implements the FreeCellOperations interface i.e. the entire functionality as.
 * required for the game of freecell to be working correctly
 */
public class FreecellModel extends AbstractModel {

  /**
   * Concrete builder for this class.
   */
  public static class Builder extends AbstractModel.Builder<Builder>{
    @Override
    public FreecellModel build(){
      return new FreecellModel(this);
    }

    @Override
    protected Builder self(){ return this; }
  }

  /**
   * Builder constructor.
   *
   * @param builder
   */
  private FreecellModel(Builder builder){
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
    super.move(source,pileNumber,cardIndex,destination,destPileNumber);

  }

  public boolean isGameOver() {
    return super.isGameOver();
  }

  public String getGameState() {
    return super.getGameState();
  }

}