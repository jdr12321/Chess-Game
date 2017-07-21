package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import controller.Commands.KeyCommand;
import model.IChessModel;
import view.IChessView;

/**
 * Created by Justin on 7/20/2017.
 */
public class ChessKeyListener implements KeyListener {

  IChessModel model;
  IChessView view;

  Map<Integer, KeyCommand> allCommands = new HashMap<Integer, KeyCommand>();

  public void addCommand(int code, KeyCommand command) {
    allCommands.put(code, command);
  }

  public void setModel(IChessModel model) {
    this.model = model;
  }

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


    KeyCommand command = allCommands.get(e.getKeyCode());

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
