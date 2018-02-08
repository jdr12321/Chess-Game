package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.IChessController;
import model.ChessPiece;
import model.IChessModel;

/**
 * Chess board panel section of view. Manages strictly the gameplay board part of the frame, not the
 * message section.
 */
public class ChessBoardPanel extends javax.swing.JPanel {

  private final int tileWidth = 50;
  private final IChessModel model;
  private ActionListener controller;
  private Point selectedSquare = null;

  List<JButton> buttons;

  /**
   * Initializes this panel linked to the given Chess board model
   * @param model the model to link interactions with this view to.
   */
  public ChessBoardPanel(IChessModel model) {
    this.model = model;
    this.setPreferredSize(new Dimension(450, 450));

    this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

    this.buttons = new ArrayList<>();

    for (int i = 8; i > 0; i--) {
      for (int j = 1; j < 9; j++) {
        JButton b = new JButton();
        b.setActionCommand(i + " " + j);
        b.setPreferredSize(new Dimension(50, 50));
        b.setFocusable(false);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(true);
        b.addActionListener(controller);
        this.add(b);
        buttons.add(b);
      }
    }
  }

  @Override
  public void paintComponent(Graphics g) {

    for (int i = 1; i < 9; i++) {
      for (int j = 1; j < 9; j++) {


        ChessPiece p = model.getPieceAt(j, i);

        if (i % 2 == j % 2) {
          g.setColor(new Color(222, 184, 135));
        } else {
          g.setColor(Color.WHITE);
        }

        if (selectedSquare != null && j == selectedSquare.x && i == selectedSquare.y) {
          g.setColor(Color.BLUE);
        }

        else if (selectedSquare != null) {
//
          try {
            model.move(selectedSquare.x, selectedSquare.y, j, i);
            model.undo();
            g.setColor(Color.PINK);
          } catch (IllegalArgumentException e) {
          }

        }

        g.fillRect((i - 1) * tileWidth, (Math.abs(j - 9) - 1)  * tileWidth, tileWidth, tileWidth);
        g.setColor(Color.PINK);

        try {
          g.drawImage(ImageIO.read(new File(p.toString() + ".png")), (i - 1) * tileWidth,
                (Math.abs(j - 9) - 1) * tileWidth, tileWidth, tileWidth, this);
        } catch (IOException e) {
        }


      }
    }

  }

  /**
   * Links a listener for commands to every button on this board
   * @param l the action listener
   */
  public void addButtonListener(ActionListener l) {
    this.controller = l;
    for (JButton b : buttons) {
      b.addActionListener(l);
    }
  }

  /**
   * Deletes the listener currently linked to all buttons on this board
   */
  public void deleteButtonListener() {
    this.controller = null;
    for (JButton b : buttons) {

      ActionListener[] al = b.getActionListeners();

      if (al.length != 0) {
        b.removeActionListener(al[0]);
      }
    }
  }

  /**
   * Sets the square at the given point as the selected square, meaning it will glow a different
   * color
   * @param p the point to designate the current selected square
   */
  public void setSelectedSquare(Point p) {
    selectedSquare = p;
  }

  /**
   * returns the currently selected square
   * @return the private field holding the selected square
   */
  public Point getSelectedSquare() {
    return selectedSquare;
  }
}
