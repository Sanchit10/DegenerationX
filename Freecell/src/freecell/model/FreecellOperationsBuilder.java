package freecell.model;

/**
 * An interface that is used to create a model of the freecell game based on the requirements.
 */
public interface FreecellOperationsBuilder {

  /**
   * A method that allows us to specify the number of cascade piles that our game model will have.
   *
   * @param c the number of cascade piles we wish to have in our game
   * @return the number of cascade piles that our game model will have
   */
  FreecellOperationsBuilder cascades(int c);

  /**
   * A method that allows us to specify the number of open piles that our game model will have.
   *
   * @param o the number of open piles we wish to have in our game
   * @return the number of open piles that our game model will have
   */
  FreecellOperationsBuilder opens(int o);

  /**
   * A method that builds our game model depending on the type of game we wish to play (single.
   * card) or (multi move) cards
   */
  <Card> FreecellOperations build();

}

