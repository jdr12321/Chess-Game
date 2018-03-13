package model.moveStrategies;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.ChessPiece;
import model.ChessPlayer;
import model.ChessTile;
import model.IChessModel;

import static model.ChessPieceType.NONE;

/**
 * Contains moving rules for a bishop
 */
public class BishopMoveStrategy implements IChessMoveStrategy {

  private IChessModel model;

  /**
   * Constructs this object containing the model of the chess board for it to judge moves against
   * @param model the chess board to examine for valid moves
   */
  public BishopMoveStrategy(IChessModel model) {
    this.model = model;
  }

  @Override
  public boolean canMove(int horizFrom, int vertFrom, int horizTo, int vertTo) {

    if (horizFrom < 1 || vertFrom < 1 || horizTo < 1 || vertTo < 1
            || horizFrom > 8 || vertFrom > 8 || horizTo > 8 || vertTo > 8) {
      return false;
    }

    ChessPiece pieceAtFrom = model.getPieceAt(horizFrom, vertFrom);
    ChessPiece pieceAtTo = model.getPieceAt(horizTo, vertTo);

    if (pieceAtFrom.getType().equals(NONE)) {
      return false;
    }

    if (pieceAtTo.getPlayer().equals(pieceAtFrom.getPlayer())) {
      return false;
    }

    int deltaHoriz = horizTo - horizFrom;
    int deltaVert = vertTo - vertFrom;

    if (Math.abs(deltaHoriz) == Math.abs(deltaVert) && deltaHoriz != 0) {
      int deltaHorizDirection = deltaHoriz / Math.abs(deltaHoriz);
      int deltaVertDirection = deltaVert / Math.abs(deltaVert);

      for (int i = 1; i < Math.abs(deltaHoriz); i++) {
        ChessPiece p = model.getPieceAt(horizFrom + i * deltaHorizDirection,
                vertFrom + i * deltaVertDirection);
        if (p.getType() != NONE) {
          return false;
        }
      }
      return true;
    }

    return false;
  }

  @Override
  public boolean cantMoveAtAll(int horiz, int vert) {

    for (int i = -1; i <= 1; i += 2) {
      for (int j = -1; j <= 1; j += 2) {
        try {
          model.move(horiz, vert, horiz + i, vert + j);
          model.undo();
          return false;
        } catch (IllegalArgumentException e) {

        }
      }
    }

    return true;
  }

  @Override
  public List<Point> lineOfSight(int horizFrom, int vertFrom, int horizTo, int vertTo) {

    List<Point> acc = new ArrayList<>();

    int deltaHoriz = horizTo - horizFrom;
    int deltaVert = vertTo - vertFrom;

    if (Math.abs(deltaHoriz) == Math.abs(deltaVert) && deltaHoriz != 0) {

      int deltaHorizDirection = deltaHoriz / Math.abs(deltaHoriz);
      int deltaVertDirection = deltaVert / Math.abs(deltaVert);

      for (int i = 1; i < Math.abs(deltaHoriz); i++) {
        acc.add(new Point(horizFrom + i * deltaHorizDirection,
                vertFrom + i * deltaVertDirection));
      }
    }

    return acc;

  }
}
