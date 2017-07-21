package controller.Commands;

import model.IChessModel;
import view.IChessView;

/**
 * Created by Justin on 7/20/2017.
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
