package model;

import model.moveStrategies.IChessMoveStrategy;
/**
 * Created by Justin on 7/11/2017.
 */
public class ChessPiece {

  private ChessPieceType type;
  private final ChessPlayer player;
  private IChessMoveStrategy moveStrategy;

  /**
   * Constructs all the information for a chess piece
   * @param type the type of piece this is
   * @param player the player this piece is for
   * @param strategy contains checks that dictate how this piece can move
   */
  public ChessPiece(ChessPieceType type, ChessPlayer player, IChessMoveStrategy strategy) {
    if (type == null || player == null) {
      throw new IllegalArgumentException("Cannot have null inputs");
    }

    this.type = type;
    this.player = player;
    this.moveStrategy =  strategy;
  }

  /**
   * type getter
   * @return the type of this piece
   */
  public ChessPieceType getType() {
    return type;
  }

  /**
   * player getter
   * @return the player of this piece
   */
  public ChessPlayer getPlayer() {
    return player;
  }

  /**
   * Move strategy getter
   * @return the move strategy object of this piece
   */
  public IChessMoveStrategy getMoveStrategy() {
    return moveStrategy;
  }

  @Override
  public String toString() {
    return type.toString() + player.toString();
  }
}
