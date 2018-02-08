package model.moveStrategies;

import java.awt.*;
import java.util.List;

import model.ChessTile;

/**
 * Moving strategies for different chess pieces. dictates how the piece can move and if a move
 * is possible.
 */
public interface IChessMoveStrategy {

  /**
   * Can a piece following these moving rules
   * move from the given coordinates to the given coordinates?
   * @param horizFrom the original x coordinate
   * @param vertFrom the original y coordinate
   * @param horizTo the desired x coordinate to travel to
   * @param vertTo the desired y coordinate to travel to
   * @return true if it can move, else false.
   */
  boolean canMove(int horizFrom, int vertFrom, int horizTo, int vertTo);

  /**
   * By the current board condition and these rules, can this piece not move to any square from the
   * given point?
   * @param horiz the original x coordinate
   * @param vert the original y coordinate
   * @return true if it cannot move at all, else false
   */
  boolean cantMoveAtAll(int horiz, int vert);

  /**
   * Calculate the list of coordinates that stand between the given start and end coordinates
   * by these rules of moving.
   * @param horizFrom the original x coordinate
   * @param vertFrom the original y coordinate
   * @param horizTo the desired x coordinate to travel to
   * @param vertTo the desired y coordinate to travel to
   * @return a list of points in the line of sight between these two points
   */
  List<Point> lineOfSight(int horizFrom, int vertFrom, int horizTo, int vertTo);
}
