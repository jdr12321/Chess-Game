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
 * Created by Justin on 7/20/2017.
 */
public class ChessPromotingController implements IChessController {

  IChessModel model;
  IChessView view;

  ChessKeyListener keyListener = new ChessKeyListener();

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
