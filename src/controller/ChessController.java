package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import controller.Commands.NewGameCommand;
import controller.Commands.UndoCommand;
import model.IChessModel;
import view.IChessView;

import static model.ChessPlayer.WHITE;

/**
 * Created by Justin on 7/19/2017.
 */
public class ChessController implements IChessController {

  private IChessModel model;
  private IChessView view;

  ChessButtonListener buttonListener = new ChessButtonListener();
  ChessKeyListener keyListener = new ChessKeyListener();

  public ChessController() {
    keyListener.addCommand(KeyEvent.VK_U, new UndoCommand());
    keyListener.addCommand(KeyEvent.VK_N, new NewGameCommand());
  }

  @Override
  public void setModel(IChessModel model) {
    this.model = model;
    buttonListener.setModel(model);
    keyListener.setModel(model);
  }

  @Override
  public void setView(IChessView view) {
    this.view = view;
    buttonListener.setView(view);
    keyListener.setView(view);
    view.setButtonListener(buttonListener);
    view.setKeyListener(keyListener);
  }
}
