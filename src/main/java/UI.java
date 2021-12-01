import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class UI extends JPanel implements Runnable{
  private final ArrayList <Integer> player = new ArrayList<>();
  private final ArrayList <Integer> Combat = new ArrayList<>();
  private final ArrayList <Image> Images = new ArrayList<>();
  private final ArrayList <ImageIcon> ImageIcons = new ArrayList<>();
  private final ArrayList <Integer> BoardX = new ArrayList<>();
  private final ArrayList <Integer> BoardY = new ArrayList<>();
  private int Time = 0;

  public UI() {
    initUI();
  }

  private void initUI() {
    for (int i = 0 ; i < 27 ; i++) {
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
    for (int i = 0 ; i < 48 ; i++){
      Images.add(i, ImageIcons.get(0).getImage());
    }
    Images.set(17,ImageIcons.get(26).getImage());
    Images.set(7,ImageIcons.get(23).getImage());
    Images.set(8,ImageIcons.get(24).getImage());
    Combat.add(200);
    Combat.add(500);
    for (int i = 0 ; i < 2 ; i++){
      player.add(Game.currMap.charXPosition(Game.playerChar.get(i)));
      player.add(Game.currMap.charYPosition(Game.playerChar.get(i)));
    }
  }

  private void drawBackground(Graphics g) {
    g.drawImage(Images.get(0), 0, 0, this);
    g.drawImage(Images.get(1), 275, 0, this);
    g.drawImage(Images.get(5), 275, 0, this);
    g.drawImage(Images.get(6),350,130,this);
    for (int i = 0 ; i < 26 ; i++){
      g.drawImage(Images.get(i+17),BoardX.get(i) * 75 + 200,BoardY.get(i) * 75 - 75,this);
    }
    g.drawImage(Images.get(42), BoardX.get(26) * 75 + 200, BoardY.get(26) * 75 - 75, this);
    for (int i = 0 ; i < 2 ; i++){
      g.drawImage(Images.get(i+7),Game.currMap.charXPosition(Game.enemyChar.get(i)) * 75 + 85,
              Game.currMap.charYPosition(Game.enemyChar.get(i)) * 75 - 200, this);
    }
    for (int i = 0 ; i < 2 ; i++){
      g.drawImage(Images.get(i+2),player.get(i * 2) * 75 + 85, player.get(i * 2 + 1) * 75 - 200, this);
    }
    g.drawImage(Images.get(45), Combat.get(0), 200,this);
    g.drawImage(Images.get(46), Combat.get(1), 300,this);
    g.drawImage(Images.get(43),0,0,this);
    g.drawImage(Images.get(47), 0, 0, this);
    Toolkit.getDefaultToolkit().sync();
  }

  private void GetBoard() {
    if (Game.selectedChar == null){
      for (int i = 0 ; i < 27; i++){
        BoardX.set(i,-1);
        BoardY.set(i,-1);
      }
      for (int i = 0 ; i < 2 ; i++){
        player.set(i * 2, Game.currMap.charXPosition(Game.playerChar.get(i)));
        player.set(i * 2 + 1, Game.currMap.charYPosition(Game.playerChar.get(i)));
      }
    }else {
      Images.set(42,ImageIcons.get(26).getImage());
      Images.set(17,ImageIcons.get(27).getImage());
      BoardX.set(0,Game.currMap.charXPosition(Game.selectedChar));
      BoardY.set(0,Game.currMap.charYPosition(Game.selectedChar));
      BoardX.set(26,Game.X);
      BoardY.set(26,Game.Y);
      int x = Game.playerChar.indexOf(Game.selectedChar);
      if (Action.x == 0) {
        player.set(x, Game.X);
        player.set(x + 1, Game.Y);
      }else {
        player.set(x, Action.x);
        player.set(x + 1, Action.y);
      }
      BoardCheck();
    }
  }

  private static ArrayList <Integer> GetData1(String Location) {
    String Row;
    ArrayList <Integer> Data = new ArrayList<>();
    try {
      FileReader Read = new FileReader(Location);
      BufferedReader Buffer = new BufferedReader(Read);
      while ((Row = Buffer.readLine()) != null) {
        Data.add(Integer.valueOf(Row));
      }
      Buffer.close();
    } catch (Exception e) {
      Data.add(Data.size(), 0);
    }
    return Data;
  }

  private void BoardCheck() {
    ArrayList <Integer> Data1 = GetData1("src/Data/X1.txt");
    ArrayList <Integer> Data2 = GetData1("src/Data/X2.txt");
    ArrayList <Integer> Data3 = GetData1("src/Data/Y1.txt");
    ArrayList <Integer> Data4 = GetData1("src/Data/Y2.txt");
    for (int i = 0 ; i < 12 ; i++){
      if (Action.boardAttackable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i))){
        BoardX.set(i + 1, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i));
        BoardY.set(i + 1, Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i));
        Images.set(i + 18, ImageIcons.get(29).getImage());
      }else{
        Images.set(i + 18, ImageIcons.get(0).getImage());
      }
    }
    for (int i = 0 ; i < 12 ; i++){
      if (Action.boardAttackable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data2.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data4.get(i))){
        BoardX.set(i + 13, Game.currMap.charXPosition(Game.selectedChar) + Data2.get(i));
        BoardY.set(i + 13, Game.currMap.charYPosition(Game.selectedChar) + Data4.get(i));
        Images.set(i + 30, ImageIcons.get(29).getImage());
      }else{
        Images.set(i + 30, ImageIcons.get(0).getImage());
      }
    }
    for (int i = 0 ; i < 12 ; i++){
      if (Action.boardMoveable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i))){
        BoardX.set(i + 1, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i));
        BoardY.set(i + 1, Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i));
        Images.set(i + 18, ImageIcons.get(27).getImage());
      }
    }
  }

  private void cycle(){
    if (Game.state == -1) {
      Images.set(0, ImageIcons.get(1).getImage());
      Images.set(1, ImageIcons.get(Game.Map + 1).getImage());
      for (int i = 0; i < 2; i++) {
        ImageIcons.set(i * 3 + 8, new ImageIcon("src/Images/" +
                Game.playerChar.get(i).getName() + "/S" + Time + ".png"));
        Images.set(i + 2, ImageIcons.get(i * 3 + 8).getImage());
        Images.set(i + 7, new ImageIcon("src/Images/" + Game.enemyChar.get(i).getName() + "1.png").getImage());
      }
      Game.Animation = 1;
      Time++;
      if (Time == 25) {
        Game.state = 0;
        Time = 0;
        Game.Animation = 0;
        if (Game.End == 1){
          Game.state = 1;
        }
      }
    }else if (Game.state == 0){
      GetBoard();
      if (Game.selectedChar != null){
        ImageIcon side = new ImageIcon("src/Images/" + Game.selectedChar.getName() + "/side.png");
        Images.set(43,side.getImage());
      }else{
        Images.set(43,ImageIcons.get(0).getImage());
      }
      if (Game.Combat == 1){
        Time++;
        Game.Animation = 1;
        for (int i = 0 ; i < 27 ; i++){
          BoardX.set(i,-1);
          BoardY.set(i,-1);
        }
        for (int i = 0 ; i < 2 ; i++){
          Images.set(i + 2, ImageIcons.get(0).getImage());
          Images.set(i + 7, ImageIcons.get(0).getImage());
        }
        Images.set(45, new ImageIcon("src/Images/" +
                Game.selectedChar.getName() + "/A" + Time + ".png").getImage());
        Images.set(6, new ImageIcon("src/Images/" +
                Game.selectedChar.getName() + "/AS" + Time + ".png").getImage());
        Images.set(46, new ImageIcon("src/Images/" + Game.selectedEnemy.getName() + "2.png").getImage());
        Images.set(1, new ImageIcon("src/Images/Combat.png").getImage());
        int y;
        if (Game.selectedChar.getName() == "Marth") {
          y = 23;
        }else{
          y = 42;
        }
        if (Time == y){
          Game.state = -1;
          Game.Animation = 0;
          Game.Combat = 0;
          Time = 0;
          Images.set(45,ImageIcons.get(0).getImage());
          Images.set(46,ImageIcons.get(0).getImage());
          Images.set(6,ImageIcons.get(0).getImage());
          Images.set(43,ImageIcons.get(0).getImage());
          for (int i = 0 ; i < 2 ; i++){
            player.set(i * 2, Game.currMap.charXPosition(Game.playerChar.get(i)));
            player.set(i * 2 + 1, Game.currMap.charYPosition(Game.playerChar.get(i)));
          }
          Game.selectedChar = null;
        }
      }
    }else if (Game.state == 1){
      Images.set(47, new ImageIcon("src/Images/End.png").getImage());
      Game.Animation = 1;
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
