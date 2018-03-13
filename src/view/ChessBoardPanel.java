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

  private final Color tanTile = new Color(222, 184, 135);
  private final int tileWidth = 50;
  private final IChessModel model;
  private ActionListener controller;
  private Point selectedSquare = null;
  private List<JButton> buttons;

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

    for (int y = 1; y < 9; y++) {
      for (int x = 1; x < 9; x++) {

        ChessPiece p = model.getPieceAt(x, y);

        if (y % 2 == x % 2) {
          g.setColor(tanTile);
        } else {
          g.setColor(Color.WHITE);
        }

        // paint any selected square blue
        if (selectedSquare != null && x == selectedSquare.x && y == selectedSquare.y) {
          g.setColor(Color.BLUE);
        }

        // if a piece is selected, paint all pieces it can move to pink
        else if (selectedSquare != null) {
          try {
            model.move(selectedSquare.x, selectedSquare.y, x, y);
            model.undo();
            g.setColor(Color.PINK);
          } catch (IllegalArgumentException e) {
            // do not change the color from the default color, the piece is not
            // selected and cannot be moven to from the selected
          }

        }

        g.fillRect((y - 1) * tileWidth, (Math.abs(x - 9) - 1)  * tileWidth, tileWidth, tileWidth);

        try {
          g.drawImage(ImageIO.read(new File(p.toString() + ".png")), (y - 1) * tileWidth,
                (Math.abs(x - 9) - 1) * tileWidth, tileWidth, tileWidth, this);
        } catch (IOException e) {
          // just dont draw the piece
        }
      }
    }
  }

  /**
   * Links a listener for commands to every button on this board
   * @param l the action listener
   */
  void addButtonListener(ActionListener l) {
    this.controller = l;
    for (JButton b : buttons) {
      b.addActionListener(l);
    }
  }

  /**
   * Deletes the listener currently linked to all buttons on this board
   */
  void deleteButtonListener() {
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
  void setSelectedSquare(Point p) {
    selectedSquare = p;
  }

}
