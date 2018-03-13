package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import utils.ChessMoveStrategyFactory;

import static model.ChessPieceType.*;
import static model.ChessPlayer.*;

/**
 * Implementation of a chess board based on standard chess rules.
 */
public class ChessBoard implements IChessModel {

  private List<ChessTile> tiles = new ArrayList<>();
  private Stack<List<ChessTile>> prevMoves = new Stack<>();

  private ChessPlayer nextPlayer;
  private ChessPlayer prevPlayer;

  /**
   * Default chess board constructor. Builds an empty board with no tiles, sets white to first
   * player
   */
  public ChessBoard() {
    for (int i = 1; i <= 8; i++) {
      for (int j = 1; j <= 8; j++) {
        tiles.add(new ChessTile(i, j));
      }
    }

    this.nextPlayer = WHITE;
    this.prevPlayer = BLACK;
  }

  @Override
  public void startGame() {

    for (ChessTile t : tiles) {
      int horiz = t.getHorizontalPosition();
      int vert = t.getVerticalPosition();
      ChessPieceType type = NONE;
      ChessPlayer player = NEITHER;

      if (horiz == 1 || horiz == 2) {
        player = WHITE;
      }
      else if (horiz == 7 || horiz == 8) {
        player = BLACK;
      }

      if (vert == 1 || vert == 8) {
        type = ROOK;
      }
      else if (vert == 2 || vert == 7) {
        type = KNIGHT;
      }
      else if (vert == 3 || vert == 6) {
        type = BISHOP;
      }
      else if (vert == 4) {
        type = QUEEN;
      }
      else if (vert == 5) {
        type = KING;
      }

      if (horiz == 2 || horiz == 7) {
        type = PAWN;
      }

      if (horiz == 3 || horiz == 4 || horiz == 5 || horiz == 6) {
        type = NONE;
      }

      t.setChessPiece(new ChessPiece(type, player,
              ChessMoveStrategyFactory.create(type, player, this)));
    }

    prevMoves = new Stack<>();
  }

  @Override
  public void move(int horizFrom, int vertFrom, int horizTo, int vertTo) {
    ChessTile tileFrom = getTileAt(horizFrom, vertFrom);
    ChessTile tileTo = getTileAt(horizTo, vertTo);
    ChessPiece pieceFrom = tileFrom.getChessPiece();

    if (pieceFrom.getMoveStrategy() == null) {
      throw new IllegalArgumentException("Cannot move this piece.");
    }

    if (pieceFrom.getMoveStrategy().canMove(horizFrom, vertFrom, horizTo, vertTo)) {

      List<ChessTile> copy = new ArrayList<>();
      for (ChessTile t : tiles) {
        copy.add(new ChessTile(t.getHorizontalPosition(), t.getVerticalPosition(),
                t.getChessPiece()));
      }

      prevMoves.add(copy);

      tileFrom.setChessPiece(new ChessPiece(NONE, NEITHER, null));
      tileTo.setChessPiece(pieceFrom);
    }
    else {
      throw new IllegalArgumentException("Cannot move this way.");
    }

    if (isInCheck(pieceFrom.getPlayer())) {
      undo();
      throw new IllegalArgumentException("The king is in check.");
    }


  }

  @Override
  public boolean undo() {
    if (prevMoves.size() != 0) {
      tiles = prevMoves.pop();
      return true;
    }
    return false;
  }

  @Override
  public ChessPlayer currentPlayer() {
    return nextPlayer;
  }

  @Override
  public void advancePlayer() {
    ChessPlayer temp = nextPlayer;
    nextPlayer = prevPlayer;
    prevPlayer = temp;
  }

  @Override
  public void promote(ChessPieceType to) {
    for (ChessTile t : tiles) {
      ChessPiece p = getPieceAt(t.getHorizontalPosition(), t.getVerticalPosition());

      if ((t.getHorizontalPosition() == 1 || t.getHorizontalPosition() == 8)
              && p.getType() == PAWN) {
        t.setChessPiece(new ChessPiece(to, p.getPlayer(),
                ChessMoveStrategyFactory.create(to, p.getPlayer(), this)));
      }
    }
  }

  @Override
  public boolean isInCheck(ChessPlayer player) {

    ChessTile t = spotOfKing(player);

    int kingHoriz = t.getHorizontalPosition();
    int kingVert = t.getVerticalPosition();

    return spotInCheckBy(kingHoriz, kingVert, player.getOppositePlayer(), true);
  }

