package controller.Commands;

import model.ChessPlayer;
import model.IChessModel;
import view.IChessView;

/**
 * Command sets the game start back to a starting one
 */
public class NewGameCommand implements IKeyCommand {

  @Override
  public void execute(IChessModel model, IChessView view) {
    model.startGame();
    if (model.currentPlayer() == ChessPlayer.BLACK) {
      model.advancePlayer();
    }
    view.setMessage("White's move.");
    view.redraw();
  }
}
