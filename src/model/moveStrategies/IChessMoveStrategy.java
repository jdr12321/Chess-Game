package model.moveStrategies;

import java.awt.*;
import java.util.List;

import model.ChessTile;

/**
 * Moving strategies for different chess pieces. dictates how the piece can move and if a move
 * is possible.
 */
public interface IChessMoveStrategy {

  boolean canMove(int horizFrom, int vertFrom, int horizTo, int vertTo);

  boolean cantMoveAtAll(int horiz, int vert);

  List<Point> lineOfSight(int horizFrom, int vertFrom, int horizTo, int vertTo);
}
