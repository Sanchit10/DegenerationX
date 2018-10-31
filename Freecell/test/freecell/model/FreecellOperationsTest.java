package freecell.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FreecellOperationsTest {

  /**
   * Moves all cascade piles to foundation piles and checks functionality of isGameOver(true&false).
   */
  @Test
  public void testGame4O4C(){
    //create model
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    /** Delete me */
    System.out.println(model.getGameState() + "\n\n");
    assertFalse(model.isGameOver());
    // move cascade piles to foundation piles
    for (int j = 0; j < 4; j++) {
      for (int i = 12; i >= 0; i--) {
        model.move(PileType.CASCADE,j,i, PileType.FOUNDATION, j);
        System.out.println(model.getGameState() + "\n\n");
      }
    }
    System.out.println(model.getGameState());
    assertTrue(model.isGameOver());
  }

  /**
   * Tests exception throw for attempted move from foundation pile.
   */
  @Test(expected = Exception.class)
  public void testMoveFromFoundationException(){
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE,0,12, PileType.FOUNDATION, 0);
    model.move(PileType.FOUNDATION,0,0, PileType.CASCADE, 0);
  }

  /**
   * Tests exception throw non-Ace addition to empty foundation pile.
   */
  @Test(expected = Exception.class)
  public void testNonAceToEmptyFoundationPile(){
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE,0,12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE,0,12, PileType.FOUNDATION, 1);
  }

  /**
   * Tests exception throw for invalid card addition to foundation pile.
   */
  @Test(expected = Exception.class)
  public void testInvalidCardToNonEmptyFoundationPile(){
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE,0,12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE,0,12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE,0,12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE,1,12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE,1,11, PileType.FOUNDATION, 0);
  }

  /**
   * Tests exception throw for same color (red) addition to cascade pile.
   */
  @Test(expected = Exception.class)
  public void testCascadeToCascadeSameColorRed(){
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE,0,12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE,0,11, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE,1,12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE,1,11, PileType.CASCADE, 0);
  }

  /**
   * Tests exception throw for same color (black) addition to cascade pile.
   */
  @Test(expected = Exception.class)
  public void testCascadeToCascadeSameColorBlack(){
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE,2,12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE,2,11, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE,3,12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE,3,11, PileType.CASCADE, 2);
  }

  /**
   * Tests exception throw for addition to non-empty open pile.
   */
  @Test(expected = Exception.class)
  public void testAdditionToNonEmptyOpenPile(){
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE,0,12, PileType.OPEN, 0);
    model.move(PileType.CASCADE,0,11, PileType.OPEN, 0);
  }

  /**
   * Tests that getDeck returns 52 cards.
   */
  @Test
  public void testGetDeck() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    assertTrue(deck.size() == 52);
    for(Object o : deck){
      Card c = (Card) o;
      assertTrue(c instanceof Card);
    }
  }

  /**
   * Tests starting game state 4  cascades, 4 opens.
   */
  @Test
  public void testGameState(){
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    String expectedState =
                    "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(expectedState, model.getGameState());
  }

  /**
   * Tests starting game state 8 cascades, 4 opens.
   */
  @Test
  public void testGame4O8C(){
    //create model
    FreecellModel model = FreecellModel.getBuilder().cascades(8).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    String expectedState =
                    "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
                    "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
                    "C3: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
                    "C4: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
                    "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
                    "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
                    "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
                    "C8: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠";
    assertEquals(expectedState, model.getGameState());
  }

  /**
   * Tests starting game state 8 cascades, 8 opens.
   */
  @Test
  public void testGame8O8C(){
    //create model
    FreecellModel model = FreecellModel.getBuilder().cascades(8).opens(8).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    String expectedState =
                    "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "O5:\n" +
                    "O6:\n" +
                    "O7:\n" +
                    "O8:\n" +
                    "C1: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
                    "C2: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
                    "C3: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
                    "C4: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
                    "C5: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
                    "C6: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
                    "C7: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
                    "C8: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠";
    assertEquals(expectedState, model.getGameState());
  }
}