package controller;

import java.awt.event.ActionEvent;

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



  @Override
  public void setModel(IChessModel model) {
    this.model = model;
    buttonListener.setModel(model);
  }

  @Override
  public void setView(IChessView view) {
    this.view = view;
    buttonListener.setView(view);
    view.setButtonListener(buttonListener);
  }
}