  @Override
  public boolean isGameOver() {
    if (!isInCheck(nextPlayer)) {
      return false;
    }

    ChessTile t = spotOfKing(nextPlayer);

    int kingHoriz = t.getHorizontalPosition();
    int kingVert = t.getVerticalPosition();

    // Can the king move out of check himself?
    if (!t.getChessPiece().getMoveStrategy().cantMoveAtAll(kingHoriz, kingVert)) {
      return false;
    }

    List<ChessTile> aggressors = collectAggressingTiles(kingHoriz, kingVert, prevPlayer);
    if (aggressors.size() < 1) {
      return false;
    }
    else if (aggressors.size() > 2) {
      return true;
    }
    else {
      ChessTile aggressingTile = aggressors.get(0);
      // can the aggressing piece get taken this turn?
      if (spotInCheckBy(aggressingTile.getHorizontalPosition(),
              aggressingTile.getVerticalPosition(), nextPlayer, false)
              || (spotInCheckBy(aggressingTile.getHorizontalPosition(),
              aggressingTile.getVerticalPosition(), nextPlayer, true)
              && !spotInCheckBy(aggressingTile.getHorizontalPosition(),
              aggressingTile.getVerticalPosition(), currentPlayer(), true))) {
        return false;
      }

      List<Point> aggressorLineOfSight = aggressingTile.getChessPiece().getMoveStrategy()
              .lineOfSight(aggressingTile.getHorizontalPosition(), aggressingTile.getVerticalPosition(),
                      kingHoriz, kingVert);

      // can the aggressors line of sight be blocked by anything but the king in check itself?
      for (Point p : aggressorLineOfSight) {
        if (spotInCheckBy(p.x, p.y, nextPlayer, false)) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public boolean isStaleMate() {

    if (isInCheck(nextPlayer)) {
      return false;
    }

    for (ChessTile t : tiles) {
      if (t.getChessPiece().getPlayer() == nextPlayer
              && !t.getChessPiece().getMoveStrategy().cantMoveAtAll(t.getHorizontalPosition(),
              t.getVerticalPosition())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public ChessPiece getPieceAt(int horiz, int vert) {

    if (horiz < 1 || horiz > 8 || vert < 1 || vert > 8) {
      return null;
    }

    for (ChessTile t : tiles) {
      if (t.getHorizontalPosition() == horiz
              && t.getVerticalPosition() == vert) {
        return t.getChessPiece();
      }
    }
    throw new IllegalArgumentException("Piece not found");
  }

  @Override
  public String getState() {
    String acc = "";

    for (int i = 7; i >= 0; i--) {
      for (int j = 0; j < 8; j++) {
        ChessTile t = tiles.get(i * 8 + j);
        ChessPiece p = t.getChessPiece();

        acc = acc + p.toString() + " ";
      }

      acc = acc + "\n";
    }

    return acc;
  }

  /**
   * returns the tile with the given coordinates
   * @param horiz desired x position
   * @param vert desired y position
   * @return the tile structure with coordinates horiz and vert
   */
  private ChessTile getTileAt(int horiz, int vert) {
    for (ChessTile t : tiles) {
      if (t.getHorizontalPosition() == horiz
              && t.getVerticalPosition() == vert) {
        return t;
      }
    }
    throw new IllegalArgumentException("Piece not found");
  }

  /**
   * Checks if the given tile is in "check" by the given player, meaning they contain a piece that
   * can move to it.
   * @param horiz the x position in question
   * @param vert the y position in question
   * @param player the player whose pieces will be checked
   * @param kingCanCheck if true, then the player's king can be the piece to move there
   * @return
   */
  private boolean spotInCheckBy(int horiz, int vert, ChessPlayer player, boolean kingCanCheck) {
    for (ChessTile t : tiles) {
      ChessPiece p = t.getChessPiece();
      if (p.getPlayer() == player && p.getPlayer() != getPieceAt(horiz, vert).getPlayer()
              && p.getMoveStrategy().canMove(t.getHorizontalPosition(), t.getVerticalPosition(),
              horiz, vert) && (kingCanCheck || p.getType() != KING)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns the location of the king on the board matching the player's color
   * @param player the player color of king desired
   * @return the tile that the king is on.
   */
  private ChessTile spotOfKing(ChessPlayer player) {
    for (ChessTile t : tiles) {
      ChessPiece p = t.getChessPiece();
      if (p.getType() == KING && p.getPlayer() == player) {
        return t;
      }
    }
    return null;
  }

  /**
   * Collects all tiles that currently contain pieces of the given player's color
   * that can move to this position.
   * @param horiz the x coordinate of this position
   * @param vert the y coordinate of this position
   * @param player the player to find pieces for
   * @return a list of all chess tiles satisfying this condition
   */
  private List<ChessTile> collectAggressingTiles(int horiz, int vert, ChessPlayer player) {

    List<ChessTile> acc = new ArrayList<>();

    for (ChessTile t : tiles) {
      ChessPiece p = t.getChessPiece();
      if (p.getPlayer() == player
              && p.getMoveStrategy().canMove(t.getHorizontalPosition(), t.getVerticalPosition(),
              horiz, vert)) {
        acc.add(t);
      }
    }

    return acc;
  }
}
