package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.IChessController;
import model.ChessBoard;
import model.IChessModel;

/**
 * Created by Justin on 7/11/2017.
 */
public class ChessBoardFrame extends JFrame implements IChessView  {

  IChessModel model;
  IChessController controller;
  ChessBoardPanel chessBoard;
  ChessMessagePanel messages;

  public ChessBoardFrame(IChessModel model) {
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(400, 550));
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
  public void setButtonListener(ActionListener l) {
    chessBoard.addButtonListener(l);
  }

  @Override
  public void setKeyListener(KeyListener l) {
    this.addKeyListener(l);
  }
}
