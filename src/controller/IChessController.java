package controller;

import model.IChessModel;
import view.IChessView;

/**
 * Created by Justin on 7/19/2017.
 */
public interface IChessController  {

  void setModel(IChessModel model);

  void setView(IChessView view);

  /**
   * Created by Justin on 7/20/2017.
   */
  class ChessPromotingController {


  }
}
