package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.IChessController;
import model.ChessBoard;
import model.IChessModel;

/**
 * JFrame chess view implementation for the chess board to be rendered within,
 * containing operations for altering the state of various pieces of the view
 */
public class ChessBoardFrame extends JFrame implements IChessView  {

  IChessModel model;
  IChessController controller;
  ChessBoardPanel chessBoard;
  ChessMessagePanel messages;

  /**
   * Builds a frame linked to the given model
   * @param model the chess board for this frame to refer to
   */
  public ChessBoardFrame(IChessModel model) {
    this.setTitle("Chess Game");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(450, 550));
    this.setResizable(false);
    this.chessBoard = new ChessBoardPanel(model);
    this.add(chessBoard);
    this.messages = new ChessMessagePanel();
    this.add(messages);
    this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
  }

  @Override
  public void setModel(IChessModel model) {
    this.model = model;
  }

  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public void setMessage(String message) {
    this.messages.setMessage(message);
  }

  @Override
  public void redraw() {
    this.repaint();
    chessBoard.repaint();
    messages.repaint();
  }

  @Override
  public void setSelectedSquare(Point p) {
    this.chessBoard.setSelectedSquare(p);
  }

  @Override
  public void setButtonListener(ActionListener l) {
    chessBoard.addButtonListener(l);
  }

  @Override
  public void setKeyListener(KeyListener l) {
    this.addKeyListener(l);
  }

  @Override
  public void deleteButtonListener() {
    chessBoard.deleteButtonListener();
  }

  @Override
  public void deleteKeyListener() {

    KeyListener[] list = this.getKeyListeners();

    if (list.length != 0) {
      this.removeKeyListener(list[0]);
    }

  }
}
