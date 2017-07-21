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
 * Created by Justin on 7/11/2017.
 */
public class BlackPawnMoveStrategy implements IChessMoveStrategy {

  IChessModel model;

  public BlackPawnMoveStrategy(IChessModel model) {
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

    if (vertFrom == vertTo && horizTo == horizFrom - 1 && pieceAtTo.getType().equals(NONE)) {
      return true;
    }

    if (horizFrom == 7 && vertFrom == vertTo && horizTo == horizFrom - 2
            && pieceAtTo.getType().equals(NONE)) {
      return true;
    }

    if (horizTo == horizFrom - 1 && Math.abs(vertFrom - vertTo) == 1 && pieceAtTo.getType() != NONE) {
      return true;
    }

    return false;
  }

  @Override
  public boolean cantMoveAtAll(int horiz, int vert) {
    /*ChessPiece bottomLeft = model.getPieceAt(horiz - 1, vert - 1);
    ChessPiece bottom = model.getPieceAt(horiz - 1, vert);
    ChessPiece bottomRight = model.getPieceAt(horiz - 1, vert + 1);

    return  (horiz == 1) || (bottom.getType() != NONE && (vert == 1 || bottomLeft.getPlayer() != ChessPlayer.WHITE)
            && (vert == 8 || bottomRight.getPlayer() != ChessPlayer.WHITE));*/

    for (int i = -1; i <= 1; i++) {
      try {
        model.move(horiz, vert, horiz - 1, vert + i);
        model.undo();
        return false;
      } catch (IllegalArgumentException e) {

      }
    }

    return true;
  }

  @Override
  public List<Point> lineOfSight(int horizFrom, int vertFrom, int horizTo, int vertTo) {
    return new ArrayList<>();
  }
}
