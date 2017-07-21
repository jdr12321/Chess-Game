package controller.Commands;

import model.ChessPlayer;
import model.IChessModel;
import view.IChessView;

/**
 * Created by Justin on 7/20/2017.
 */
public class NewGameCommand implements KeyCommand {

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
