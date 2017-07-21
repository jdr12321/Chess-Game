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
 * Created by Justin on 7/11/2017.
 */
public class ChessMoveStrategyFactory {

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
