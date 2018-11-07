package freecell.model;

import java.io.InputStreamReader;
import java.util.List;

import freecell.controller.FreecellController;


public class main {

  public static void main(String args[]) {
//    FreecellMultiMoveModel myModel = new FreecellMultiMoveModel.Builder().cascades(4).opens(4)
//        .build();
//    List deck = myModel.getDeck();
//    //myModel.startGame(deck, false);
//    FreecellController controller = new FreecellController(new InputStreamReader(System.in), System.out);
//    controller.playGame(deck, myModel, false);

    FreecellMultiMoveModel myModel = FreecellMultiMoveModel.getBuilder().cascades(4).opens(4)
        .build();
    List deck = myModel.getDeck();
    myModel.startGame(deck, false);
System.out.println(myModel.getGameState());
    System.out.println();
//    myModel.move(PileType.CASCADE,0,12,PileType.FOUNDATION,0);
//    myModel.move(PileType.CASCADE,1,12,PileType.FOUNDATION,1);
//    myModel.move(PileType.CASCADE,2,12,PileType.FOUNDATION,2);
//    myModel.move(PileType.CASCADE,3,12,PileType.FOUNDATION,3);
//
//
//    myModel.move(PileType.CASCADE,0,11,PileType.OPEN,0);
//    myModel.move(PileType.CASCADE,3,11,PileType.OPEN,2);
//    myModel.move(PileType.CASCADE,3,10,PileType.OPEN,1);
//    myModel.move(PileType.CASCADE,2,11,PileType.CASCADE,0);
//    myModel.move(PileType.CASCADE,0,10,PileType.CASCADE,3);
//    myModel.move(PileType.CASCADE,3,10,PileType.CASCADE,0);

    myModel.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    myModel.move(PileType.CASCADE, 3, 12, PileType.OPEN, 1);
    myModel.move(PileType.CASCADE, 3, 11, PileType.OPEN, 2);
    myModel.move(PileType.CASCADE, 1, 12, PileType.OPEN, 3);
    myModel.move(PileType.CASCADE, 2, 12, PileType.CASCADE, 1);
    myModel.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);
    myModel.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 1);
    myModel.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 2);
    myModel.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 1);
    myModel.move(PileType.CASCADE, 3, 10, PileType.OPEN, 1);
//    myModel.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
////    myModel.move(PileType.CASCADE, 2, 11, PileType.OPEN, 3);
////    myModel.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 1);
////    myModel.move(PileType.CASCADE, 1, 11, PileType.CASCADE, 2);
////    myModel.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 0);
////    myModel.move(PileType.CASCADE,2,10,PileType.CASCADE,1);
//    myModel.move(PileType.CASCADE,0,10,PileType.CASCADE,3);
    myModel.move(PileType.CASCADE,1,11,PileType.CASCADE,2);



    System.out.println(myModel.getGameState());
    System.out.println();

  }
}


