package freecell.model;

import java.util.List;
import java.util.PrimitiveIterator;

public class main {

  public static void main(String args[]){

    FreecellModel myModel = FreecellModel.getBuilder()
        .cascades(6).opens(4).build();
    List myList = myModel.getDeck();
    myModel.startGame(myList,false);
    System.out.println(myModel.getGameState());
    System.out.println(myModel.isGameOver());
//    System.out.println(myModel.getGameState());
//
//    myModel.move(PileType.CASCADE,1,12,PileType.OPEN,1);
//    System.out.println(myModel.getGameState());
//
//    myModel.move(PileType.CASCADE,1,11,PileType.FOUNDATION,0);
//    System.out.println(myModel.getGameState());



//    System.out.println();
//    System.out.println(myModel.getGameState());
//    System.out.println();
//    myModel.startGame(myList,false);
//    System.out.println(myModel.getGameState());



//   System.out.println(myModel.getGameState());
//    myModel.move(PileType.CASCADE,3,12,PileType.OPEN,2);
//  myModel.move(PileType.CASCADE,1,12,PileType.OPEN,0);
////  myModel.move(PileType.CASCADE,3,12,PileType.OPEN,0);
//
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println();
//
//    System.out.println(myModel.getGameState());
//
//    myModel.move(PileType.OPEN,0,0,PileType.CASCADE,3);
//
//
//    System.out.println();
//
//    System.out.println(myModel.getGameState());
//
//    System.out.println();
//
//    System.out.println();
//
//    System.out.println();
//
//
//    myModel.move(PileType.CASCADE,2,12,PileType.FOUNDATION,0);
//
//
//    System.out.println(myModel.getGameState());
//
//
//    myModel.move(PileType.CASCADE,2,11,PileType.FOUNDATION,0);
//    System.out.println();
//
//    System.out.println(myModel.getGameState());
//
//
//    myModel.move(PileType.CASCADE,3,12,PileType.FOUNDATION,1);
//    System.out.println();
//
//    System.out.println(myModel.getGameState());
//    myModel.move(PileType.OPEN,2,0,PileType.FOUNDATION,2);
//
//    myModel.move(PileType.CASCADE,3,11,PileType.FOUNDATION,2);
//    System.out.println();
////    myModel.move(PileType.CASCADE,0,12, PileType.CASCADE,1);
//
//    System.out.println(myModel.getGameState());
//
//    myModel.move(PileType.CASCADE,0,12,PileType.FOUNDATION,3);
//    System.out.println();
//    System.out.println(myModel.getGameState());
//
//
//    myModel.move(PileType.CASCADE,0,11,PileType.CASCADE,3);
//
//    System.out.println();
//    System.out.println(myModel.getGameState());
//
//    myModel.move(PileType.CASCADE,1,11,PileType.CASCADE,2);
//    System.out.println();
//    System.out.println(myModel.getGameState());
//    myModel.move(PileType.CASCADE,2,11,PileType.FOUNDATION,1);
//
//    System.out.println();
//    System.out.println(myModel.getGameState());
//    myModel.move(PileType.CASCADE,2,10,PileType.OPEN,2);
//    System.out.println();
//    System.out.println(myModel.getGameState());
//
//    myModel.move(PileType.OPEN,2,0,PileType.FOUNDATION,0);
//    System.out.println();
//    System.out.println(myModel.getGameState());
//    System.out.println();

//    myModel.startGame(myList,false);
//    System.out.println(myModel.getGameState());

  }
}
