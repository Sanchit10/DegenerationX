package freecell.model;

import java.util.ArrayList;
import java.util.List;

public class FreecellModel implements FreecellOperations {


  private int numberOfCascadePiles;
  private int numberOfOpenPiles;
  private int numberOfFoundationPiles;

  public FreecellModel(int numberOfCascadePiles,int numberOfOpenPiles) {
    this.numberOfCascadePiles = numberOfCascadePiles;
    this.numberOfOpenPiles = numberOfOpenPiles;
    this.numberOfFoundationPiles=4;
  }



  // method that is used to
  public static FreecellModelBuilder getBuilder() {
    return new FreecellModelBuilder();
  }

// the first method looks correct. Do point out any mistake that I might have made

  @Override
  public List<Card> getDeck() {
    return Card.deckOfCards;
  }











 //nested builder class that implements the FreecellOperationsBuilder
  public static class FreecellModelBuilder implements FreecellOperationsBuilder {


    //not sure if this is the way to go about it for these methods!
    private int numberOfCascadePiles;
   private int numberOfOpenPiles;
   private int numberOfFoundationPiles;

   private FreecellModelBuilder(int numberOfCascadePiles, int numberOfOpenPiles){
     this.numberOfCascadePiles=numberOfCascadePiles;
     this.numberOfOpenPiles=numberOfOpenPiles;
     this.numberOfFoundationPiles=4;
   }






   public FreecellOperationsBuilder cascades(int c) {
      if (c < 4 || c > 8) {
        throw new IllegalStateException("The game can only have cascade piles between 4 and 8");
      }
      this.numberOfCascadePiles=c;
      return this;
    }

    public FreecellOperationsBuilder opens(int o) {
      if (o < 1 || o > 4) {
        throw new IllegalStateException("The game can only have open piles between 1 and 4");
      }
      this.numberOfOpenPiles=o;
      return this;
    }


    public <Card> FreecellModel build() {

      return new FreecellModel(this.numberOfCascadePiles,this.numberOfOpenPiles,);
    }


  }

}

