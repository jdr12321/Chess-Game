package utils;

import model.ChessPieceType;
import model.ChessPlayer;
import model.IChessModel;
import model.moveStrategies.BishopMoveStrategy;
import model.moveStrategies.BlackPawnMoveStrategy;
import model.moveStrategies.IChessMoveStrategy;
import model.moveStrategies.KingMoveStrategy;
import model.moveStrategies.KnightMoveStrategy;
import model.moveStrategies.QueenMoveStrategy;
import model.moveStrategies.RookMoveStrategy;
import model.moveStrategies.WhitePawnMoveStrategy;

/**
 * IChessMoveStrategy factory, giving chess types and players their moving rules
 */
public class ChessMoveStrategyFactory {

  /**
   * Creates the appropriate chess move rule set for a given chess type and player and chess board.
   * @param type the type of piece
   * @param player the player color of the piece
   * @param model the chess board to store inside the piece for valid move checks
   * @return the IChessMoveStategy structure appropriate for the type and player.
   */
  public static IChessMoveStrategy create(ChessPieceType type, ChessPlayer player, IChessModel model) {
    switch (type) {
      case PAWN:
        if (player.equals(ChessPlayer.WHITE)) {
          return new WhitePawnMoveStrategy(model);
        }
        else {
          return new BlackPawnMoveStrategy(model);
        }
      case ROOK:
        return new RookMoveStrategy(model);
      case BISHOP:
        return new BishopMoveStrategy(model);
      case KNIGHT:
        return new KnightMoveStrategy(model);
      case KING:
        return new KingMoveStrategy(model);
      case QUEEN:
        return new QueenMoveStrategy(model);
      default:
        return null;
    }
  }
}
