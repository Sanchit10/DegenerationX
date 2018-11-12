package freecell.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import freecell.model.Card;
import freecell.model.FreecellModel;
import freecell.model.FreecellMultiMoveModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FreecellControllerTest {

  FreecellModel singleModel;
  FreecellMultiMoveModel multiModel;
  List deck;

  /**
   * Setup test objects.
   */
  @Before
  public void setup() {
    singleModel = FreecellModel.getBuilder().opens(4).cascades(4).build();
    multiModel = FreecellMultiMoveModel.getBuilder().opens(4).cascades(4).build();
    deck = multiModel.getDeck();
  }

  /**
   * Tests single-instruction move with spaces as delimiters and no shuffle (MultiMoveModel).
   */
  @Test
  public void singleValidMoveSpaceDelimiterCascadeToFoundationMultiModelNoShuffle() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 f1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String expectedOutput =
            "F1: A♥\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(expectedOutput, multiModel.getGameState());
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    for (int i = 0; i < 13; i++) {
      string = scan.nextLine();
    }
    assertTrue(string.matches("c1"));
    string = scan.nextLine();
    assertTrue(string.matches("13"));
    string = scan.nextLine();
    assertTrue(string.matches("f1"));
  }

  /**
   * Tests single-instruction move with spaces as delimiters and no shuffle (MultiMoveModel).
   */
  @Test
  public void singleValidMoveSpaceDelimiterCascadeToFoundationMultiModelShuffle() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 f1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, true);
    String nonExpectedOutput =
            "F1: A♥\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertFalse(nonExpectedOutput.matches(multiModel.getGameState()));
    Scanner scan = new Scanner(out.toString());
    String string = new String();
  }

  /**
   * Tests single-instruction move with spaces as delimiters (SingleModel).
   */
  @Test
  public void singleValidMoveSpaceDelimiterCascadeToFoundationSingleModel() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 f1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, singleModel, false);
    String expectedOutput =
            "F1: A♥\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(expectedOutput, singleModel.getGameState());
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    for (int i = 0; i < 13; i++) {
      string = scan.nextLine();
    }
    assertTrue(string.matches("c1"));
    string = scan.nextLine();
    assertTrue(string.matches("13"));
    string = scan.nextLine();
    assertTrue(string.matches("f1"));
  }

  /**
   * Tests a single valid move from cascade to open pile with \s delimiters.
   */
  @Test
  public void singleValidMoveSpaceDelimiterCascadeToOpen() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 o1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String expectedOutput =
            "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: A♥\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(expectedOutput, multiModel.getGameState());
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    for (int i = 0; i < 13; i++) {
      string = scan.nextLine();
    }
    assertTrue(string.matches("c1"));
    string = scan.nextLine();
    assertTrue(string.matches("13"));
    string = scan.nextLine();
    assertTrue(string.matches("o1"));
  }

  /**
   * Tests a valid move from open to cascade pile with \s delimiters.
   */
  @Test
  public void validMoveSpaceDelimiterOpenToCascade() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 f1 c1 12 o1 c3 13 f2 c3 12 o2 o1 1 c3");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String expectedOutput =
            "F1: A♥\n" +
                    "F2: A♣\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2: 2♣\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♥\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(expectedOutput, multiModel.getGameState());
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    for (int i = 0; i < 73; i++) {
      string = scan.nextLine();
    }
    assertTrue(string.matches("o1"));
    string = scan.nextLine();
    assertTrue(string.matches("1"));
    string = scan.nextLine();
    assertTrue(string.matches("c3"));
  }

  /**
   * Tests single-instruction move with \n as delimiters (MultiMoveModel).
   */
  @Test
  public void singleValidMoveNewLineDelimiter() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1\n13\nf1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String expectedOutput =
            "F1: A♥\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(expectedOutput, multiModel.getGameState());
  }

  /**
   * Tests multi-instruction move with spaces as delimiters (MultiMoveModel).
   */
  @Test
  public void multipleValidMoveSpaceDelimiter() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
            "c1 13 f1 c1 12 f1 c1 11 f1 c1 10 f1 c1 9 f1 c1 8 f1 c1 7 f1 "
                    +
                    "c1 6 f1 c1 5 f1 c1 4 f1 c1 3 f1 c1 2 f1 c1 1 f1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String expectedOutput =
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1:\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(expectedOutput, multiModel.getGameState());
  }

  /**
   * Tests multi-instruction move with \n as delimiters (MultiMoveModel).
   */
  @Test
  public void multipleValidMoveNewlineDelimiter() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader(
            "c1\n13\nf1\nc1\n12\nf1\nc1\n11\nf1\nc1\n10\nf1\nc1\n9\nf1\nc1\n8\nf1\nc1\n7\nf1\n"
                    +
                    "c1\n6\nf1\nc1\n5\nf1\nc1\n4\nf1\nc1\n3\nf1\nc1\n2\nf1\nc1\n1\nf1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String expectedOutput =
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1:\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(expectedOutput, multiModel.getGameState());
  }

  /**
   * Tests for gameOver.
   */
  @Test
  public void finishGame() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 f1 c1 12 f1 c1 11 f1 c1 10 f1 c1 9 f1 c1 8 f1 c1 7 f1 "
            +
            "c1 6 f1 c1 5 f1 c1 4 f1 c1 3 f1 c1 2 f1 c1 1 f1 "
            +
            "c2 13 f2 c2 12 f2 c2 11 f2 c2 10 f2 c2 9 f2 c2 8 f2 c2 7 f2 "
            +
            "c2 6 f2 c2 5 f2 c2 4 f2 c2 3 f2 c2 2 f2 c2 1 f2 "
            +
            "c3 13 f3 c3 12 f3 c3 11 f3 c3 10 f3 c3 9 f3 c3 8 f3 c3 7 f3 "
            +
            "c3 6 f3 c3 5 f3 c3 4 f3 c3 3 f3 c3 2 f3 c3 1 f3 "
            +
            "c4 13 f4 c4 12 f4 c4 11 f4 c4 10 f4 c4 9 f4 c4 8 f4 c4 7 f4 "
            +
            "c4 6 f4 c4 5 f4 c4 4 f4 c4 3 f4 c4 2 f4 c4 1 f4");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String expectedOutput =
            "F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
                    "F2: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
                    "F3: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
                    "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1:\n" +
                    "C2:\n" +
                    "C3:\n" +
                    "C4:";
    assertEquals(expectedOutput, multiModel.getGameState());
    assertTrue(multiModel.isGameOver());
  }

  /**
   * Tests behavior for empty input.
   */
  @Test
  public void noInput() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String expectedOutput =
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
    assertEquals(expectedOutput, multiModel.getGameState());
    Scanner scan = new Scanner(out.toString());
  }

  /**
   * Tests invalid moves.
   */
  @Test
  public void invalidMove() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 14 o1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    while (!string.matches("Invalid move. Try again.")) {
      string = scan.nextLine();
    }
    assertTrue(string.matches("Invalid move. Try again."));
  }

  /**
   * Teste behavior for invalid src input.
   */
  @Test
  public void invalidSrcInput() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("b1 c1 13 o1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String string =
            "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: A♥\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(string, multiModel.getGameState());
  }

  /**
   * Teste behavior for invalid src input.
   */
  @Test
  public void invalidDestInput() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 g1 o1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String string =
            "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: A♥\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(string, multiModel.getGameState());
  }

  /**
   * Teste behavior for invalid index input.
   */
  @Test
  public void invalidIndexInput() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 f 13 o1");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    String string =
            "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: A♥\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C2: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠";
    assertEquals(string, multiModel.getGameState());
  }

  /**
   * Tests behavior for null deck input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNullDeck() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 o1");
    FreecellController controller = new FreecellController(in, out);
    LinkedList<Card> nullList = null;
    controller.playGame(nullList, multiModel, false);
  }

  /**
   * Tests behavior for null Readable input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNullIn() {
    StringBuffer out = new StringBuffer();
    Reader in = null;
    FreecellController controller = new FreecellController(in, out);
  }

  /**
   * Teste behavior for null Appendable output.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNullOut() {
    StringBuffer out = null;
    Reader in = new StringReader("c1 13 o1");
    FreecellController controller = new FreecellController(in, out);
  }

  /**
   * Tests behavior for invalid deck input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionInvalidl53CardDeck() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 o1");
    deck.add(new Card(1, 1));
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
  }

  /**
   * Tests behavior for 51 card deck input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionInvalidl51CardDeck() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 o1");
    deck.remove(1);
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
  }

  /**
   * Tests deck for invalid deck with invalid suits.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionInvalidSuit() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 o1");
    deck.remove(1);
    deck.add(new Card(1, 0));
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
  }

  /**
   * Tests deck for invalid deck with invalid ranks.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionInvalidRank() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 o1");
    deck.remove(1);
    deck.add(new Card(0, 1));
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
  }

  /**
   * Tests behavior for null model input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNullModel() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 o1");
    FreecellController controller = new FreecellController(in, out);
    FreecellModel nullModel = null;
    controller.playGame(deck, nullModel, false);
  }

  /**
   * Tests behavior for quitting at beginning of game (lowercase 'q').
   */
  @Test
  public void quitGameBeginningLowerCase() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("q");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    while (scan.hasNext()) {
      string = scan.nextLine();
      System.out.println("line" + string);
    }
    assertTrue(string.matches("Game quit prematurely."));
  }

  /**
   * Tests behavior for quitting at beginning of game (uppercase 'Q').
   */
  @Test
  public void quitGameBeginningUpperCase() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("Q");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    while (scan.hasNext()) {
      string = scan.nextLine();
      System.out.println("line" + string);
    }
    assertTrue(string.matches("Game quit prematurely."));
  }

  /**
   * Tests behavior for quitting mid-game.
   */
  @Test
  public void quitGamemultipleInputs() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 13 o1 Q");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    while (scan.hasNext()) {
      string = scan.nextLine();
      System.out.println("line" + string);
    }
    assertTrue(string.matches("Game quit prematurely."));
  }

  /**
   * Tests behavior for quitting mid-game, with invalid input string.
   */
  @Test
  public void quitGameNegativeInputs() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("c1 -13 o1 q");
    FreecellController controller = new FreecellController(in, out);
    controller.playGame(deck, multiModel, false);
    Scanner scan = new Scanner(out.toString());
    String string = new String();
    while (scan.hasNext()) {
      string = scan.nextLine();
    }
    assertEquals(string, "Game quit prematurely.");
  }
}