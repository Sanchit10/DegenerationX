package freecell.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import freecell.model.Card;
import freecell.model.FreecellOperations;
import freecell.model.PileType;

public class FreecellController implements IFreecellController {

  final Readable in;
  final Appendable out;

  public FreecellController(Readable rd, Appendable ap) {
    if (rd == null) {
      throw new IllegalArgumentException("Readable object is null");
    }
    if (ap == null) {
      throw new IllegalArgumentException("Appendable object is null");
    }
    this.in = rd;
    this.out = ap;
  }

  @Override
  public void playGame(List deck, FreecellOperations model, boolean shuffle)
      throws IllegalArgumentException, IllegalStateException {

    if (deck == null || model == null || !helperIsDeckValid(deck)) {
      throw new IllegalArgumentException("Illegeal argument FreecellController.playGame().");
    }

    //unnecessary shuffle
    if (shuffle) {
      Collections.shuffle(deck);
    }

    //modulo 3 is applied to determine proper input (0 == srcPile, 1 == index, 2== destPile)
    int inputsTaken = 0;

    //init variables to store user input
    PileType sourcePileType = PileType.FOUNDATION;
    PileType destPileType = PileType.FOUNDATION;
    int srcPileNum = 0;
    int destPileNum = 0;
    int srcCardIndex = 0;
    String userInput = "";

    //messages displayed to prompt input
    String[] inputMessages = {
        "\nPlease enter a source pile to draw your card: ",
        "\nPlease enter the index of the source pile you would like to grab: ",
        "\nPlease enter the destination pile you would like to" +
            " drop your selection on: "};

    //error messages displayed to prompt after erroneous input
    String[] errorMessages = {
        "\nPlease re-enter a source pile to draw your card.\n",
        "\nPlease re-enter the index of the source pile you would like to grab.\n",
        "\nPlease re-enter the destination pile you would like to" +
            " drop your selection on.\n"};

    //init scanner to take user input
    Scanner scan = new Scanner(this.in);

    //start the game
    model.startGame(deck, shuffle);

    //welcome message
    System.out.println("\nWelcome to Freecell, a DX production.\n");

    //while the game is going on
    while (!model.isGameOver()) {

      //print the current state
      if (inputsTaken % 3 == 0) {
        try {
          System.out.println("\n");
          this.out.append(model.getGameState() + "\n");
          System.out.println("\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      //get user input
      System.out.println(inputMessages[inputsTaken % 3]);

//      userInput = (scan.hasNext()) ? scan.nextLine() : "no next line";

      if (scan.hasNextLine()) {
        userInput = scan.nextLine();
      } else {
        try {
          this.out.append("Game over." + "\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
        return;
      }

      //check for "quit" status
      if (quitGame(userInput)) {
        return;
      }

      //get user intent
      switch (inputsTaken % 3) {

        //gather sourcePile
        case 0:
          //if length of input > 2
          //or character != 'o' or 'c'
          if (!readibleSrc(userInput)) {
            //display error message
            System.out.println("\nInvalid card index input.\n" +
                "Inputs taken: " + inputsTaken + "\n" +
                "User input: " + userInput);
            //break from switch and retry for valid input
            break;
          }

          //if intended source pile is 'OPEN'
          //gather source pile character
          char srcPileChar = userInput.toLowerCase().charAt(0);
          if (srcPileChar == 'o') {

            //set destination index and type inputs for move() method
            sourcePileType = PileType.OPEN;
            srcPileNum = Integer.parseInt(userInput.substring(1)) - 1;

            //append to this.out
            try {
              //increment inputsTaken
              this.out.append(userInput);
              inputsTaken++;
            } catch (IOException e) {
              throw new IllegalStateException("Failed to append " + userInput + " to " +
                  "Appendable output");
            }
          }

          //if intended source pile is 'CASCADE'
          else if (srcPileChar == 'c') {

            //set destination index and type inputs for move() method
            sourcePileType = PileType.CASCADE;
            srcPileNum = Integer.parseInt(userInput.substring(1)) - 1;

            try {
              //append input to sout and increment
              this.out.append(userInput + "\n");
              inputsTaken++;
            } catch (IOException e) {
              throw new IllegalStateException("Failed to append " + userInput + " to " +
                  "Appendable output");
            }
          }

          //break from case 0
          break;

        //gather srcCard index
        case 1:

          //if input is invalid
          if (!validIndex(userInput)) {
            System.out.println("\nInvalid card index input.\n" +
                "Inputs taken: " + inputsTaken + "\n" +
                "User input: " + userInput);
            break;
          }

          //gather source pile index
          srcCardIndex = Integer.parseInt(userInput) - 1;
          try {
            //append input to sout and increment
            this.out.append(userInput + "\n");
            inputsTaken++;
          } catch (IOException e) {
            throw new IllegalStateException("Failed to append " + userInput + " to " +
                "Appendable output");
          }

          //break from case 1
          break;

        //gather destinationPile
        case 2:

          //if length of input > 2
          //or character != 'o' and 'c' and 'f'
          if (!validDest(userInput)) {
            System.out.println("\nInvalid card destination input.\n" +
                "Inputs taken: " + inputsTaken + "\n" +
                "User input: " + userInput);
            break;
          }

          //if intended destination pile is 'OPEN'
          //gather destination pile character
          char destPileChar = userInput.toLowerCase().charAt(0);
          if (destPileChar == 'o') {

            //set destination index and type inputs for move() method
            destPileType = PileType.OPEN;
            destPileNum = Integer.parseInt(userInput.substring(1)) - 1;

            //append to this.out
            try {
              //append input to sout and increment
              this.out.append(userInput + "\n");
              inputsTaken++;
            } catch (IOException e) {
              throw new IllegalStateException("Failed to append " + userInput + " to " +
                  "Appendable output");
            }
          }

          //if intended destination pile is 'CASCADE'
          else if (destPileChar == 'c') {

            //set destination index and type inputs for move() method
            destPileType = PileType.CASCADE;
            destPileNum = Integer.parseInt(userInput.substring(1)) - 1;

            //append to this.out
            try {
              //append input to sout and increment
              this.out.append(userInput + "\n");
              inputsTaken++;
            } catch (IOException e) {
              throw new IllegalStateException("Failed to append " + userInput + " to " +
                  "Appendable output");
            }
          }

          //if intended destination pile is 'FOUNDATION'
          else if (destPileChar == 'f') {

            //set destination index and type inputs for move() method
            destPileType = PileType.FOUNDATION;
            destPileNum = Integer.parseInt(userInput.substring(1)) - 1;

            //append to this.out
            try {
              //increment inputsTaken
              this.out.append(userInput + "\n");
              inputsTaken++;
            } catch (IOException e) {
              throw new IllegalStateException("Failed to append " + userInput + " to " +
                  "Appendable output");
            }
          }

          try {
            model.move(sourcePileType, srcPileNum, srcCardIndex, destPileType, destPileNum);
          } catch (Exception e1) {
            System.out.println("\n\n" + e1.getMessage());
            try {
              this.out.append("Invalid move. Try again." + "\n");
            } catch (IOException e2) {
              System.err.println("Failed to append error message");
            }
            break;
          }
          //break from case 2
          break;

        //intentionally left blank
        default:
          break;
      }
    }

    //print results
    System.out.println(model.getGameState());
    try {
      this.out.append("Game Over." + "\n");
    } catch (IOException e) {
      System.err.println("Game NOT over" + e);
    }
  }

  /**
   * Evaluates src pile validity. Returns true if input is valid.
   */
  private boolean readibleSrc(String userInput) {
    //gather source pile character
    char srcPileChar = userInput.toLowerCase().charAt(0);
    //if length of input > 2
    //or character != 'o' or 'c'
    if (userInput.length() > 2
        ||
        (srcPileChar != 'c' && srcPileChar != 'o')) {
      return false;
    }
    return true;
  }

  /**
   * Evaluates dest pile validity. Returns true if input is valid.
   */
  private boolean validDest(String userInput) {
    //gather destination pile character
    char destPileChar = userInput.toLowerCase().charAt(0);
    //if length of input > 2
    //or character != 'o' and 'c' and 'f'
    if (userInput.length() > 2
        ||
        (destPileChar != 'c' && destPileChar != 'o' && destPileChar != 'f')) {
      return false;
    }
    return true;
  }

  /**
   * Evaluates index validity. Returns true if input is valid.
   */
  private boolean validIndex(String userInput) {
    try {
      int index = Integer.parseInt(userInput);
      if (index < 1 || index > 52) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * A helper function to check if the deck that is being used to start the game is a valid one.
   *
   * @param myList the deck of card objects that is used in dealing and starting the game
   * @return returns true if the deck is valid, else otherwise
   */
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

  /**
   * Quits the game if userinput.matches "q".
   */
  private boolean quitGame(String userInput) {
    //check for "quit" status
    if (userInput.toLowerCase().contains("q")) {
      try {
        this.out.append("Game quit prematurely." + "\n");
        return true;
      } catch (IOException e) {
        System.err.println("Failed to quit game.");
        return false;
      }
    }
    return false;
  }
}