package model.moveStrategies;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.ChessPiece;
import model.ChessTile;
import model.IChessModel;

import static model.ChessPieceType.NONE;
import static model.ChessPlayer.NEITHER;

/**
 * Contains moving rules for a king
 */
public class KingMoveStrategy implements IChessMoveStrategy {

  private IChessModel model;

  /**
   * Constructs this object containing the model of the chess board for it to judge moves against
   * @param model the chess board to examine for valid moves
   */
  public KingMoveStrategy(IChessModel model) {
    this.model = model;
  }

  @Override
  public boolean canMove(int horizFrom, int vertFrom, int horizTo, int vertTo) {
    ChessPiece pieceAtFrom = model.getPieceAt(horizFrom, vertFrom);
    ChessPiece pieceAtTo = model.getPieceAt(horizTo, vertTo);

    if (horizFrom < 1 || vertFrom < 1 || horizTo < 1 || vertTo < 1
            || horizFrom > 8 || vertFrom > 8 || horizTo > 8 || vertTo > 8) {
      return false;
    }

    if (pieceAtFrom.getType().equals(NONE)) {
      return false;
    }

    if (pieceAtTo.getPlayer().equals(pieceAtFrom.getPlayer())) {
      return false;
    }

    int deltaHoriz = Math.abs(horizFrom - horizTo);
    int deltaVert = Math.abs(vertFrom - vertTo);
    int delta = deltaHoriz + deltaVert;

    if (delta > 2 || delta < 1) {
      return false;
    }

    if (deltaHoriz == 1 || deltaVert == 1) {
      return true;
    }

    return false;
  }

  @Override
  public boolean cantMoveAtAll(int horiz, int vert) {

    int numInvalids = 0;

    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        try {
          model.move(horiz, vert, horiz + i, vert + j);
          model.undo();
        } catch (IllegalArgumentException e) {
          numInvalids++;
        }
      }
    }

    return numInvalids == 9;
  }

  @Override
  public List<Point> lineOfSight(int horizFrom, int vertFrom, int horizTo, int vertTo) {
    return new ArrayList<>();
  }
}
