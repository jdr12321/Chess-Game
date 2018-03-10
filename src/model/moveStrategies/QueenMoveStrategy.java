package model.moveStrategies;

import java.awt.*;
import java.util.List;

import model.IChessModel;

/**
 * Contains moving rules for a queen
 */
public class QueenMoveStrategy implements IChessMoveStrategy {

  private final RookMoveStrategy rookMoveStrategy;
  private final BishopMoveStrategy bishopMoveStrategy;

  /**
   * Constructs this object containing the model of the chess board for it to judge moves against
   * @param model the chess board to examine for valid moves
   */
  public QueenMoveStrategy(IChessModel model) {
    this.rookMoveStrategy = new RookMoveStrategy(model);
    this.bishopMoveStrategy = new BishopMoveStrategy(model);
  }

  @Override
  public boolean canMove(int horizFrom, int vertFrom, int horizTo, int vertTo) {

    return rookMoveStrategy.canMove(horizFrom, vertFrom, horizTo, vertTo)
            || bishopMoveStrategy.canMove(horizFrom, vertFrom, horizTo, vertTo);
  }

  @Override
  public boolean cantMoveAtAll(int horiz, int vert) {
    return bishopMoveStrategy.cantMoveAtAll(horiz, vert) && rookMoveStrategy.cantMoveAtAll(horiz, vert);
  }

  @Override
  public List<Point> lineOfSight(int horizFrom, int vertFrom, int horizTo, int vertTo) {
    List<Point> rook = rookMoveStrategy.lineOfSight(horizFrom, vertFrom, horizTo, vertTo);
    List<Point> bishop = bishopMoveStrategy.lineOfSight(horizFrom, vertFrom, horizTo, vertTo);

    if (rook.size() != 0) {
      return rook;
    }
    else {
      return bishop;
    }
  }
}
