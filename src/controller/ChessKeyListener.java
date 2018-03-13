package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import controller.Commands.IKeyCommand;
import model.IChessModel;
import view.IChessView;

/**
 * Handles keyboard events for chess games. Whenever a key is pressed, processes it and modifies
 * the view and model if necessary
 */
public class ChessKeyListener implements KeyListener {

  private IChessModel model;
  private IChessView view;

  private Map<Integer, IKeyCommand> allCommands = new HashMap<Integer, IKeyCommand>();

  /**
   * Add a command to be supported by this listener and executed on a certain key press
   * @param code number representing the key press to execute this command
   * @param command the command to execute on the key press
   */
  public void addCommand(int code, IKeyCommand command) {
    allCommands.put(code, command);
  }

  /**
   * Link a model to this listener to be modified with key presses
   * @param model the model to be linked
   */
  public void setModel(IChessModel model) {
    this.model = model;
  }

  /**
   * Link a view to this listener to be modified with key presses
   * @param view the view to be linked
   */
  public void setView(IChessView view) {
    this.view = view;
  }

  @Override
  public void keyTyped(KeyEvent e) {

    // Currently unsupported
    return ;
  }

  @Override
  public void keyPressed(KeyEvent e) {

    IKeyCommand command = allCommands.get(e.getKeyCode());

    if (command != null) {
      command.execute(model, view);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // currently unsupported
    return ;
  }
}
