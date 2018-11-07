

import freecell.model.Card;
import freecell.model.FreecellModel;
import freecell.model.FreecellMultiMoveModel;
import freecell.model.PileType;
import java.util.LinkedList;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FreecellOperationsTest {

  /**
   * Moves all cascade piles to foundation piles and checks functionality of
   * isGameOver(true&false).
   */
  @Test
  public void testGame4O4C() {
    //create model
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    System.out.println(model.getGameState() + "\n\n");
    assertFalse(model.isGameOver());
    // move cascade piles to foundation piles
    for (int j = 0; j < 4; j++) {
      for (int i = 12; i >= 0; i--) {
        model.move(PileType.CASCADE, j, i, PileType.FOUNDATION, j);
        System.out.println(model.getGameState() + "\n\n");
      }
    }
    System.out.println(model.getGameState());
    assertTrue(model.isGameOver());
  }

  /**
   * Tests exception throw for attempted move from foundation pile to the same cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromFoundationException() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
  }

  /**
   * Tests exception throw non-Ace addition to empty foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonAceToEmptyFoundationPile() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 1);
  }

  /**
   * Tests exception throw non-Ace addition to empty foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGameEmpty() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    LinkedList myList = new LinkedList();
    model.startGame(myList, false);

  }

  /**
   * Tests exception throw for invalid card addition to foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardToNonEmptyFoundationPile() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 1, 11, PileType.FOUNDATION, 0);
  }

  /**
   * Tests exception throw for same color (red) addition to cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCascadeToCascadeSameColorRed() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 1, 11, PileType.CASCADE, 0);
  }

  /**
   * Tests exception throw for same color (black) addition to cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCascadeToCascadeSameColorBlack() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 2, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 2, 11, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 3, 11, PileType.CASCADE, 2);
  }

  /**
   * Tests exception throw for addition to non-empty open pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAdditionToNonEmptyOpenPile() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 11, PileType.OPEN, 0);
  }


  /**
   * Tests exception throw for trying to move card that is not on top of the source pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionToMoveCardNotOnTop() {
    FreecellModel model = FreecellModel.getBuilder().cascades(8).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
  }


  /**
   * Tests exception throw for trying to move card that has an invalid card index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionInvalidCardIndex() {
    FreecellModel model = FreecellModel.getBuilder().cascades(8).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, -1, PileType.OPEN, 0);
  }

  /**
   * Tests exception throw for trying to make a move before the game has even started.
   */
  @Test(expected = IllegalStateException.class)
  public void testMove() {
    FreecellModel model = FreecellModel.getBuilder().cascades(8).opens(4).build();
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
  }

  /**
   * Tests exception throw for trying to create a model with -ve number of open piles.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeCascadePiles() {
    FreecellModel model = FreecellModel.getBuilder().cascades(8).opens(-4).build();

  }

  /**
   * Tests exception throw for trying to create a model with -ve number of cascade piles.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeOpenPiles() {
    FreecellModel model = FreecellModel.getBuilder().cascades(-8).opens(4).build();

  }

  /**
   * Tests exception throw for trying to make an invalid move from open to cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveFromOpenToCascade() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 1);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
  }

  /**
   * Tests exception throw for trying to start the game with an pile having duplicate cards.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidDeck() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = new LinkedList();
    deck.add(new Card(7, 2));
    deck.add(new Card(7, 2));
    model.startGame(deck, false);

  }

  /**
   * Tests exception throw for moving a foundation pile card to a non empty open pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveFromFoundationToOpen() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 11, PileType.OPEN, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 0);

  }


  /**
   * Tests exception throw for trying to start the game with an empty deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void emptyDeck() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = new LinkedList();
    model.startGame(deck, false);

  }

  /**
   * Tests exception throw for adding invalid card object to the deck required for dealing.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidCard() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = new LinkedList();
    model.startGame(deck, false);

  }


  /**
   * Tests that getDeck returns 52 cards.
   */
  @Test
  public void testGetDeck() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    assertTrue(deck.size() == 52);
    for (Object o : deck) {
      Card c = (Card) o;
      assertTrue(c instanceof Card);
    }
  }

  /**
   * Tests starting game state 4  cascades, 4 opens.
   */
  @Test
  public void testGameState() {
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
  public void testGame4O8C() {
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
  public void testGame8O8C() {
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

  @Test
  public void testGameStateBeforeBeginningGame() {
    FreecellModel model = FreecellModel.getBuilder().cascades(8).opens(4).build();
    assertEquals(model.getGameState(), "");
  }

  @Test
  public void testMoves() {
    String expectedMove1 =
        "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3: A♥\n"
            + "O4:\n"
            + "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";

    String expectedMove2 =
        "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 2);
    assertEquals(model.getGameState(), expectedMove1);
    model.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 0);
    assertEquals(model.getGameState(), expectedMove2);
    model.startGame(deck, false);
    assertFalse(model.isGameOver());


  }

  @Test
  public void testGameOverBeforeStart() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    assertTrue(model.isGameOver());


  }


  @Test
  public void testGameOverBeforeStar1t() {
    FreecellModel model = FreecellModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 2);
    model.move(PileType.FOUNDATION, 2, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
    assertFalse(model.isGameOver());

  }


  // write the test to check the deck when it is shuffled!!!!

  @Test
  public void testGame4O4C1() {
    //create model
    FreecellMultiMoveModel model1 = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model1.getDeck();
    model1.startGame(deck, false);
    System.out.println(model1.getGameState() + "\n\n");
    assertFalse(model1.isGameOver());
    // move cascade piles to foundation piles
    for (int j = 0; j < 4; j++) {
      for (int i = 12; i >= 0; i--) {
        model1.move(PileType.CASCADE, j, i, PileType.FOUNDATION, j);
        System.out.println(model1.getGameState() + "\n\n");
      }
    }
    System.out.println(model1.getGameState());

    assertTrue(model1.isGameOver());
  }

  /**
   * Tests exception throw for attempted move from foundation pile to the same cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromFoundationException1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
  }

  /**
   * Tests exception throw non-Ace addition to empty foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonAceToEmptyFoundationPile1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 1);
  }

  /**
   * Tests exception throw non-Ace addition to empty foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGameEmpty1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    LinkedList myList = new LinkedList();
    model.startGame(myList, false);

  }

  /**
   * Tests exception throw for invalid card addition to foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardToNonEmptyFoundationPile1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 1, 11, PileType.FOUNDATION, 0);
  }

  /**
   * Tests exception throw for same color (red) addition to cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCascadeToCascadeSameColorRed1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 1, 11, PileType.CASCADE, 0);
  }

  /**
   * Tests exception throw for same color (black) addition to cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCascadeToCascadeSameColorBlack1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 2, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 2, 11, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 3, 11, PileType.CASCADE, 2);
  }

  /**
   * Tests exception throw for addition to non-empty open pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAdditionToNonEmptyOpenPile1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 0, 11, PileType.OPEN, 0);
  }


  /**
   * Tests exception throw for trying to move card that is not on top of the source pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionToMoveCardNotOnTop1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(8).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
  }


  /**
   * Tests exception throw for trying to move card that has an invalid card index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionInvalidCardIndex1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(8).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, -1, PileType.OPEN, 0);
  }

  /**
   * Tests exception throw for trying to make a move before the game has even started.
   */
  @Test(expected = IllegalStateException.class)
  public void testMove1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(8).opens(4).build();
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
  }

  /**
   * Tests exception throw for trying to create a model with -ve number of open piles.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeCascadePiles1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(8).opens(-4).build();

  }

  /**
   * Tests exception throw for trying to create a model with -ve number of cascade piles.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeOpenPiles1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(-8).opens(4).build();

  }

  /**
   * Tests exception throw for trying to make an invalid move from open to cascade pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveFromOpenToCascade1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 1);
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
  }

  /**
   * Tests exception throw for trying to start the game with an pile having duplicate cards.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidDeck1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = new LinkedList();
    deck.add(new Card(7, 2));
    deck.add(new Card(7, 2));
    model.startGame(deck, false);

  }

  /**
   * Tests exception throw for moving a foundation pile card to a non empty open pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveFromFoundationToOpen1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    model.move(PileType.CASCADE, 0, 11, PileType.OPEN, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 0);

  }


  /**
   * Tests exception throw for trying to start the game with an empty deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void emptyDeck1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = new LinkedList();
    model.startGame(deck, false);

  }

  /**
   * Tests exception throw for adding invalid card object to the deck required for dealing.
   */
  @Test(expected = IllegalArgumentException.class)
  public void invalidCard1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = new LinkedList();
    model.startGame(deck, false);

  }




  /**
   * Tests that getDeck returns 52 cards.
   */
  @Test
  public void testGetDeck1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    assertTrue(deck.size() == 52);
    for (Object o : deck) {
      Card c = (Card) o;
      assertTrue(c instanceof Card);
    }
  }

  /**
   * Tests starting game state 4  cascades, 4 opens.
   */
  @Test
  public void testGameState1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
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
  public void testGame4O8C1() {
    //create model
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(8).opens(4).build();
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
  public void testGame8O8C1() {
    //create model
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(8).opens(8).build();
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

  @Test
  public void testGameStateBeforeBeginningGame1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(8).opens(4).build();
    assertEquals(model.getGameState(), "");
  }

  @Test
  public void testMoves1() {
    String expectedMove1 =
        "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3: A♥\n"
            + "O4:\n"
            + "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";

    String expectedMove2 =
        "F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 2);
    assertEquals(model.getGameState(), expectedMove1);
    model.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 0);
    assertEquals(model.getGameState(), expectedMove2);
    model.startGame(deck, false);
    assertFalse(model.isGameOver());


  }

  @Test
  public void testGameOverBeforeStart1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    assertTrue(model.isGameOver());


  }


  @Test
  public void testGameOverBeforeStar1t1() {
    FreecellMultiMoveModel model = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4).build();
    List deck = model.getDeck();
    model.startGame(deck, false);
    model.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 2);
    model.move(PileType.FOUNDATION, 2, 0, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 0);
    model.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 0);
    assertFalse(model.isGameOver());

  }

  @Test
  public void moveMultipleCard(){
    String s = "F1: A♥, 2♥, 3♥\n"
        + "F2: A♠, 2♠, 3♠\n"
        + "F3: A♦\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4: 2♣\n"
        + "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♣, 2♦, A♣\n"
        + "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
        + "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣\n"
        + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠";

    FreecellMultiMoveModel myModel = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4)
        .build();
    List deck = myModel.getDeck();
    myModel.startGame(deck, false);
    myModel.move(PileType.CASCADE,0,12,PileType.OPEN,0);
    myModel.move(PileType.CASCADE,3,12,PileType.OPEN,1);
    myModel.move(PileType.CASCADE,3,11,PileType.OPEN,2);
    myModel.move(PileType.CASCADE,1,12,PileType.OPEN,3);
    myModel.move(PileType.CASCADE,2,12,PileType.CASCADE,1);
    myModel.move(PileType.OPEN,0,0,PileType.FOUNDATION,0);
    myModel.move(PileType.OPEN,1,0,PileType.FOUNDATION,1);
    myModel.move(PileType.OPEN,3,0,PileType.FOUNDATION,2);
    myModel.move(PileType.OPEN, 2,0,PileType.FOUNDATION,1);
    myModel.move(PileType.CASCADE, 3,10 ,PileType.OPEN,1);
    myModel.move(PileType.CASCADE,0, 11, PileType.FOUNDATION,0);
    myModel.move(PileType.CASCADE,2,11,PileType.OPEN,3);
    myModel.move(PileType.OPEN,1,0,PileType.FOUNDATION,1);
    myModel.move(PileType.CASCADE,1,11,PileType.CASCADE,2);
    myModel.move(PileType.CASCADE,0,10,PileType.FOUNDATION,0);
    myModel.move(PileType.CASCADE,2,10,PileType.CASCADE,0);
    assertEquals(myModel.getGameState(),s);

  }


}