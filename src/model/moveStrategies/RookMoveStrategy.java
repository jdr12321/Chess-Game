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
 * Contains moving rules for a rook
 */
public class RookMoveStrategy implements IChessMoveStrategy {

  IChessModel model;

  /**
   * Constructs this object containing the model of the chess board for it to judge moves against
   * @param model the chess board to examine for valid moves
   */
  public RookMoveStrategy(IChessModel model) {
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

    int deltaHoriz = Math.abs(horizFrom - horizTo);
    int deltaVert = Math.abs(vertFrom - vertTo);

    if (horizFrom == horizTo) {
      if (vertFrom < vertTo) {
        for (int i = vertFrom + 1; i < vertTo; i++) {
          ChessPiece p = model.getPieceAt(horizFrom, i);
          if (p.getType() != NONE) {
            return false;
          }
        }
        return true;
      }
      else if (vertFrom > vertTo) {
        for (int i = vertFrom - 1; i > vertTo; i--) {
          ChessPiece p = model.getPieceAt(horizFrom, i);
          if (p.getType() != NONE) {
            return false;
          }
        }
        return true;
      }
    }

    if (vertFrom == vertTo) {
      if (horizFrom < horizTo) {
        for (int i = horizFrom + 1; i < horizTo; i++) {
          ChessPiece p = model.getPieceAt(i, vertFrom);
          if (p.getType() != NONE) {
            return false;
          }
        }
        return true;
      }
      else if (horizFrom > horizTo) {
        for (int i = horizFrom - 1; i > horizTo; i--) {
          ChessPiece p = model.getPieceAt(i, vertFrom);
          if (p.getType() != NONE) {
            return false;
          }
        }
        return true;
      }
    }


    return false;
  }

  @Override
  public boolean cantMoveAtAll(int horiz, int vert) {
    /*ChessPlayer rookPlayer = model.getPieceAt(horiz, vert).getPlayer();
    ChessPiece top = model.getPieceAt(horiz + 1, vert);
    ChessPiece bottom = model.getPieceAt(horiz - 1, vert);
    ChessPiece left = model.getPieceAt(horiz, vert - 1);
    ChessPiece right = model.getPieceAt(horiz, vert + 1);

    return (top == null || top.getPlayer() == rookPlayer)
            && (right == null || right.getPlayer() == rookPlayer)
            && (left == null || left.getPlayer() == rookPlayer)
            && (bottom == null || bottom.getPlayer() == rookPlayer);*/

    try {
      model.move(horiz, vert, horiz, vert + 1);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz, vert - 1);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz + 1, vert);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz - 1, vert);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    return true;
  }

  @Override
  public List<Point> lineOfSight(int horizFrom, int vertFrom, int horizTo, int vertTo) {

    List<Point> acc = new ArrayList<>();

    int deltaHoriz = Math.abs(horizFrom - horizTo);
    int deltaVert = Math.abs(vertFrom - vertTo);

    if (horizFrom == horizTo) {
      if (vertFrom < vertTo) {
        for (int i = vertFrom + 1; i < vertTo; i++) {
          acc.add(new Point(horizFrom, i));
        }
      }
      else if (vertFrom > vertTo) {
        for (int i = vertFrom - 1; i > vertTo; i--) {
          acc.add(new Point(horizFrom, i));
        }
      }
    }

    if (vertFrom == vertTo) {
      if (horizFrom < horizTo) {
        for (int i = horizFrom + 1; i < horizTo; i++) {
          acc.add(new Point(i, vertFrom));
        }
      }
      else if (horizFrom > horizTo) {
        for (int i = horizFrom - 1; i > horizTo; i--) {
          acc.add(new Point(i, vertFrom));
        }
      }
    }

    return acc;
  }
}
