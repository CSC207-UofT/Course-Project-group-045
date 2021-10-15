import java.util.*;
import java.awt.*;
import javax.swing.*;

public class UI extends JPanel implements Runnable{
  private Thread animator;
  private Image Background,Background1,Selected;
  private int x;
  
  public UI() {
    initUI();
  }
  
  private void initUI() {
    ImageIcon background = new ImageIcon("src/Images/Background7.png");
    ImageIcon selected = new ImageIcon("src/Images/Forest1.png");
    ImageIcon blank = new ImageIcon("");
    Background = background.getImage();
    Background1 = blank.getImage();
    Selected = selected.getImage();
    setPreferredSize(new Dimension(1000, 675));
  }
  
  @Override
  public void addNotify() {
    super.addNotify();
    animator = new Thread(this);
    animator.start();
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBackground(g);
  }
  
  private void drawBackground(Graphics g) {
    g.drawImage(Background, 0, 0, this);
    g.drawImage(Background1, 0, 0, this);
    g.drawImage(Selected, 275, 0, this);
    Toolkit.getDefaultToolkit().sync();
  }
  
  private void cycle(){
    if (Game.x == 1) {
      ImageIcon selected = new ImageIcon("src/Images/Forest1 Grid.png");
      ImageIcon background = new ImageIcon("src/Images/Background8.png");
      Selected = selected.getImage();
      Background1 = background.getImage();
    }
  }
  
  @Override
  public void run() {
    long beforeTime, timeDiff, sleep;
    beforeTime = System.currentTimeMillis();
    while (true) {
      cycle();
      repaint();
      try {
        Thread.sleep(33);
      } catch (InterruptedException e) {
      }
    beforeTime = System.currentTimeMillis();
    }
  }
}
