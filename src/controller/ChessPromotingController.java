package controller;

import java.awt.event.KeyEvent;

import controller.Commands.PromoteBishopCommand;
import controller.Commands.PromoteKnightCommand;
import controller.Commands.PromoteQueenCommand;
import controller.Commands.PromoteRookCommand;
import model.ChessPiece;
import model.IChessModel;
import view.IChessView;

/**
 * Controller specifically for time when a pawn needs to be promoted. Disables other actions while
 * user input on what to promote the piece to is waited for.
 */
public class ChessPromotingController implements IChessController {

  private IChessModel model;
  private IChessView view;

  private ChessKeyListener keyListener = new ChessKeyListener();

  /**
   * Default constructor, contains key press commands for n, r, q, b for different possible
   * promotions
   */
  public ChessPromotingController() {
    keyListener.addCommand(KeyEvent.VK_N, new PromoteKnightCommand());
    keyListener.addCommand(KeyEvent.VK_R, new PromoteRookCommand());
    keyListener.addCommand(KeyEvent.VK_Q, new PromoteQueenCommand());
    keyListener.addCommand(KeyEvent.VK_B, new PromoteBishopCommand());
  }

  @Override
  public void setModel(IChessModel model) {
    this.model = model;
    keyListener.setModel(model);
  }

  @Override
  public void setView(IChessView view) {
    this.view = view;
    keyListener.setView(view);
    view.setKeyListener(keyListener);
  }
}
