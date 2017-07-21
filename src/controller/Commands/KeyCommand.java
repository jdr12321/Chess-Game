package controller.Commands;

import model.IChessModel;
import view.IChessView;

/**
 * Created by Justin on 7/20/2017.
 */
public interface KeyCommand {

  void execute(IChessModel model, IChessView view);
}
