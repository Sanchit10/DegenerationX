package freecell.model;

import java.util.List;


public class main {

  public static void main(String args[]) {
    FreecellMultiMoveModel myModel = FreecellMultiMoveModel.getBuilder1().cascades(4).opens(20)
        .build1();
    List deck = myModel.getDeck();
    myModel.startGame(deck, false);

    myModel.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    myModel.move(PileType.CASCADE, 0, 11, PileType.OPEN, 1);
    myModel.move(PileType.CASCADE, 0, 10, PileType.OPEN, 2);
    myModel.move(PileType.CASCADE, 0, 9, PileType.OPEN, 3);

    System.out.println(myModel.getGameState());

    System.out.println();

    myModel.move(PileType.CASCADE, 3, 12, PileType.OPEN, 4);
    myModel.move(PileType.CASCADE, 3, 11, PileType.OPEN, 5);
    myModel.move(PileType.CASCADE, 3, 10, PileType.OPEN, 6);
    myModel.move(PileType.CASCADE, 3, 9, PileType.OPEN, 7);
    myModel.move(PileType.CASCADE, 3, 8, PileType.OPEN, 8);
    System.out.println(myModel.getGameState());

    myModel.move(PileType.OPEN, 7, 0, PileType.CASCADE, 0);
    System.out.println(myModel.getGameState());

    System.out.println();

    myModel.move(PileType.CASCADE, 0, 8, PileType.CASCADE, 3);

    System.out.println();
    System.out.println(myModel.getGameState());

    myModel.move(PileType.CASCADE, 1, 12, PileType.OPEN, 9);
    myModel.move(PileType.CASCADE, 1, 11, PileType.OPEN, 10);
    myModel.move(PileType.CASCADE, 1, 10, PileType.OPEN, 11);
    myModel.move(PileType.OPEN, 6, 0, PileType.CASCADE, 1);

    myModel.move(PileType.OPEN, 1, 0, PileType.CASCADE, 1);
    myModel.move(PileType.CASCADE, 2, 12, PileType.CASCADE, 1);
    myModel.move(PileType.CASCADE, 1, 10, PileType.OPEN, 11);

    myModel.move(PileType.OPEN, 11, 0, PileType.CASCADE, 3);
//    myModel.move(PileType.CASCADE,1,11,PileType.CASCADE,3);
    myModel.move(PileType.CASCADE, 2, 12, PileType.OPEN, 13);
    myModel.move(PileType.CASCADE, 2, 11, PileType.OPEN, 14);
    myModel.move(PileType.CASCADE, 2, 10, PileType.OPEN, 15);
    myModel.move(PileType.CASCADE, 2, 9, PileType.OPEN, 16);
//    myModel.move(PileType.CASCADE,1,9,PileType.CASCADE,2);
    System.out.println();
    System.out.println(myModel.getGameState());
    myModel.move(PileType.CASCADE, 1, 9, PileType.CASCADE, 2);
    System.out.println();
    System.out.println(myModel.getGameState());

    myModel.move(PileType.CASCADE, 2, 8, PileType.CASCADE, 0);
    System.out.println();
    System.out.println(myModel.getGameState());

    myModel.move(PileType.CASCADE, 2, 7, PileType.OPEN, 17);
    System.out.println();
    System.out.println(myModel.getGameState());

    myModel.move(PileType.CASCADE, 0, 7, PileType.CASCADE, 2);
    System.out.println();
    System.out.println(myModel.getGameState());

    System.out.println(myModel.isGameOver());
  }
}


