package classses;

public class CardRank {

  public final rankEnum rank;
  private final int pips;

  /**
   * Enumeration represents the ranks in a standard deck of cards.
   */
  private enum rankEnum {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING
  }
  /**
   * Constructor.
   * @param rank
   * @throws IllegalArgumentException
   */
  public CardRank(int rank) throws IllegalArgumentException{
    switch (rank){
      case 0:
        this.rank = rankEnum.TWO;
        this.pips = 2;
        break;
      case 1:
        this.rank = rankEnum.THREE;
        this.pips = 3;
        break;
      case 2:
        this.rank = rankEnum.FOUR;
        this.pips = 4;
        break;
      case 3:
        this.rank = rankEnum.FIVE;
        this.pips = 5;
        break;
      case 4:
        this.rank = rankEnum.SIX;
        this.pips = 6;
        break;
      case 5:
        this.rank = rankEnum.SEVEN;
        this.pips = 7;
        break;
      case 6:
        this.rank = rankEnum.EIGHT;
        this.pips = 8;
        break;
      case 7:
        this.rank = rankEnum.NINE;
        this.pips = 9;
        break;
      case 8:
        this.rank = rankEnum.TEN;
        this.pips = 10;
        break;
      case 9:
        this.rank = rankEnum.JACK;
        this.pips = 0;
        break;
      case 10:
        this.rank = rankEnum.QUEEN;
        this.pips = 0;
        break;
      case 11:
        this.rank = rankEnum.KING;
        this.pips = 0;
        break;
      case 12:
        this.rank = rankEnum.ACE;
        this.pips = 1;
        break;
      default:
        throw new IllegalArgumentException("Invalid input for CARDRANK constructor.");
    }
  }

  /**
   * Getter for name of the card rank.
   *
   * @return
   */
  public String getName() {
    return this.rank.name();
  }

  /**
   * Getter for pips number of card rank (face cards have no pips).
   *
   * @return
   */
  public int getPips() { return this.pips; }
}
