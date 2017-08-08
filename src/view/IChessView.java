package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import controller.IChessController;
import model.IChessModel;

/**
 * Created by Justin on 7/11/2017.
 */
public interface IChessView {

  void setModel(IChessModel model);

  void initialize();

  void setMessage(String message);

  void redraw();

  void setSelectedSquare(Point p);

  Point getSelectedSquare();

  void setButtonListener(ActionListener l);

  void setKeyListener(KeyListener l);

  void deleteButtonListener();

  void deleteKeyListener();
}
