package controller.Commands;

import controller.ChessController;
import model.ChessPieceType;
import model.IChessModel;
import view.IChessView;

/**
 * Created by Justin on 7/20/2017.
 */
public class PromoteKnightCommand implements KeyCommand {

  @Override
  public void execute(IChessModel model, IChessView view) {
    model.promote(ChessPieceType.KNIGHT);
    view.redraw();

    view.deleteButtonListener();
    view.deleteKeyListener();

    ChessController c = new ChessController();
    c.setModel(model);
    c.setView(view);

    view.setMessage(model.currentPlayer().s + "'s move.");
  }
}
