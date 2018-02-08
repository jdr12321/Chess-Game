package model;

/**
 * Chess player enumeration
 */
public enum ChessPlayer {

  BLACK("Black"), WHITE("White"), NEITHER("neither");

  public String s;

  /**
   * Constructs a chess player with a string representation of its color
   * @param s the string representation
   */
  ChessPlayer(String s) {
    this.s = s;
  }



  @Override
  public String toString() {
    switch (this) {
      case BLACK:
        return "-";
      case WHITE:
        return "+";
      default:
        return " ";
    }
  }

  public ChessPlayer getOppositePlayer() {
    if (this == WHITE) {
      return BLACK;
    }
    else if (this == BLACK) {
      return WHITE;
    }
    else { return NEITHER; }
  }
}
