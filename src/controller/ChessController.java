package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import controller.Commands.NewGameCommand;
import controller.Commands.UndoCommand;
import model.IChessModel;
import view.IChessView;

import static model.ChessPlayer.WHITE;

/**
 * Controller part of MVC pattern, handles interactions between view and model for most gameplay
 */
public class ChessController implements IChessController {

  private IChessModel model;
  private IChessView view;

  private ChessButtonListener buttonListener = new ChessButtonListener();
  private ChessKeyListener keyListener = new ChessKeyListener();

  /**
   * Default constructor, for most of game only two commands supported are n for new game, u for
   * undo move
   */
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
