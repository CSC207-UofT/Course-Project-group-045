import java.util.*;
import java.awt.*;
import javax.swing.*;

public class UI extends JPanel implements Runnable{
  private Thread animator;
  private Image Background,Combat,Selected,Slash,Unit1,Unit2,Foe1,Foe2;
  private int y = 0;
  private ArrayList <Integer> LocationX = new ArrayList<>();
  private ArrayList <Integer> LocationY = new ArrayList<>();
  private int Time = 0;
  
  public UI() {
    initUI();
  }
  
  private void initUI() {
    LocationX.add(0,310);
    LocationY.add(0,100);
    LocationX.add(1,385);
    LocationY.add(1,100);
    LocationX.add(2,310);
    LocationY.add(2,175);
    LocationX.add(3,385);
    LocationY.add(3,175);
    ImageIcon background = new ImageIcon("src/Images/Background.png");
    ImageIcon blank = new ImageIcon("");
    ImageIcon unit1 = new ImageIcon("src/Images/Marth/S1.png");
    ImageIcon unit2 = new ImageIcon("src/Images/Hector/S1.png");
    ImageIcon foe1 = new ImageIcon("src/Images/Axe1/S1.png");
    ImageIcon foe2 = new ImageIcon("src/Images/Sword1/S1.png");
    ImageIcon slash1 = new ImageIcon("src/Images/Marth/AS0.png");
    Background = background.getImage();
    Combat = blank.getImage();
    Selected = blank.getImage();
    Unit1 = unit1.getImage();
    Unit2 = unit2.getImage();
    Foe1 = foe1.getImage();
    Foe2 = foe2.getImage();
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
    g.drawImage(Selected,350 + Game.X*75,225,this);
    g.drawImage(Unit1,LocationX.get(0),LocationY.get(0),this);
    g.drawImage(Unit2,LocationX.get(1),LocationY.get(1),this);
    g.drawImage(Foe1,LocationX.get(2),LocationY.get(2),this);
    g.drawImage(Foe2,LocationX.get(3),LocationY.get(3),this);
    g.drawImage(Slash,350,130,this);
    Toolkit.getDefaultToolkit().sync();
  }
  
  private void cycle(){
    if (Game.state == -1) {
      ImageIcon unit1 = new ImageIcon("src/Images/Marth/S" + y + ".png");
      ImageIcon unit2 = new ImageIcon("src/Images/Hector/S" + y + ".png");
      ImageIcon foe1 = new ImageIcon("src/Images/Axe1.png");
      ImageIcon foe2 = new ImageIcon("src/Images/Sword1.png");
      Foe1 = foe1.getImage();
      Foe2 = foe2.getImage();
      Unit1 = unit1.getImage();
      Unit2 = unit2.getImage();
      Game.Animation = 1;
      y++;
      Time++;
      if (Time == 25){
        Game.state = 0;
        y = 0;
        Time = 0;
        Game.Animation = 0;
      }
    }else if (Game.state == 0) {
      ImageIcon blank = new ImageIcon("");
      Selected = blank.getImage();
    }else if (Game.state == 1) {
      ImageIcon selected = new ImageIcon("src/Images/Base.png");
      Selected = selected.getImage();
    }else if (Game.state == 2) {
      int End = 0;
      Game.Animation = 1;
      LocationX.set(0,200);
      LocationY.set(0,200);
      String Character = "Marth";
      if (Game.Selected == 0){
        Character = "Marth";
        End = 40;
      }else if (Game.Selected == 1){
        Character = "Hector";
        End = 60;
      }
      ImageIcon unit = new ImageIcon("src/Images/"+ Character +"/A" + y + ".png");
      ImageIcon slash = new ImageIcon("src/Images/"+ Character +"/AS" + y + ".png");
      ImageIcon combat = new ImageIcon("src/Images/Combat.png");
      ImageIcon blank = new ImageIcon("");
      Unit1 = unit.getImage();
      Slash = slash.getImage();
      Unit2 = blank.getImage();
      Combat = combat.getImage();
      if (Game.ESelected == 0){
        ImageIcon foe = new ImageIcon("src/Images/Axe2.png");
        Foe1 = foe.getImage();
        Foe2 = blank.getImage();
        LocationX.set(2,500);
        LocationY.set(2,300);
      }else if (Game.ESelected == 1){
        ImageIcon foe = new ImageIcon("src/Images/Sword2.png");
        Foe2 = foe.getImage();
        Foe1 = blank.getImage();
        LocationX.set(3,500);
        LocationY.set(3,300);
      }
      Selected = blank.getImage();
      if (Game.Selected == 0){
        if (y < 23) {
          y++;
        }
      }else if (Game.Selected == 1){
        if (y < 42) {
          y++;
        }

      }
      Time++;
      if (Time == End) {
        Game.state = -1;
        if (Game.Selected == 0) {
          ImageIcon unit3 = new ImageIcon("src/Images/Marth/S0.png");
          ImageIcon unit4 = new ImageIcon("src/Images/Hector/S1.png");
          Unit1 = unit3.getImage();
          Unit2 = unit4.getImage();
          LocationX.set(0,310);
          LocationY.set(0,100);
          LocationX.set(1,385);
          LocationY.set(1,100);
        }else if (Game.Selected == 1){
          ImageIcon unit3 = new ImageIcon("src/Images/Marth/S1.png");
          ImageIcon unit4 = new ImageIcon("src/Images/Hector/S0.png");
          Unit1 = unit3.getImage();
          Unit2 = unit4.getImage();
          LocationX.set(0,310);
          LocationY.set(0,100);
          LocationX.set(1,385);
          LocationY.set(1,100);
        }
        if (Game.ESelected == 0){
          LocationX.set(2,310);
          LocationY.set(2,175);
        }else if (Game.ESelected == 1){
          LocationX.set(3,385);
          LocationY.set(3,175);
        }
        ImageIcon foe1 = new ImageIcon("src/Images/Axe1.png");
        ImageIcon foe2 = new ImageIcon("src/Images/Sword1.png");
        Foe1 = foe1.getImage();
        Foe2 = foe2.getImage();
        Slash = blank.getImage();
        Combat = blank.getImage();
        Time = 0;
        y = 0;
        Game.Animation = 0;
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
