import java.util.*;
import java.awt.*;
import javax.swing.*;
public class UI extends JPanel implements Runnable{
  private Thread animator;
  private Image Background;
  
  public Board() {
    initBoard();
  }
  
  private void initBoard() {
    ImageIcon background = new ImageIcon("C:/BlueJ/Medieval Battles/Backgrounds/Background1.png");
    Background = background.getImage();
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
    Toolkit.getDefaultToolkit().sync();
  }
  
  private void cycle{
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
