package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.ChessPiece;

/**
 * Created by Justin on 7/20/2017.
 */
public class ChessMessagePanel extends javax.swing.JPanel {

  String message = "White's move.";

  public ChessMessagePanel() {
    this.setPreferredSize(new Dimension(450, 100));
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public void paintComponent(Graphics g) {
    g.setFont(new Font("Bold", 1, 24));
    g.setColor(Color.BLACK);
    g.drawString(message, 20, 40);
  }
}
