package model.moveStrategies;

import java.awt.*;
import java.util.List;

import model.IChessModel;

/**
 * Created by Justin on 7/11/2017.
 */
public class QueenMoveStrategy implements IChessMoveStrategy {

  private final RookMoveStrategy rookMoveStrategy;
  private final BishopMoveStrategy bishopMoveStrategy;

  public QueenMoveStrategy(IChessModel model) {
    this.rookMoveStrategy = new RookMoveStrategy(model);
    this.bishopMoveStrategy = new BishopMoveStrategy(model);
  }

  @Override
  public boolean canMove(int horizFrom, int vertFrom, int horizTo, int vertTo) {

    if (horizFrom < 1 || vertFrom < 1 || horizTo < 1 || vertTo < 1
            || horizFrom > 8 || vertFrom > 8 || horizTo > 8 || vertTo > 8) {
      return false;
    }

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
