package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import controller.IChessController;
import model.IChessModel;

/**
 * Contains general functionalities that all implementations of rendering chess games should
 * support
 */
public interface IChessView {

  /**
   * Links a model to this view so user interactions with this view affect the game state
   * @param model the model to link
   */
  void setModel(IChessModel model);

  /**
   * Initialize the state of the view (dimensions, default data, images, etc).
   */
  void initialize();

  /**
   * Sets the message to be displayed to the user based on actions unfolding in the game
   * @param message the message to display
   */
  void setMessage(String message);

  /**
   * Recreates the image of the board based on the current board state
   */
  void redraw();

  /**
   * Sets the square at the given point as the selected square, meaning it will glow a different
   * color
   * @param p the point to designate the current selected square
   */
  void setSelectedSquare(Point p);

  /**
   * returns the currently selected square
   * @return the private field holding the selected square
   */
  Point getSelectedSquare();

  /**
   * Sets a listener to all buttons on this board. Any button presses will be handled by
   * the given listener
   * @param l the listener
   */
  void setButtonListener(ActionListener l);

  /**
   * Sets the listener for keyboard presses. When keys are pressed on the keyboard, the given
   * listener will process them
   * @param l the key listener
   */
  void setKeyListener(KeyListener l);

  /**
   * Deletes current button listeners from the chess buttons
   */
  void deleteButtonListener();

  /**
   * Deletes current key listener if one exists
   */
  void deleteKeyListener();
}
