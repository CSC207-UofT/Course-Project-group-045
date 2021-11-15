import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class UI extends JPanel implements Runnable{
  private int X,Y,y = 0;
  private int EnemyMatch,AllyMatch = 0;
  private final ArrayList <Image> Images = new ArrayList<>();
  private final ArrayList <ImageIcon> ImageIcons = new ArrayList<>();
  private final ArrayList <Integer> BoardX = new ArrayList<>();
  private final ArrayList <Integer> BoardY = new ArrayList<>();
  private int Time = 0;
  
  public UI() {
    initUI();
  }
  
  private void initUI() {
    for (int i = 0 ; i < 25 ; i++) {
      BoardX.add(i,-200);
      BoardY.add(i,-200);
    }
    setPreferredSize(new Dimension(1000, 675));
    GetData();
  }
  
  @Override
  public void addNotify() {
    Thread animator;
    super.addNotify();
    animator = new Thread(this);
    animator.start();
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBackground(g);
  }

  private void GetData() {
    String Location = ("src/Data/ImageIcons.txt");
    String Row;
    ArrayList <String> Data1 = new ArrayList<>();
    try {
      FileReader Read = new FileReader(Location);
      BufferedReader Buffer = new BufferedReader(Read);
      while ((Row = Buffer.readLine()) != null) {
        Data1.add(Row);
      }
      Buffer.close();
    }catch (Exception e) {
      Data1.add(Data1.size(),"0");
    }
    for (int i = 0 ; i < 30 ; i++){
      ImageIcons.add(i, new ImageIcon(Data1.get(i)));
    }
    for (int i = 0 ; i < 43 ; i++){
      Images.add(i, ImageIcons.get(0).getImage());
    }
    Images.set(17,ImageIcons.get(26).getImage());
    Images.set(7,ImageIcons.get(23).getImage());
    Images.set(8,ImageIcons.get(24).getImage());
  }

  private void drawBackground(Graphics g) {
    g.drawImage(Images.get(0), 0, 0, this);
    g.drawImage(Images.get(1), 275, 0, this);
    g.drawImage(Images.get(5), 275, 0, this);
    g.drawImage(Images.get(6),350,130,this);
    for (int i = 0 ; i < 25 ; i++){
      g.drawImage(Images.get(i+17),BoardX.get(i) * 75 + 200,BoardY.get(i) * 75 - 75,this);
    }
    for (int i = 0 ; i < 2 ; i++){
      g.drawImage(Images.get(i+7),Game.currMap.charXPosition(Game.enemyChar.get(i)) * 75 + 85,
              Game.currMap.charYPosition(Game.enemyChar.get(i)) * 75 - 200, this);
    }
    for (int i = 0 ; i < 2 ; i++){
      g.drawImage(Images.get(i+2),Game.currMap.charXPosition(Game.playerChar.get(i)) * 75 + 85,
              Game.currMap.charYPosition(Game.playerChar.get(i)) * 75 - 200, this);
    }
    g.drawImage(Images.get(42),0,0,this);
    Toolkit.getDefaultToolkit().sync();
  }

  private void GetBoard() {
    if (Game.Selected == -1){
      for (int i = 0 ; i < 25 ; i++){
        BoardX.set(i,-1);
        BoardY.set(i,-1);
      }
    }else {
      Images.set(17,ImageIcons.get(26).getImage());
      BoardX.set(0,Game.X);
      BoardY.set(0,Game.Y);
      BoardCheck();
    }
  }

  private void AllyCheck() {
    AllyMatch = 0;
    for (int i = 0; i < Game.playerChar.size(); i++) {
      if (Game.currMap.charXPosition(Game.playerChar.get(i)) == X &&
              Game.currMap.charYPosition(Game.playerChar.get(i)) == Y) {
        AllyMatch = 1;
        break;
      }
    }
  }
  private void EnemyCheck() {
    EnemyMatch = 0;
    for (int i = 0; i < Game.enemyChar.size(); i++) {
      if (Game.currMap.charXPosition(Game.enemyChar.get(i)) == X &&
              Game.currMap.charYPosition(Game.enemyChar.get(i)) == Y) {
        EnemyMatch = 1;
        break;
      }
    }
  }

  private void BoardCheck() {
    String Location1 = ("src/Data/BoardX.txt");
    String Row;
    ArrayList <Integer> Data1 = new ArrayList<>();
    try {
      FileReader Read = new FileReader(Location1);
      BufferedReader Buffer = new BufferedReader(Read);
      while ((Row = Buffer.readLine()) != null) {
        Data1.add(Integer.valueOf(Row));
      }
      Buffer.close();
    }catch (Exception e) {
      Data1.add(Data1.size(),0);
    }
    String Location2 = ("src/Data/BoardY.txt");
    ArrayList <Integer> Data2 = new ArrayList<>();
    try {
      FileReader Read = new FileReader(Location2);
      BufferedReader Buffer = new BufferedReader(Read);
      while ((Row = Buffer.readLine()) != null) {
        Data2.add(Integer.valueOf(Row));
      }
      Buffer.close();
    }catch (Exception e) {
      Data2.add(Data1.size(),0);
    }
    for (int i = 0 ; i < 4 ; i++){
      X = Game.X + Data1.get(i);
      Y = Game.Y + Data2.get(i);
      EnemyCheck();
      if (EnemyMatch == 0){
        X = Game.X + Data1.get(i+4);
        Y = Game.Y + Data2.get(i+4);
        EnemyCheck();
        AllyCheck();
        if (EnemyMatch == 0 && AllyMatch == 0){
          X = Game.X + Data1.get(i+8);
          Y = Game.Y + Data2.get(i+8);
          AllyCheck();
          if (AllyMatch == 0){
            if (X > 0 && Y > 0 && X < 7 && Y < 9) {
              BoardX.set(i + 1, X);
              BoardY.set(i + 1, Y);
              Images.set(i + 18, ImageIcons.get(29).getImage());
            }else {
              Images.set(i + 18, ImageIcons.get(0).getImage());
            }
          }else{
            Images.set(i+18,ImageIcons.get(0).getImage());
          }
        }else{
          Images.set(i+18,ImageIcons.get(0).getImage());
        }
      }else{
        Images.set(i+18,ImageIcons.get(0).getImage());
      }
    }
    for (int i = 0 ; i < 8 ; i++){
      int Check1;
      int Check2;
      X = Game.X + Data1.get(i*2+12);
      Y = Game.Y + Data2.get(i*2+12);
      EnemyCheck();
      Check1 = EnemyMatch;
      X = Game.X + Data1.get(i*2+13);
      Y = Game.Y + Data2.get(i*2+13);
      EnemyCheck();
      if (EnemyMatch == 1 && Check1 == 1) {
        Images.set(i + 22,ImageIcons.get(0).getImage());
      }else{
        X = Game.X + Data1.get(i*2+28);
        Y = Game.Y + Data2.get(i*2+28);
        EnemyCheck();
        AllyCheck();
        Check1 = EnemyMatch;
        Check2 = AllyMatch;
        X = Game.X + Data1.get(i*2+29);
        Y = Game.Y + Data2.get(i*2+29);
        EnemyCheck();
        AllyCheck();
        if (EnemyMatch == 1 | AllyMatch == 1 && Check1 == 1 | Check2 == 1) {
          Images.set(i + 22,ImageIcons.get(0).getImage());
        }else{
          X = Game.X + Data1.get(i+44);
          Y = Game.Y + Data2.get(i+44);
          AllyCheck();
          if (AllyMatch == 0){
            if (X > 0 && Y > 0 && X < 7 && Y < 9) {
              BoardX.set(i + 5, X);
              BoardY.set(i + 5, Y);
              Images.set(i + 22, ImageIcons.get(29).getImage());
            }else {
              Images.set(i + 22, ImageIcons.get(0).getImage());
            }
          }else{
            Images.set(i + 22,ImageIcons.get(0).getImage());
          }
        }
      }
    }
    for (int i = 0 ; i < 4 ; i++){
      X = Game.X + Data1.get(i + 52);
      Y = Game.Y + Data2.get(i + 52);
      EnemyCheck();
      if (EnemyMatch == 0){
        X = Game.X + Data1.get(i+52);
        Y = Game.Y + Data2.get(i+52);
        AllyCheck();
        X = Game.X + Data1.get(i+56);
        Y = Game.Y + Data2.get(i+56);
        EnemyCheck();
        if (EnemyMatch == 1 && AllyMatch == 1) {
          Images.set(i + 30, ImageIcons.get(0).getImage());
        }else{
          X = Game.X + Data1.get(i+56);
          Y = Game.Y + Data2.get(i+56);
          EnemyCheck();
          if (EnemyMatch == 0){
            if (X > 0 && Y > 0 && X < 7 && Y < 9) {
              BoardX.set(i + 13, X);
              BoardY.set(i + 13, Y);
              Images.set(i + 30, ImageIcons.get(27).getImage());
            }else {
              Images.set(i + 30, ImageIcons.get(0).getImage());
            }
          }else{
            BoardX.set(i + 13, X);
            BoardY.set(i + 13, Y);
            Images.set(i + 30, ImageIcons.get(29).getImage());
          }
        }
      }else{
        Images.set(i+30,ImageIcons.get(0).getImage());
      }
    }
    for (int i = 0 ; i < 4 ; i++){
      int Check;
      X = Game.X + Data1.get(i*2 + 60);
      Y = Game.Y + Data2.get(i*2 + 60);
      EnemyCheck();
      Check = EnemyMatch;
      X = Game.X + Data1.get(i*2 + 61);
      Y = Game.Y + Data2.get(i*2 + 61);
      EnemyCheck();
      if (EnemyMatch == 1 && Check == 1) {
        Images.set(i + 34,ImageIcons.get(0).getImage());
      }else{
        X = Game.X + Data1.get(i*2 + 60);
        Y = Game.Y + Data2.get(i*2 + 60);
        AllyCheck();
        Check = AllyMatch;
        X = Game.X + Data1.get(i*2 + 61);
        Y = Game.Y + Data2.get(i*2 + 61);
        AllyCheck();
        if (AllyMatch == 1 && Check == 1) {
          X = Game.X + Data1.get(i + 68);
          Y = Game.Y + Data2.get(i + 68);
          EnemyCheck();
          if (EnemyMatch == 1){
            Images.set(i + 34,ImageIcons.get(0).getImage());
          }else{
            if (X > 0 && Y > 0 && X < 7 && Y < 9) {
              BoardX.set(i + 17, X);
              BoardY.set(i + 17, Y);
              Images.set(i + 34, ImageIcons.get(27).getImage());
            }else {
              Images.set(i + 34, ImageIcons.get(0).getImage());
            }
          }
        }else{
          X = Game.X + Data1.get(i+68);
          Y = Game.Y + Data2.get(i+68);
          EnemyCheck();
          if (EnemyMatch == 0){
            if (X > 0 && Y > 0 && X < 7 && Y < 9) {
              BoardX.set(i + 17, X);
              BoardY.set(i + 17, Y);
              Images.set(i + 34, ImageIcons.get(27).getImage());
            }else {
              Images.set(i + 34, ImageIcons.get(0).getImage());
            }
          }else{
            if (X > 0 && Y > 0 && X < 7 && Y < 9) {
              BoardX.set(i + 17, X);
              BoardY.set(i + 17, Y);
              Images.set(i + 34, ImageIcons.get(29).getImage());
            }else {
              Images.set(i + 34, ImageIcons.get(0).getImage());
            }
          }
        }
      }
    }
    for (int i = 0 ; i < 4 ; i++){
      X = Game.X + Data1.get(i);
      Y = Game.Y + Data2.get(i);
      EnemyCheck();
      if (EnemyMatch == 1){
        if (X > 0 && Y > 0 && X < 7 && Y < 9) {
          BoardX.set(i + 21, X);
          BoardY.set(i + 21, Y);
          Images.set(i + 38, ImageIcons.get(29).getImage());
        }else {
          Images.set(i + 38, ImageIcons.get(0).getImage());
        }
      }else{
        if (X > 0 && Y > 0 && X < 7 && Y < 9) {
          BoardX.set(i + 21, X);
          BoardY.set(i + 21, Y);
          Images.set(i + 38, ImageIcons.get(27).getImage());
        }else {
          Images.set(i + 38, ImageIcons.get(0).getImage());
        }
      }
    }
  }

  private void cycle(){
    if (Game.state == -1) {
      Images.set(0, ImageIcons.get(1).getImage());
      Images.set(1, ImageIcons.get(Game.Map + 1).getImage());
      for (int i = 0; i < 2; i++) {
        ImageIcons.set(i * 3 + 8, new ImageIcon("src/Images/" +
                Game.playerChar.get(i).getName() + "/S" + y + ".png"));
        Images.set(i + 2, ImageIcons.get(i * 3 + 8).getImage());
      }
      Game.Animation = 1;
      y++;
      Time++;
      Game.Selected = 0;
      if (Time == 25) {
        Game.state = 0;
        y = 0;
        Time = 0;
        Game.Animation = 0;
      }
    }else if (Game.state == 0){
      GetBoard();
      if (Game.selectedChar != null){
        ImageIcon side = new ImageIcon("src/Images/" + Game.selectedChar.getName() + "/side.png");
        Images.set(42,side.getImage());
      }else{
        Images.set(42,ImageIcons.get(0).getImage());
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
        cycle();
      }
    }
  }
}
