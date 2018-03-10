package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.ChessPieceType;
import model.IChessModel;
import view.IChessView;

import static model.ChessPlayer.WHITE;

/**
 * Action listener implementation for chess buttons. Handles all the actions when a button is pressed
 * on a chess view
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
    if (model.getPieceAt(horiz, vert).getPlayer() == model.currentPlayer()) {
      horizFrom = horiz;
      vertFrom = vert;

      view.setSelectedSquare(new Point(horiz, vert));
    }
    else if (horizFrom != 0 && horizTo == 0) {
      horizTo = horiz;
      vertTo = vert;
    }

    if (horizTo != 0) {

      try {
        model.move(horizFrom, vertFrom, horizTo, vertTo);
        model.advancePlayer();

        view.setMessage(model.currentPlayer().s + "'s move.");

        if ((horizTo == 1 || horizTo == 8) && model.getPieceAt(horizTo, vertTo).getType() == ChessPieceType.PAWN) {

          view.deleteKeyListener();
          view.deleteButtonListener();
          ChessPromotingController c = new ChessPromotingController();
          c.setModel(model);
          c.setView(view);
          view.setMessage("Promotion!");
        }

        if (model.isInCheck(model.currentPlayer())) {
          view.setMessage("Check!");
        }

      } catch (IllegalArgumentException iae) {
        view.setMessage(iae.getMessage() + " Retry.");
      }

      horizFrom = 0;
      vertFrom = 0;
      horizTo = 0;
      vertTo = 0;
      view.setSelectedSquare(null);
    }

    if (model.isGameOver()) {
      view.setMessage("Checkmate!");
    }
    else if (model.isStaleMate()) {
      view.setMessage("Stalemate!");
    }

    view.redraw();

  }

  public void setModel(IChessModel model) {
    this.model = model;
  }

  public void setView(IChessView view) {
    this.view = view;
  }
}
