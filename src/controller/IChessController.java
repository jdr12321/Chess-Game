package controller;

import model.IChessModel;
import view.IChessView;

/**
 * General interface for actions that should be supported by chess controllers that mediate
 * interactions between the chess view and the game state
 */
public interface IChessController  {

  /**
   * Link a model to this controller to be modified with interactions
   * @param model the model to be linked
   */
  void setModel(IChessModel model);

  /**
   * Link a view to this controller to be modified with interactions
   * @param view the view to be linked
   */
  void setView(IChessView view);


}
