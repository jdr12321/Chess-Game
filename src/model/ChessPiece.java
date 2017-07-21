package model;

import model.moveStrategies.IChessMoveStrategy;
/**
 * Created by Justin on 7/11/2017.
 */
public class ChessPiece {

  private ChessPieceType type;
  private final ChessPlayer player;
  private IChessMoveStrategy moveStrategy;

  public ChessPiece(ChessPieceType type, ChessPlayer player, IChessMoveStrategy strategy) {
    if (type == null || player == null) {
      throw new IllegalArgumentException("Cannot have null inputs");
    }

    this.type = type;
    this.player = player;
    this.moveStrategy =  strategy;
  }

  public ChessPieceType getType() {
    return type;
  }

  public ChessPlayer getPlayer() {
    return player;
  }

  public IChessMoveStrategy getMoveStrategy() {
    return moveStrategy;
  }

  @Override
  public String toString() {
    return type.toString() + player.toString();
  }
}
