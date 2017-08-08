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
 * Created by Justin on 7/17/2017.
 */
public class ChessBoardPanel extends javax.swing.JPanel {

  private final int tileWidth = 50;
  private final IChessModel model;
  private ActionListener controller;
  private Point selectedSquare = null;

  List<JButton> buttons;

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

        else if (selectedSquare != null
                && model.getPieceAt(selectedSquare.x, selectedSquare.y).getMoveStrategy()
                .canMove(selectedSquare.x, selectedSquare.y, j, i)) {
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

//    if (buttons.size() == 0) {
//      for (int i = 1; i < 9; i++) {
//        for (int j = 1; j < 9; j++) {
//          JButton b = new JButton();
//          b.setActionCommand(j + " " + i);
//        //b.setPreferredSize(new Dimension(50, 50));
//        //b.setLocation((i - 1) * tileWidth, (Math.abs(j - 9) - 1) * tileWidth);
//          b.setBounds((i - 1) * tileWidth, (Math.abs(j - 9) - 1) * tileWidth, tileWidth, tileWidth);
//          b.setSize(new Dimension(50, 50));
//          b.setFocusable(false);
//          b.setOpaque(false);
//          b.setContentAreaFilled(false);
//          b.setBorderPainted(true);
//          b.addActionListener(controller);
//          this.add(b);
//          buttons.add(b);
//        }
//      }
//    }
  }

  public void addButtonListener(ActionListener l) {
    this.controller = l;
    for (JButton b : buttons) {
      b.addActionListener(l);
    }
  }

  public void deleteButtonListener() {
    this.controller = null;
    for (JButton b : buttons) {

      ActionListener[] al = b.getActionListeners();

      if (al.length != 0) {
        b.removeActionListener(al[0]);
      }
    }
  }

  public void setSelectedSquare(Point p) {
    selectedSquare = p;
  }

  public Point getSelectedSquare() {
    return selectedSquare;
  }
}
