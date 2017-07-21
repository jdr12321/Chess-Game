package model;

import java.util.List;

/**
 * Model containing all supported operations of chess boards.
 */
public interface IChessModel {

  /**
   * Starts a game of chess with a specific starting piece layout. Places down pawns, rooks,
   * bishops, knights, a queen and king for each color black and white.
   */
  void startGame();

  /**
   * Moves a piece from one chess tile to another, if the move is valid.
   * @param horizFrom the horizontal position to be moved from.
   * @param horizTo the horizontal position to be moved to.
   * @param vertFrom the vertical position to be moved from.
   * @param vertTo the vertical position to be moved to.
   */
  void move(int horizFrom, int vertFrom, int horizTo, int vertTo);

  /**
   * Goes back one move in time.
   */
  void undo();

  /**
   * Determine who is the next player to move in the chess game.
   *
   * @return the player color for the next player to move.
   */
  ChessPlayer currentPlayer();

  /**
   * Advances the player so that next time currentPlayer()
   * is invoked, the next next player is returned.
   */
  void advancePlayer();

  /**
   * Determines if the given player's king is in check.
   * @param player the player of question
   * @return true if the player's king is being contested, else false.
   */
  boolean isInCheck(ChessPlayer player);

  /**
   * Determines if the game is over.
   * @return true if the player needing to move is in checkmate, else false.
   */
  boolean isGameOver();

  /**
   * Determines if the game is in a stalemate, or a tie. This means the player that the next player
   * cannot move due to all moves causing check, but the player isn't in check currently.
   * @return true if this is the case, else false.
   */
  boolean isStaleMate();

  /**
   * Finds the piece in the chess game at the specified position.
   * @param horiz the horizontal position.
   * @param vert the vertical position.
   * @return the piece at that point
   */
  ChessPiece getPieceAt(int horiz, int vert);

  /**
   * return the state of the game as a String, mainly for testing purposes
   * @return the board formatted as a string.
   */
  String getState();

}
