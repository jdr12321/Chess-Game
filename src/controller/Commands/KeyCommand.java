package controller.Commands;

import model.IChessModel;
import view.IChessView;

/**
 * General interface for any kind of chess game keyboard command
 */
public interface KeyCommand {

  /**
   * Execute the action linked to this specific type of key command on the given model and view
   * @param model the view to modify the state of based on the action
   * @param view the view to reflect the change in model state in
   */
  void execute(IChessModel model, IChessView view);
}
