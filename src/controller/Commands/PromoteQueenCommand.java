package controller.Commands;

import java.security.Key;

import controller.ChessController;
import model.ChessPieceType;
import model.IChessModel;
import view.IChessView;

/**
 * Created by Justin on 7/21/2017.
 */
public class PromoteQueenCommand implements KeyCommand {

  @Override
  public void execute(IChessModel model, IChessView view) {
    model.promote(ChessPieceType.QUEEN);
    view.redraw();

    view.deleteButtonListener();
    view.deleteKeyListener();

    ChessController c = new ChessController();
    c.setModel(model);
    c.setView(view);

    view.setMessage(model.currentPlayer().s + "'s move.");
  }
}
