import controller.ChessController;
import controller.IChessController;
import model.ChessBoard;
import model.IChessModel;
import view.ChessBoardFrame;
import view.IChessView;

/**
 * Main chess game class
 */
public class ChessGame {

  public static void main(String[] args) {
    IChessModel model = new ChessBoard();
    IChessView view = new ChessBoardFrame(model);
    IChessController controller = new ChessController();
    controller.setModel(model);
    controller.setView(view);
    view.setModel(model);
    model.startGame();
    view.initialize();
  }
}
