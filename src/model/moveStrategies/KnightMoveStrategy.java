package model.moveStrategies;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor;

import model.ChessPiece;
import model.ChessPlayer;
import model.ChessTile;
import model.IChessModel;

import static model.ChessPieceType.NONE;

/**
 * Contains moving rules for a knight
 */
public class KnightMoveStrategy implements IChessMoveStrategy {

  private IChessModel model;

  /**
   * Constructs this object containing the model of the chess board for it to judge moves against
   * @param model the chess board to examine for valid moves
   */
  public KnightMoveStrategy(IChessModel model) {
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
    int delta = deltaHoriz + deltaVert;

    if (delta == 3 && deltaHoriz != 3 && deltaVert != 3) {
      return true;
    }

    return false;
  }

  @Override
  public boolean cantMoveAtAll(int horiz, int vert) {

    try {
      model.move(horiz, vert, horiz + 2, vert + 1);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz + 1, vert + 2);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz + 2, vert - 1);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz + 1, vert - 2);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz - 2, vert + 1);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz - 1, vert + 2);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz - 1, vert - 2);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    try {
      model.move(horiz, vert, horiz - 2, vert - 1);
      model.undo();
      return false;
    } catch (IllegalArgumentException e) {

    }

    return true;
  }

  @Override
  public List<Point> lineOfSight(int horizFrom, int vertFrom, int horizTo, int vertTo) {
    return new ArrayList<>();
  }


}
