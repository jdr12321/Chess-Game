package model;

import static model.ChessPieceType.NONE;
import static model.ChessPlayer.NEITHER;

/**
 * Created by Justin on 7/11/2017.
 */
public class ChessTile {

  private final int horizontalPosition;
  private final int verticalPosition;
  ChessPiece piece;

  public ChessTile(int horizontalPosition, int verticalPosition) {
    this.horizontalPosition = horizontalPosition;
    this.verticalPosition = verticalPosition;
    this.piece = new ChessPiece(NONE, NEITHER, null);
  }

  public ChessTile(int horizontalPosition, int verticalPosition, ChessPiece piece) {
    this(horizontalPosition, verticalPosition);
    this.piece = piece;
  }

  /**
   * horizontal position getter.
   * @return the horizontal position of this tile.
   */
  public int getHorizontalPosition() {
    return horizontalPosition;
  }

  /**
   * vertical position getter.
   * @return the vertical position of this tile.
   */
  public int getVerticalPosition() {
    return verticalPosition;
  }

  /**
   * Chess piece getter.
   * @return the chess piece of this tile.
   */
  public ChessPiece getChessPiece() {
    return piece;
  }

  /**
   * chess piece setter
   * @param p the new chess piece for this tile.
   */
  public void setChessPiece(ChessPiece p) {
    this.piece = p;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ChessTile chessTile = (ChessTile) o;

    if (horizontalPosition != chessTile.horizontalPosition) return false;
    return verticalPosition == chessTile.verticalPosition;
  }

  @Override
  public int hashCode() {
    int result = horizontalPosition;
    result = 31 * result + verticalPosition;
    return result;
  }
}
