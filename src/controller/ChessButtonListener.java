package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.IChessModel;
import view.IChessView;

import static model.ChessPlayer.WHITE;

/**
 * Created by Justin on 7/20/2017.
 */
public class ChessButtonListener implements ActionListener {

  private IChessModel model;
  private IChessView view;
  private int horizFrom, vertFrom, horizTo, vertTo = 0;

  @Override
  public void actionPerformed(ActionEvent e) {

    if (model.isStaleMate() || model.isGameOver()) {
      return ;
    }

    String s = e.getActionCommand();
    int horiz = Integer.parseInt(s.substring(0, 1));
    int vert = Integer.parseInt(s.substring(2, 3));
    if (horizFrom == 0 && model.getPieceAt(horiz, vert).getPlayer() == model.currentPlayer()) {
      horizFrom = horiz;
      vertFrom = vert;
    }
    else if (horizFrom != 0 && horizTo == 0) {
      horizTo = horiz;
      vertTo = vert;
    }

    if (horizTo != 0) {

      System.out.println("Move attempted");
      try {
        model.move(horizFrom, vertFrom, horizTo, vertTo);
        model.advancePlayer();
        //view.redraw();

        if (model.currentPlayer() == WHITE) {
          view.setMessage("White's move.");
        }

        else {
          view.setMessage("Black's move.");
        }
      } catch (IllegalArgumentException iae) {
        System.out.println("Bad move");
        view.setMessage("Invalid move. Retry");
        //view.redraw();
      }

      horizFrom = 0;
      vertFrom = 0;
      horizTo = 0;
      vertTo = 0;
    }

    if (model.isGameOver()) {
      view.setMessage("Checkmate!");
    }
    else if (model.isStaleMate()) {
      view.setMessage("Stalemate!");
    }

    view.redraw();

    System.out.println(horizFrom);
    System.out.println(vertFrom);
    System.out.println(horizTo);
    System.out.println(vertTo);

  }

  public void setModel(IChessModel model) {
    this.model = model;
  }

  public void setView(IChessView view) {
    this.view = view;
  }
}
