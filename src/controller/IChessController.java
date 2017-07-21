package controller;

import java.awt.event.ActionListener;

import model.IChessModel;
import view.IChessView;

/**
 * Created by Justin on 7/19/2017.
 */
public interface IChessController  {

  void setModel(IChessModel model);

  void setView(IChessView view);
}
