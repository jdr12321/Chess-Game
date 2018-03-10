import org.junit.Before;
import org.junit.Test;


import java.awt.*;
import java.util.ArrayList;

import model.ChessBoard;
import model.ChessPiece;
import model.ChessPlayer;
import model.IChessModel;

import static model.ChessPieceType.KING;
import static model.ChessPieceType.KNIGHT;
import static model.ChessPieceType.NONE;
import static model.ChessPieceType.QUEEN;
import static org.junit.Assert.assertEquals;

/**
 * Tests of logic behind chess game
 */
public class TestChessBoard {

  IChessModel model;

  @Before
  public void init() {
    model = new ChessBoard();
  }

  @Test
  public void testGetStateWithStartGame() {

    // 8 rows of 8 columns of simple spaces before starting game
    assertEquals(model.getState(),
            "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n");


    // Starting game should now have two rows of whites at bottom and two blacks at top
    model.startGame();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

  }

  @Test
  public void testMovingWhitePawn() {
    model.startGame();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    model.move(2, 1, 4, 1);

    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");



    // can now only move one forward
    model.move(4, 1, 5, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    model.move(5, 1, 6, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "P+                      \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // Can move diagonally by pawn to take a piece
    model.move(6, 1, 7, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P+ P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    model.move(7, 2, 8, 1);
    assertEquals(model.getState(),
            "P+ N- B- Q- K- B- N- R- \n" +
                    "P-    P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveWhitePawnForwardTwice() {

    model.startGame();

    model.move(2, 1, 4, 1);

    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // now cannot move two spaces forward again
    model.move(4, 1, 6, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveForwardWithWhitePawnToTakePiece() {
    model.startGame();

    model.move(2, 1, 4, 1);
    model.move(4, 1, 5, 1);
    model.move(5, 1, 6, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "P+                      \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // cannot move forward to overtake a piece
    model.move(6, 1, 7, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotCaptureOwnPieceWithWhitePawn() {
    // Cannot capture own piece
    model.move(2, 2, 3, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    model.move(2, 3, 3, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotNormallyMoveDiagonalWithWhitePawn() {
    // cannot normally move diagonal if not capturing.
    model.move(2, 3, 3, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveBackwardWithWhitePawn() {
    model.move(2, 2, 3, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    // cannot move backward
    model.move(3, 2, 2, 2);
  }

  @Test
  public void testMovingBlackPawn() {
    model.startGame();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    model.move(7, 1, 5, 1);

    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "   P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "P-                      \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // Now, can only move one forward
    model.move(5, 1, 4, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "   P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P-                      \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    model.move(4, 1, 3, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "   P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P-                      \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // Can move diagonally by one to take a piece
    model.move(3, 1, 2, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "   P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P- P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    model.move(2, 2, 1, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "   P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "P- N+ B+ Q+ K+ B+ N+ R+ \n");
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveBlackPawnForwardTwice() {

    model.startGame();

    model.move(7, 1, 5, 1);

    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "   P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "P-                      \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // now cannot move two spaces forward again
    model.move(5, 1, 3, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveForwardWithBlackPawnToTakePiece() {
    model.startGame();

    model.move(7, 1, 5, 1);
    model.move(5, 1, 4, 1);
    model.move(4, 1, 3, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "   P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P-                      \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // cannot move forward to overtake a piece
    model.move(3, 1, 2, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotCaptureOwnPieceWithBlackPawn() {
    // Cannot capture own piece
    model.move(7, 2, 6, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P-    P- P- P- P- P- P- \n" +
                    "   P-                   \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    model.move(7, 3, 6, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotNormallyMoveDiagonalWithBlackPawn() {
    // cannot normally move diagonal if not capturing.
    model.move(7, 3, 6, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveBackwardWithBlackPawn() {
    model.move(7, 2, 6, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P-    P- P- P- P- P- P- \n" +
                    "   P-                   \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    // cannot move backward
    model.move(6, 2, 7, 2);
  }

  @Test
  public void testMovingWithKnight() {
    model.startGame();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // knights move in L shape
    model.move(1, 2, 3, 3);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "      N+                \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+    B+ Q+ K+ B+ N+ R+ \n");
    model.move(3, 3, 1, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    model.move(1, 2, 3, 3);
    model.move(3, 3, 4, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "N+                      \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+    B+ Q+ K+ B+ N+ R+ \n");
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveKnightNotInLShape() {
    model.startGame();
    // knights move in L shape
    model.move(1, 2, 3, 3);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "      N+                \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+    B+ Q+ K+ B+ N+ R+ \n");

    // cannot move in not L shape
    model.move(3, 3, 4, 4);
  }

  @Test
  public void testMovingKing() {
    model.startGame();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    model.move(2, 5, 3, 5);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "            P+          \n" +
                    "P+ P+ P+ P+    P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // can move one vertical
    model.move(1, 5, 2, 5);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "            P+          \n" +
                    "P+ P+ P+ P+ K+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+    B+ N+ R+ \n");

    // can move one diagonal
    model.move(2, 5, 3, 4);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "         K+ P+          \n" +
                    "P+ P+ P+ P+    P+ P+ P+ \n" +
                    "R+ N+ B+ Q+    B+ N+ R+ \n");

  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveKingTwoSpaces() {
    model.startGame();
    model.move(2, 5, 3, 5);
    model.move(1, 5, 2, 5);
    model.move(2, 5, 3, 4);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "         K+ P+          \n" +
                    "P+ P+ P+ P+    P+ P+ P+ \n" +
                    "R+ N+ B+ Q+    B+ N+ R+ \n");

    // cannot move two up
    model.move(3, 4, 5, 4);
  }

  @Test
  public void testMovingRook() {
    model.startGame();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    model.move(2, 1, 4, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // rook can move vertically
    model.move(1, 1, 3, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "R+                      \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "   N+ B+ Q+ K+ B+ N+ R+ \n");
    // can move in opposite direction vertically
    model.move(3, 1, 1, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // can move horizontally
    model.move(1, 1, 3, 1);
    model.move(3, 1, 3, 8);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "                     R+ \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "   N+ B+ Q+ K+ B+ N+ R+ \n");
    // can move in opposite direction horizontally
    model.move(3, 8, 3, 5);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "            R+          \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "   N+ B+ Q+ K+ B+ N+ R+ \n");

    // can capture pieces
    model.move(3, 5, 7, 5);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- R+ P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "   N+ B+ Q+ K+ B+ N+ R+ \n");
  }

  @Test (expected = IllegalArgumentException.class)
  public void rookCannotJumpOverPieces() {
    model.startGame();
    model.move(1, 1, 3, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void rookCannotMoveNotInStraightLine() {
    model.startGame();
    model.move(2, 2, 4, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+                   \n" +
                    "                        \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // cannot move, for instance, diagonally
    model.move(1, 1, 3, 3);
  }

  @Test
  public void testMovingWithBishop() {
    model.startGame();
    model.move(2, 2, 3, 2);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // can move up left
    model.move(1, 3, 3, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "B+ P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+    Q+ K+ B+ N+ R+ \n");

    // can move up right, capture a piece
    model.move(3, 1, 7, 5);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- B+ P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+    Q+ K+ B+ N+ R+ \n");

    // can move down right
    model.move(7, 5, 6, 6);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P-    P- P- P- \n" +
                    "               B+       \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+    Q+ K+ B+ N+ R+ \n");

    // can move down left
    model.move(6, 6, 4, 4);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P-    P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "         B+             \n" +
                    "   P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+    Q+ K+ B+ N+ R+ \n");
  }

  @Test (expected = IllegalArgumentException.class)
  public void bishopCannotJumpOverPieces() {
    model.startGame();
    model.move(1, 6, 3, 8);
  }

  @Test (expected = IllegalArgumentException.class)
  public void bishopCannotMoveOffBoard() {
    model.move(1, 3, 0, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveWithBishopInNonDiagonalDirection() {
    model.startGame();
    model.move(2, 3, 3, 3);

    // cannot move upward
    model.move(1, 3, 2, 3);
  }

  @Test
  public void testMovingQueen() {
    model.startGame();
    model.move(7, 4, 5, 4);
    assertEquals(model.getState(),
              "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P-    P- P- P- P- \n" +
                    "                        \n" +
                    "         P-             \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // can move vertically
    model.move(8, 4, 6, 4);
    assertEquals(model.getState(),
            "R- N- B-    K- B- N- R- \n" +
                    "P- P- P-    P- P- P- P- \n" +
                    "         Q-             \n" +
                    "         P-             \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // can move horizontally
    model.move(6, 4, 6, 8);
    assertEquals(model.getState(),
            "R- N- B-    K- B- N- R- \n" +
                    "P- P- P-    P- P- P- P- \n" +
                    "                     Q- \n" +
                    "         P-             \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // can move diagonally
    model.move(6, 8, 4, 6);
    model.move(4, 6, 3, 7);
    assertEquals(model.getState(),
            "R- N- B-    K- B- N- R- \n" +
                    "P- P- P-    P- P- P- P- \n" +
                    "                        \n" +
                    "         P-             \n" +
                    "                        \n" +
                    "                  Q-    \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
  }

  @Test (expected = IllegalArgumentException.class)
  public void queenCannotJumpOtherPieces() {
    model.startGame();
    model.move(1, 4, 3, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void queenCannotMoveNotDiagonalOrVerticalOrHorizontal() {

    model.startGame();
    model.move(2, 4, 4, 4);
    model.move(1, 4, 3, 4);
    model.move(3, 4, 5, 6);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "               Q+       \n" +
                    "         P+             \n" +
                    "                        \n" +
                    "P+ P+ P+    P+ P+ P+ P+ \n" +
                    "R+ N+ B+    K+ B+ N+ R+ \n");

    // queen cannot move in a weird direction, like a knight move for instance
    model.move(5, 6, 4, 8);
  }

  // check method properly determines if a king is in check.
  @Test
  public void testCheckVerification() {
    model.startGame();
    assertEquals(model.isInCheck(ChessPlayer.WHITE), false);
    model.move(7, 5, 5, 5);
    model.move(8, 4, 5, 7);
    model.move(5, 7, 2, 4);
    assertEquals(model.getState(),
              "R- N- B-    K- B- N- R- \n" +
                    "P- P- P- P-    P- P- P- \n" +
                    "                        \n" +
                    "            P-          \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ Q- P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    assertEquals(model.isInCheck(ChessPlayer.WHITE), true);
    model.move(2, 4, 3, 4);
    assertEquals(model.getState(),
            "R- N- B-    K- B- N- R- \n" +
                    "P- P- P- P-    P- P- P- \n" +
                    "                        \n" +
                    "            P-          \n" +
                    "                        \n" +
                    "         Q-             \n" +
                    "P+ P+ P+    P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    assertEquals(model.isInCheck(ChessPlayer.WHITE), false);
  }

  @Test (expected = IllegalArgumentException.class)
  public void cannotMoveToPutOwnKingInCheck() {
    model.startGame();
    assertEquals(model.isInCheck(ChessPlayer.WHITE), false);
    model.move(7, 5, 5, 5);
    model.move(8, 4, 5, 7);
    model.move(5, 7, 2, 4);
    model.move(2, 4, 3, 3);
    model.move(1, 4, 2, 4);
    assertEquals(model.getState(),
            "R- N- B-    K- B- N- R- \n" +
                    "P- P- P- P-    P- P- P- \n" +
                    "                        \n" +
                    "            P-          \n" +
                    "                        \n" +
                    "      Q-                \n" +
                    "P+ P+ P+ Q+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+    K+ B+ N+ R+ \n");
    model.move(2, 4, 3, 4);
  }

  @Test
  public void testIsGameOver() {
    model.startGame();
    assertEquals(model.isInCheck(ChessPlayer.WHITE), false);
    model.move(7, 5, 5, 5);
    model.move(8, 4, 5, 7);
    model.move(5, 7, 2, 4);
    model.move(2, 4, 3, 3);
    assertEquals(model.getState(),
            "R- N- B-    K- B- N- R- \n" +
                    "P- P- P- P-    P- P- P- \n" +
                    "                        \n" +
                    "            P-          \n" +
                    "                        \n" +
                    "      Q-                \n" +
                    "P+ P+ P+    P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    assertEquals(model.isGameOver(), false);
    model.move(3, 3, 3, 5);
    model.move(2, 2, 3, 2);
    model.move(1, 2, 3, 1);
    model.move(3, 5, 3, 3);

    assertEquals(model.getState(),
            "R- N- B-    K- B- N- R- \n" +
                    "P- P- P- P-    P- P- P- \n" +
                    "                        \n" +
                    "            P-          \n" +
                    "                        \n" +
                    "N+ P+ Q-                \n" +
                    "P+    P+    P+ P+ P+ P+ \n" +
                    "R+    B+ Q+ K+ B+ N+ R+ \n");
    assertEquals(model.isGameOver(), false);

    model.move(3, 3, 2, 4);
    model.move(2, 4, 1, 4);
    model.move(1, 4, 1, 3);
    model.move(1, 3, 2, 4);
    model.move(2, 4, 3, 3);
    model.move(7, 8, 5, 8);
    model.move(8, 8, 6, 8);
    model.move(6, 8, 6, 4);
    assertEquals(model.getState(),
            "R- N- B-    K- B- N-    \n" +
                    "P- P- P- P-    P- P-    \n" +
                    "         R-             \n" +
                    "            P-       P- \n" +
                    "                        \n" +
                    "N+ P+ Q-                \n" +
                    "P+    P+    P+ P+ P+ P+ \n" +
                    "R+          K+ B+ N+ R+ \n");
    assertEquals(model.isGameOver(), true);
  }

  @Test
  public void testUndoFunction() {
    model.startGame();
    model.move(2, 2, 3, 2);
    model.move(1, 3, 3, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "B+ P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+    Q+ K+ B+ N+ R+ \n");
    model.undo();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+                   \n" +
                    "P+    P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    model.undo();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    // cannot undo at start state with no moves
    model.undo();
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

  }

  @Test
  public void testStalemateCheck() {
    model.startGame();
    assertEquals(model.isStaleMate(), false);
    model.move(7, 5, 6, 5);
    model.move(8, 4, 4, 8);
    model.move(4, 8, 2, 8);
    model.move(2, 8, 2, 7);
    model.move(2, 7, 2, 6);
    model.move(2, 6, 2, 5);
    model.move(2, 5, 2, 4);
    model.move(2, 4, 2, 3);
    model.move(2, 3, 2, 2);
    model.move(2, 2, 2, 1);
    model.move(2, 1, 1, 1);
    model.move(1, 1, 1, 2);
    model.move(1, 2, 2, 3);
    model.move(2, 3, 1, 4);
    model.move(1, 4, 2, 5);
    model.move(2, 5, 2, 8);
    model.move(2, 8, 1, 8);
    model.move(1, 8, 1, 7);
    model.move(1, 7, 1, 6);
    model.move(1, 6, 2, 7);
    model.move(7, 1, 5, 1);
    model.move(8, 1, 6, 1);
    model.move(6, 1, 6, 2);
    model.move(6, 2, 1, 2);
    model.move(1, 5, 1, 4);
    model.move(2, 7, 2, 6);
    assertEquals(model.getState(),
            "   N- B-    K- B- N- R- \n" +
                    "   P- P- P-    P- P- P- \n" +
                    "            P-          \n" +
                    "P-                      \n" +
                    "                        \n" +
                    "                        \n" +
                    "               Q-       \n" +
                    "   R- B+ K+             \n");

    // not in check
    assertEquals(model.isInCheck(ChessPlayer.WHITE), false);

    // but nothing can move, so stalemate
    assertEquals(model.isStaleMate(), true);
  }

  @Test
  public void testPromotingPawns() {
    model.startGame();
    model.move(2, 1, 4, 1);
    model.move(4, 1, 5, 1);
    model.move(5, 1, 6, 1);
    model.move(6, 1, 7, 2);
    model.move(7, 2, 8, 1);

    model.move(7, 8, 5, 8);
    model.move(5, 8, 4, 8);
    model.move(4, 8, 3, 8);
    model.move(3, 8, 2, 7);
    model.move(2, 7, 1, 8);

    assertEquals(model.getState(),
            "P+ N- B- Q- K- B- N- R- \n" +
                    "P-    P- P- P- P- P-    \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+    P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ P- \n");

    model.promote(KNIGHT);

    assertEquals(model.getState(),
            "N+ N- B- Q- K- B- N- R- \n" +
                    "P-    P- P- P- P- P-    \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "   P+ P+ P+ P+ P+    P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ N- \n");


  }

  @Test
  public void testFullGame() {
    model.startGame();
    model.move(2, 5, 3, 5);
    model.move(1, 4, 5, 8);

    model.move(7, 5, 6, 5);
    model.move(8, 5, 7, 5);

    model.move(5, 8, 7, 8);
    model.move(7, 8, 8, 8);
    model.move(8, 8, 8, 7);
    model.move(8, 7, 8, 6);
    model.move(8, 6, 8, 5);
    model.move(8, 5, 8, 4);
    model.move(8, 4, 8, 3);
    model.move(8, 3, 8, 2);
    model.move(8, 2, 8, 1);

    model.move(7, 5, 6, 4);
    model.move(6, 4, 5, 3);
    model.move(5, 3, 4, 2);
    model.move(4, 2, 4, 1);

    model.move(8, 1, 7, 1);

    assertEquals(model.getState(),
                "                        \n" +
                      "Q+ P- P- P-    P- P-    \n" +
                      "            P-          \n" +
                      "                        \n" +
                      "K-                      \n" +
                      "            P+          \n" +
                      "P+ P+ P+ P+    P+ P+ P+ \n" +
                      "R+ N+ B+    K+ B+ N+ R+ \n");

    model.advancePlayer();

    assertEquals(model.currentPlayer(), ChessPlayer.BLACK);
    assertEquals(model.isInCheck(ChessPlayer.BLACK), true);

    ChessPiece king = model.getPieceAt(4, 1);

    assertEquals(king.getMoveStrategy().cantMoveAtAll(4, 1), false);

    assertEquals(model.isGameOver(), false);
  }


}
