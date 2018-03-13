package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.ChessPiece;

/**
 * Part of the bigger picture JFrame that handles the display of messages to the game players
 */
public class ChessMessagePanel extends javax.swing.JPanel {

  private String message = "White's move.";

  /**
   * Default constructor of the panel.
   */
  public ChessMessagePanel() {
    this.setPreferredSize(new Dimension(450, 100));
  }

  /**
   * Sets the current message to be displayed
   * @param message the message
   */
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
