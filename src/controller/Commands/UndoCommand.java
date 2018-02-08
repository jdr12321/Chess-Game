package controller.Commands;

import model.IChessModel;
import view.IChessView;

/**
 * Undoes the last move on the board
 */
public class UndoCommand implements KeyCommand {

  @Override
  public void execute(IChessModel model, IChessView view) {

    if (model.undo()) {
      model.advancePlayer();
    }

    view.setMessage(model.currentPlayer().s +"'s move.");
    view.redraw();
  }
}
