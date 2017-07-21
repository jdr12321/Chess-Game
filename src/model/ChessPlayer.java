package model;

/**
 * Created by Justin on 7/11/2017.
 */
public enum ChessPlayer {

  BLACK, WHITE, NEITHER;

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
