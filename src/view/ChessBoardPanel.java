package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

  public ChessBoardPanel(IChessModel model) {
    this.model = model;
    this.setPreferredSize(new Dimension(400, 450));




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

        g.fillRect((i - 1) * tileWidth, (Math.abs(j - 9) - 1)  * tileWidth, tileWidth, tileWidth);
        g.setColor(Color.PINK);
//        g.drawString(p.toString(), (i - 1) * tileWidth + tileWidth / 2,
//                (Math.abs(j - 9) - 1) * tileWidth + tileWidth / 2);
        try {
          g.drawImage(ImageIO.read(new File(p.toString() + ".png")), (i - 1) * tileWidth,
                (Math.abs(j - 9) - 1) * tileWidth, tileWidth, tileWidth, this);
        } catch (IOException e) {
        }


      }
    }

    for (int i = 1; i < 9; i++) {
      for (int j = 1; j < 9; j++) {
        JButton b = new JButton();
        b.setActionCommand(j + " " + i);
//        b.setPreferredSize(new Dimension(50, 50));
//        b.setLocation(0, 0);
        b.setBounds((i - 1) * tileWidth, (Math.abs(j - 9) - 1) * tileWidth, tileWidth, tileWidth);
        b.setFocusable(false);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.addActionListener(controller);
        this.add(b);
      }
    }

    /*JButton b = new JButton();
    b.setActionCommand("a");
    b.setLocation(0, 0);
    b.setBounds(0, 0, 50, 50);
    b.addActionListener(this);
    this.add(b);*/
  }

  public void addButtonListener(ActionListener l) {
    this.controller = l;
  }
}
