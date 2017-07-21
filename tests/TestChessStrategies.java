import org.junit.Before;
import org.junit.Test;

import model.ChessBoard;
import model.ChessPlayer;
import model.IChessModel;
import model.moveStrategies.IChessMoveStrategy;
import utils.ChessMoveStrategyFactory;

import static model.ChessPieceType.BISHOP;
import static model.ChessPieceType.KING;
import static model.ChessPieceType.KNIGHT;
import static model.ChessPieceType.PAWN;
import static model.ChessPieceType.QUEEN;
import static model.ChessPieceType.ROOK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Justin on 7/14/2017.
 */
public class TestChessStrategies {

  IChessModel model;
  IChessMoveStrategy rook;
  IChessMoveStrategy bishop;
  IChessMoveStrategy king;
  IChessMoveStrategy queen;
  IChessMoveStrategy bpawn;
  IChessMoveStrategy wpawn;
  IChessMoveStrategy knight;

  @Before
  public void init() {
    model = new ChessBoard();
    model.startGame();
    rook = ChessMoveStrategyFactory.create(ROOK, ChessPlayer.WHITE, model);
    bishop = ChessMoveStrategyFactory.create(BISHOP, ChessPlayer.WHITE, model);
    king = ChessMoveStrategyFactory.create(KING, ChessPlayer.WHITE, model);
    queen = ChessMoveStrategyFactory.create(QUEEN, ChessPlayer.WHITE, model);
    bpawn = ChessMoveStrategyFactory.create(PAWN, ChessPlayer.BLACK, model);
    wpawn = ChessMoveStrategyFactory.create(PAWN, ChessPlayer.WHITE, model);
    knight = ChessMoveStrategyFactory.create(KNIGHT, ChessPlayer.WHITE, model);
  }

  @Test
  public void whitePawnCannotMove() {
    assertFalse(wpawn.cantMoveAtAll(2, 8));
    model.move(1, 7, 3, 8);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                     N+ \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+    R+ \n");
    assertTrue(wpawn.cantMoveAtAll(2, 8));
    model.move(7, 7, 5, 7);
    model.move(5, 7, 4, 7);
    model.move(4, 7, 3, 7);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P-    P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                  P- N+ \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+    R+ \n");
    assertFalse(wpawn.cantMoveAtAll(2, 8));
  }

  @Test
  public void blackPawnCannotMove() {
    assertFalse(bpawn.cantMoveAtAll(7, 8));
    model.move(8, 7, 6, 8);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B-    R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                     N- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    assertTrue(bpawn.cantMoveAtAll(7, 8));
    model.move(2, 7, 4, 7);
    model.move(4, 7, 5, 7);
    model.move(5, 7, 6, 7);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B-    R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                  P+ N- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+    P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    assertFalse(bpawn.cantMoveAtAll(7, 8));
  }

  @Test
  public void rookCantMove() {
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    assertTrue(rook.cantMoveAtAll(1, 1));
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
    assertFalse(rook.cantMoveAtAll(1, 1));
    model.move(3, 3, 1,  2);
    model.move(2, 1, 3, 1);
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+                      \n" +
                    "   P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    assertFalse(rook.cantMoveAtAll(1, 1));

  }

  @Test
  public void bishopCantMove() {
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");
    assertTrue(bishop.cantMoveAtAll(1, 3));
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
    assertFalse(bishop.cantMoveAtAll(1, 3));
  }

  @Test
  public void knightCannotMove() {
    assertEquals(model.getState(),
            "R- N- B- Q- K- B- N- R- \n" +
                    "P- P- P- P- P- P- P- P- \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "                        \n" +
                    "P+ P+ P+ P+ P+ P+ P+ P+ \n" +
                    "R+ N+ B+ Q+ K+ B+ N+ R+ \n");

    assertFalse(knight.cantMoveAtAll(1, 2));
    model.move(2, 1, 3, 1);
    model.move(2, 3, 3, 3);
    assertTrue(knight.cantMoveAtAll(1, 2));
  }
}
