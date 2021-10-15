import java.util.*;
import java.awt.*;
import javax.swing.*;

public class UI extends JPanel implements Runnable{
  private Thread animator;
  private Image Background,Combat,Selected,Slash,Unit1,Unit2;
  private int UnitX,UnitY;
  private int y = 0;
  private ArrayList <Image> Units = new ArrayList<>();
  private ArrayList <Integer> LocationX = new ArrayList<>();
  private ArrayList <Integer> LocationY = new ArrayList<>();
  private int Time = 0;
  
  public UI() {
    initUI();
  }
  
  private void initUI() {
    Units.add(0,Unit1);
    Units.add(1,Unit2);
    LocationX.add(0,235 + Game.X*75);
    LocationY.add(0,25 + Game.Y*75);
    ImageIcon background = new ImageIcon("src/Images/Background.png");
    ImageIcon blank = new ImageIcon("");
    ImageIcon unit1 = new ImageIcon("src/Images/Marth/S1.png");
    ImageIcon slash1 = new ImageIcon("src/Images/Marth/AS0.png");
    Background = background.getImage();
    Combat = blank.getImage();
    Selected = blank.getImage();
    Unit1 = unit1.getImage();
    Unit2 = unit1.getImage();
    Slash = slash1.getImage();
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
    g.drawImage(Combat, 275, 0, this);
    g.drawImage(Selected,425,225,this);
    g.drawImage(Unit1,LocationX.get(0),LocationY.get(0),this);
    g.drawImage(Slash,300,130,this);
    Toolkit.getDefaultToolkit().sync();
  }
  
  private void cycle(){
    if (Game.state == 1) {
      ImageIcon selected = new ImageIcon("src/Images/Base.png");
      Selected = selected.getImage();

    }else if (Game.state == 2) {
      LocationX.set(0,200);
      LocationY.set(0,200);
      ImageIcon unit = new ImageIcon("src/Images/Marth/A" + y + ".png");
      ImageIcon slash = new ImageIcon("src/Images/Marth/AS" + y + ".png");
      ImageIcon combat = new ImageIcon("src/Images/Combat.png");
      ImageIcon blank = new ImageIcon("");
      Unit1 = unit.getImage();
      Slash = slash.getImage();
      Combat = combat.getImage();
      Selected = blank.getImage();
      if (y < 23) {
        y++;
      }
      Time++;
      if (Time == 40) {
        Game.state = 0;
        ImageIcon unit1 = new ImageIcon("src/Images/Marth/S0.png");
        Unit1 = unit1.getImage();
        Slash = blank.getImage();
        Combat = blank.getImage();
        LocationX.set(0,235 + Game.X*75);
        LocationY.set(0,25 + Game.Y*75);
        Time = 0;
        y = 0;
      }
    }
  }
  
  @Override
  public void run() {
    while (true) {
      cycle();
      repaint();
      try {
        Thread.sleep(33);
      } catch (InterruptedException e) {
      }
    }
  }
}
