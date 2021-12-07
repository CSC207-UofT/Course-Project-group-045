import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class UI extends JPanel implements Runnable{
  private final ArrayList <Integer> player = new ArrayList<>();
  private final ArrayList <Image> Images = new ArrayList<>();
  private final ArrayList <ImageIcon> ImageIcons = new ArrayList<>();
  private final ArrayList <Integer> BoardX = new ArrayList<>();
  private final ArrayList <Integer> BoardY = new ArrayList<>();
  private final ArrayList <Integer> enemyXY = new ArrayList<>();
  private int Time, Cycle, enemy, damageX, damageY, mapX, mapY, lose = 0;

  public UI() {
    initUI();
  }

  private void initUI() {
    for (int i = 0 ; i < 28 ; i++) {
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
    for (int i = 0 ; i < 26 ; i++){
      ImageIcons.add(i, new ImageIcon(Data1.get(i)));
    }
    for (int i = 0 ; i < 78 ; i++){
      Images.add(i, ImageIcons.get(0).getImage());
    }
    for (int i = 0 ; i < 8 ; i++){
      player.add(-1);
    }
    for (int i = 0 ; i < 6 ; i++){
      enemyXY.add(85);
      enemyXY.add(-200);
    }
    mapX = -1000;
    mapY = -1000;
  }

  private void drawBackground(Graphics g) {
    g.drawImage(Images.get(0), 0, 0, this);
    g.drawImage(Images.get(1), 275, 0, this);
    g.drawImage(Images.get(53), 275, 0, this);
    for (int i = 0 ; i < 26 ; i++){
      g.drawImage(Images.get(i+17),BoardX.get(i) * 75 + 200,BoardY.get(i) * 75 - 75,this);
    }
    g.drawImage(Images.get(42), BoardX.get(26) * 75 + 200, BoardY.get(26) * 75 - 75, this);
    g.drawImage(Images.get(43), BoardX.get(27) * 75 + 200, BoardY.get(27) * 75 - 75, this);
    for (int i = 0 ; i < Game.enemyChar.size() ; i++){
      g.drawImage(Images.get(i+7),Game.currMap.charXPosition(Game.enemyChar.get(i)) * 75 + enemyXY.get(i * 2),
              Game.currMap.charYPosition(Game.enemyChar.get(i)) * 75 + enemyXY.get(i * 2 + 1), this);
    }
    for (int i = 0 ; i < Game.playerChar.size() ; i++){
      g.drawImage(Images.get(i+2),player.get(i * 2) * 75 + 85, player.get(i * 2 + 1) * 75 - 200, this);
    }
    for (int i = 0 ; i < 4 ; i++){
      g.drawImage(Images.get(i+49), 323 + i * 88, 103, this);
    }
    g.drawImage(Images.get(46), 0, 0,this);
    g.drawImage(Images.get(47), 0, 0,this);
    g.drawImage(Images.get(6),0,0,this);
    g.drawImage(Images.get(44),0,0,this);
    g.drawImage(Images.get(45),725,0,this);
    g.drawImage(Images.get(48), 0, 0, this);
    g.drawImage(Images.get(54), 275, 0, this);
    g.drawImage(Images.get(55), 0, 0, this);
    for (int i = 0 ; i < Game.itemList.size() ; i++){
      g.drawImage(Images.get(56 + i), 350 + i * 75, 450, this);
    }
    g.drawImage(Images.get(60), 54, 70, this);
    g.drawImage(Images.get(61), 68, 70, this);
    g.drawImage(Images.get(62), 96, 70, this);
    g.drawImage(Images.get(63), 172, 410, this);
    g.drawImage(Images.get(64), 195, 410, this);
    g.drawImage(Images.get(65), 16, 121, this);
    g.drawImage(Images.get(66), 0, 0, this);
    g.drawImage(Images.get(67), 779, 70, this);
    g.drawImage(Images.get(68), 793, 70, this);
    g.drawImage(Images.get(69), 821, 70, this);
    g.drawImage(Images.get(70), 741, 121, this);
    g.drawImage(Images.get(71), 897, 410, this);
    g.drawImage(Images.get(72), 920, 410, this);
    g.drawImage(Images.get(73), 725, 0, this);
    g.drawImage(Images.get(74), 610, 330, this);
    g.drawImage(Images.get(75), 654, 330, this);
    g.drawImage(Images.get(76), damageX * 75 + 200, damageY * 75 - 130, this);
    g.drawImage(Images.get(77), mapX, mapY, this);
    Toolkit.getDefaultToolkit().sync();
  }

  private void GetBoard() {
    if (Game.selectedChar == null){
      for (int i = 0 ; i < 27; i++){
        BoardX.set(i,-1);
        BoardY.set(i,-1);
      }
      for (int i = 0 ; i < Game.playerChar.size() ; i++){
        player.set(i * 2, Game.currMap.charXPosition(Game.playerChar.get(i)));
        player.set(i * 2 + 1, Game.currMap.charYPosition(Game.playerChar.get(i)));
      }
      if (Game.selectedEnemy != null) {
        Images.set(43, ImageIcons.get(23).getImage());
        BoardX.set(27, Game.X);
        BoardY.set(27, Game.Y);
      }else {
        BoardX.set(27, -1);
        BoardY.set(27, -1);
      }
    }else {
      Images.set(42,ImageIcons.get(21).getImage());
      Images.set(43, ImageIcons.get(0).getImage());
      Images.set(17,ImageIcons.get(22).getImage());
      BoardX.set(0,Game.currMap.charXPosition(Game.selectedChar));
      BoardY.set(0,Game.currMap.charYPosition(Game.selectedChar));
      BoardX.set(26,Game.X);
      BoardY.set(26,Game.Y);
      BoardX.set(27,-1);
      BoardY.set(27,-1);
      int x = Game.playerChar.indexOf(Game.selectedChar);
      if (Action.x == 0) {
        player.set(x * 2, Game.X);
        player.set(x * 2 + 1, Game.Y);
      }else {
        player.set(x * 2, Action.x);
        player.set(x * 2 + 1, Action.y);
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
      if (Action.boardHealable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i))) {
        BoardX.set(i + 1, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i));
        BoardY.set(i + 1, Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i));
        Images.set(i + 18, ImageIcons.get(25).getImage());
      }else if (Action.boardAttackable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i))){
        BoardX.set(i + 1, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i));
        BoardY.set(i + 1, Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i));
        Images.set(i + 18, ImageIcons.get(24).getImage());
      }else {
        Images.set(i + 18, ImageIcons.get(0).getImage());
      }
    }
    for (int i = 0 ; i < 12 ; i++){
      if (Action.boardHealable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data2.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data4.get(i))) {
        BoardX.set(i + 13, Game.currMap.charXPosition(Game.selectedChar) + Data2.get(i));
        BoardY.set(i + 13, Game.currMap.charYPosition(Game.selectedChar) + Data4.get(i));
        Images.set(i + 30, ImageIcons.get(25).getImage());
      }else if (Action.boardAttackable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data2.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data4.get(i))) {
        BoardX.set(i + 13, Game.currMap.charXPosition(Game.selectedChar) + Data2.get(i));
        BoardY.set(i + 13, Game.currMap.charYPosition(Game.selectedChar) + Data4.get(i));
        Images.set(i + 30, ImageIcons.get(24).getImage());
      }else {
        Images.set(i + 30, ImageIcons.get(0).getImage());
      }
    }
    for (int i = 0 ; i < 12 ; i++){
      if (Action.boardHealable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i))) {
        BoardX.set(i + 1, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i));
        BoardY.set(i + 1, Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i));
        Images.set(i + 18, ImageIcons.get(25).getImage());
      }else if (Action.boardMoveable(Game.selectedChar, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i),
              Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i))){
        BoardX.set(i + 1, Game.currMap.charXPosition(Game.selectedChar) + Data1.get(i));
        BoardY.set(i + 1, Game.currMap.charYPosition(Game.selectedChar) + Data3.get(i));
        Images.set(i + 18, ImageIcons.get(22).getImage());
      }
    }
  }

  private void cycle(){
    if (Game.screen == 1) {
      Images.set(0, ImageIcons.get(1).getImage());
      Images.set(77, ImageIcons.get(0).getImage());
    }else if (Game.screen == 2){
      Images.set(0, ImageIcons.get(2).getImage());
      for (int i = 0 ; i < Game.playerChar.size() ; i++){
        Images.set(i + 49, new ImageIcon("src/Images/"+ Game.playerChar.get(i).getName() +
                "/Icon.png").getImage());
      }
      for (int i = 0 ; i < 4 - Game.playerChar.size() ; i++){
        Images.set(52 - i, ImageIcons.get(0).getImage());
      }
    }else if (Game.screen == 3){
      Images.set(0, ImageIcons.get(3).getImage());
      Images.set(77, new ImageIcon("src/Images/MapSelect.png").getImage());
      if (Game.Map == 1){
        mapX = 309;
        mapY = 204;
      }else if (Game.Map == 2){
        mapX = 534;
        mapY = 204;
      }else if (Game.Map == 3){
        mapX = 309;
        mapY = 385;
      }else if (Game.Map == 4){
        mapX = 534;
        mapY = 385;
      }
    }else if (Game.screen == 4) {
      for (int i = 0 ; i < 4 ; i++){
        Images.set(i + 49, ImageIcons.get(0).getImage());
      }
      for (int i = 0 ; i < Game.playerChar.size() ; i++){
        player.set(i * 2, Game.currMap.charXPosition(Game.playerChar.get(i)));
        player.set(i * 2 + 1, Game.currMap.charYPosition(Game.playerChar.get(i)));
      }
      if (Game.state == -1) {
        GetBoard();
        Images.set(0, ImageIcons.get(4).getImage());
        Images.set(1, ImageIcons.get(Game.Map + 4).getImage());
        for (int i = 0; i < Game.playerChar.size() ; i++) {
          if (!Game.playerChar.get(i).isActionUsed()) {
            Images.set(i + 2, new ImageIcon("src/Images/" +
                    Game.playerChar.get(i).getName() + "/S" + Time + ".png").getImage());
          }else{
            Images.set(i + 2, new ImageIcon("src/Images/" + Game.playerChar.get(i).getName() +
                  "/S0.png").getImage());
          }
        }
        for (int i = 0 ; i < Game.enemyChar.size() ; i++) {
            Images.set(i + 7, new ImageIcon("src/Images/" + Game.enemyChar.get(i).getName() +
                    "1.png").getImage());
        }
        Game.Animation = 1;
        Time++;
        if (Time == 25) {
          Game.state = 0;
          Time = 0;
          Game.Animation = 0;
        }
      } else if (Game.state == 0) {
        GetBoard();
        for (int i = 0; i < Game.playerChar.size() ; i++) {
          if (!Game.playerChar.get(i).isActionUsed()) {
            Images.set(i + 2, new ImageIcon("src/Images/" + Game.playerChar.get(i).getName() +
                    "/S24.png").getImage());
          }else{
            Images.set(i + 2, new ImageIcon("src/Images/" + Game.playerChar.get(i).getName() +
                    "/S0.png").getImage());
          }
        }
        if (Game.selectedChar != null) {
          Images.set(44, new ImageIcon("src/Images/" + Game.selectedChar.getName() + "/side.png").getImage());
          if (Game.selectedChar.getCurrHealth() == 100){
            Images.set(60, new ImageIcon("src/Images/Side/Numbers/HP/100.png").getImage());
            Images.set(61, ImageIcons.get(0).getImage());
            Images.set(62, ImageIcons.get(0).getImage());
          }else {
            Images.set(60, ImageIcons.get(0).getImage());
            if (Game.selectedChar.getCurrHealth() > 9) {
              Images.set(61, new ImageIcon("src/Images/Side/Numbers/HP/" +
                      (int) Math.floor(Game.selectedChar.getCurrHealth() / 10) + ".png").getImage());
            }else {
              Images.set(61, ImageIcons.get(0).getImage());
            }
            Images.set(62, new ImageIcon("src/Images/Side/Numbers/HP/" +
                    (int) (Game.selectedChar.getCurrHealth() - Math.floor(Game.selectedChar.getCurrHealth()/10) * 10)
                    + ".png").getImage());
          }
          Images.set(63, new ImageIcon("src/Images/Side/Numbers/Atk/" +
                  (int) Math.floor(Game.selectedChar.getAttack()/10) + ".png").getImage());
          Images.set(64, new ImageIcon("src/Images/Side/Numbers/Atk/" +
                  (int) (Game.selectedChar.getAttack() - Math.floor(Game.selectedChar.getAttack()/10) * 10)
                  + ".png").getImage());
          Images.set(65, new ImageIcon("src/Images/Side/HPBar/HP" +
                  (int) Math.ceil(Game.selectedChar.getCurrHealth() * 100 /Game.selectedChar.getMaxHealth())
                  + ".png").getImage());
          if (Game.ultimateSelect){
            Images.set(66, new ImageIcon("src/Images/Side/UltimateUse.png").getImage());
          }else {
            Images.set(66, new ImageIcon("src/Images/Side/Ultimate" + Game.selectedChar.get_Meter() +
                    ".png").getImage());
          }
        } else {
          Images.set(44, ImageIcons.get(0).getImage());
          for (int i = 0 ; i < 7 ; i++){
            Images.set(i + 60, ImageIcons.get(0).getImage());
          }
        }
        if (Game.selectedEnemy != null) {
          if (Game.enemyChar.contains(Game.selectedEnemy)) {
            Images.set(43, ImageIcons.get(23).getImage());
            Images.set(45, new ImageIcon("src/Images/" + Game.selectedEnemy.getName() + "Side.png").getImage());
            if (Game.selectedEnemy.getCurrHealth() == 100) {
              Images.set(67, new ImageIcon("src/Images/Side/Numbers/HP/100.png").getImage());
              Images.set(68, ImageIcons.get(0).getImage());
              Images.set(69, ImageIcons.get(0).getImage());
            } else {
              Images.set(67, ImageIcons.get(0).getImage());
              if (Game.selectedEnemy.getCurrHealth() > 9) {
                Images.set(68, new ImageIcon("src/Images/Side/Numbers/HP/" +
                        (int) Math.floor(Game.selectedEnemy.getCurrHealth() / 10) + ".png").getImage());
              } else {
                Images.set(68, ImageIcons.get(0).getImage());
              }
              Images.set(69, new ImageIcon("src/Images/Side/Numbers/HP/" +
                      (int) (Game.selectedEnemy.getCurrHealth() - Math.floor(Game.selectedEnemy.getCurrHealth() / 10)
                              * 10)
                      + ".png").getImage());
            }
            Images.set(70, new ImageIcon("src/Images/Side/HPBar/HP" +
                    (int) Math.ceil(Game.selectedEnemy.getCurrHealth() * 100 / Game.selectedEnemy.getMaxHealth())
                    + ".png").getImage());
          }else {
            Images.set(45, new ImageIcon("src/Images/" + Game.selectedEnemy.getName() +
                    "/side.png").getImage());
            Images.set(67, ImageIcons.get(0).getImage());
            if (Game.selectedEnemy.getCurrHealth() > 9) {
              Images.set(68, new ImageIcon("src/Images/Side/Numbers/HP/" +
                      (int) Math.floor(Game.selectedEnemy.getCurrHealth() / 10) + ".png").getImage());
            }else {
              Images.set(68, ImageIcons.get(0).getImage());
            }
            Images.set(69, new ImageIcon("src/Images/Side/Numbers/HP/" +
                    (int) (Game.selectedEnemy.getCurrHealth() - Math.floor(Game.selectedEnemy.getCurrHealth()/10)
                            * 10) + ".png").getImage());
            Images.set(71, new ImageIcon("src/Images/Side/Numbers/Atk/" +
                    (int) Math.floor(Game.selectedEnemy.getAttack()/10) + ".png").getImage());
            Images.set(72, new ImageIcon("src/Images/Side/Numbers/Atk/" +
                    (int) (Game.selectedEnemy.getAttack() - Math.floor(Game.selectedEnemy.getAttack()/10) * 10)
                    + ".png").getImage());
            Images.set(70, new ImageIcon("src/Images/Side/HPBar/HP" +
                    (int) Math.ceil(Game.selectedEnemy.getCurrHealth() * 100 /Game.selectedEnemy.getMaxHealth())
                    + ".png").getImage());
            Images.set(73, new ImageIcon("src/Images/Side/Ultimate" + Game.selectedEnemy.get_Meter() +
                    ".png").getImage());
          }
        }else {
          Images.set(45, ImageIcons.get(0).getImage());
          for (int i = 0 ; i < 7 ; i++) {
            Images.set(i + 67, ImageIcons.get(0).getImage());
          }
        }
        if (Game.itemSelect){
          Images.set(55, new ImageIcon("src/Images/Item.png").getImage());
          for (int i = 0 ; i < Game.itemList.size() ; i++) {
            Images.set(56 + i, new ImageIcon("src/Images/" + Game.itemList.get(i).get_Name() + ".png").getImage());
          }
        }else {
          Images.set(55, ImageIcons.get(0).getImage());
          for (int i = 0 ; i < Game.itemList.size() ; i++) {
            Images.set(56 + i, ImageIcons.get(0).getImage());
          }
        }
        if (Game.Combat == 1) {
          Time++;
          Game.Animation = 1;
          for (int i = 0; i < 27; i++) {
            BoardX.set(i, -1);
            BoardY.set(i, -1);
          }
          for (int i = 0; i < Game.playerChar.size(); i++) {
            Images.set(i + 2, ImageIcons.get(0).getImage());
          }
          for (int i = 0; i < Game.enemyChar.size(); i++) {
            Images.set(i + 7, ImageIcons.get(0).getImage());
          }
          Images.set(46, new ImageIcon("src/Images/" +
                  Game.selectedChar.getName() + "/A" + Time + ".png").getImage());
          Images.set(6, new ImageIcon("src/Images/" +
                  Game.selectedChar.getName() + "/AS" + Time + ".png").getImage());
          Images.set(47, new ImageIcon("src/Images/" + Game.selectedEnemy.getName() + "2.png").getImage());
          Images.set(53, ImageIcons.get(Game.Map + 8).getImage());
          int y = 0;
          if (Game.selectedChar.getName().equals("Marth")) {
            y = 33;
          }else if (Game.selectedChar.getName().equals("Hector")) {
            y = 52;
          }else if (Game.selectedChar.getName().equals("Takumi")) {
            y = 27;
          }else if (Game.selectedChar.getName().equals("Kagero")) {
            y = 29;
          }else if (Game.selectedChar.getName().equals("Sakura")) {
            y = 34;
          }
          if (Time > (y - 16)){
            int x = 0;
            if (Action.ultimate == 1) {
              if (Game.selectedChar.getName() == "Marth"){
                x = 10;
              }else if (Game.selectedChar.getName() == "Hector"){
                x = 20;
              }else if (Game.selectedChar.getName() == "Takumi"){
                x = (int) (Game.selectedChar.getAttack() * 0.5);
              }else if (Game.selectedChar.getName() == "Kagero"){
                x = 15;
              }
            }
            if (Time < (y - 13)) {
              Images.set(74, new ImageIcon("src/Images/Damage/" +
                      (int) Math.floor((Game.selectedChar.getAttack() + x)/ 10) + (Time * (-1) + y - 13) +
                      ".png").getImage());
              Images.set(75, new ImageIcon("src/Images/Damage/" +
                      (int) ((Game.selectedChar.getAttack() + x) - Math.floor((Game.selectedChar.getAttack() + x) / 10)
                              * 10) + (Time * (-1) + y - 13) + ".png").getImage());
            }else if (Time < (y - 3)) {
              Images.set(74, new ImageIcon("src/Images/Damage/" +
                      (int) Math.floor((Game.selectedChar.getAttack() + x) / 10) + ".png").getImage());
              Images.set(75, new ImageIcon("src/Images/Damage/" +
                      (int) ((Game.selectedChar.getAttack() + x) - Math.floor((Game.selectedChar.getAttack() + x) / 10)
                              * 10) + ".png").getImage());
            }else {
              Images.set(74, new ImageIcon("src/Images/Damage/" +
                      (int) Math.floor((Game.selectedChar.getAttack() + x) / 10) + (y - Time) +
                      ".png").getImage());
              Images.set(75, new ImageIcon("src/Images/Damage/" +
                      (int) ((Game.selectedChar.getAttack() + x) - Math.floor((Game.selectedChar.getAttack() + x) / 10)
                              * 10) + (y - Time) + ".png").getImage());
            }
          }
          if (Time == y) {
            Images.set(75, ImageIcons.get(0).getImage());
            Images.set(76, ImageIcons.get(0).getImage());
            Action.ultimate = 0;
            Game.state = -1;
            Game.Animation = 0;
            Game.Combat = 0;
            Time = 0;
            Images.set(6, ImageIcons.get(0).getImage());
            Images.set(44, ImageIcons.get(0).getImage());
            Images.set(45, ImageIcons.get(0).getImage());
            Images.set(46, ImageIcons.get(0).getImage());
            Images.set(47, ImageIcons.get(0).getImage());
            Images.set(53, ImageIcons.get(0).getImage());
            for (int i = 0 ; i < 7 ; i++){
              Images.set(i + 60, ImageIcons.get(0).getImage());
            }
            for (int i = 0 ; i < 7 ; i++){
              Images.set(i + 67, ImageIcons.get(0).getImage());
            }
            for (int i = 0; i < Game.playerChar.size() ; i++) {
              Images.set(i + 2, new ImageIcon("src/Images/" + Game.playerChar.get(i).getName() +
                        "/S24.png").getImage());
            }
            for (int i = 0 ; i < Game.enemyChar.size() ; i++) {
              Images.set(i + 7, new ImageIcon("src/Images/" + Game.enemyChar.get(i).getName() +
                      "1.png").getImage());
            }
            for (int i = 0; i < Game.playerChar.size() ; i++) {
              player.set(i * 2, Game.currMap.charXPosition(Game.playerChar.get(i)));
              player.set(i * 2 + 1, Game.currMap.charYPosition(Game.playerChar.get(i)));
            }
            Game.selectedChar = null;
            Game.selectedEnemy = null;
            if (Game.checkActions(Game.playerChar) && Game.End != 1) {
              Game.confirmAttack = false;
              Game.confirmMove = false;
              Game.enemyTurn = true;
              Game.state = 1;
            }
            if (Game.End == 1) {
              Game.state = 3;
            }
          }
        }
      } else if (Game.state == 1){
        Game.Animation = 1;
        Time++;
        GetBoard();
        Images.set(0, ImageIcons.get(4).getImage());
        Images.set(1, ImageIcons.get(Game.Map + 4).getImage());
        Images.set(44, ImageIcons.get(0).getImage());
        Images.set(45, ImageIcons.get(0).getImage());
        for (int i = 0 ; i < 7 ; i++){
          Images.set(i + 60, ImageIcons.get(0).getImage());
        }
        for (int i = 0 ; i < 7 ; i++){
          Images.set(i + 67, ImageIcons.get(0).getImage());
        }
        if (Game.enemyTurn){
          Images.set(54, new ImageIcon("src/Images/Animations/EnemyTurn/" + Time + ".png").getImage());
        }else {
          Images.set(54, new ImageIcon("src/Images/Animations/AllyTurn/" + Time + ".png").getImage());
        }
        for (int i = 0; i < Game.playerChar.size() ; i++) {
          Images.set(i + 2, new ImageIcon("src/Images/" + Game.playerChar.get(i).getName() +
                  "/S24.png").getImage());
        }
        for (int i = 0 ; i < Game.enemyChar.size() ; i++) {
          Images.set(i + 7, new ImageIcon("src/Images/" + Game.enemyChar.get(i).getName() +
                  "1.png").getImage());
        }
        if (Time == 20){
          Time = 0;
          Game.Animation = 0;
          Game.state = -1;
          Images.set(54, ImageIcons.get(0).getImage());
          Game.setActions(Game.playerChar);
          Game.state = -1;
          if (Game.enemyTurn){
            Game.state = 2;
          }
        }
      }else if (Game.state == 2){
        if (Time == 0) {
          Action.AI(Game.enemyChar.get(enemy));
        }
        if (Action.ai == 1) {
          Time++;
          if (Action.x1 != 0 | Action.y1 != 0) {
            if (Game.currMap.charXPosition(Game.enemyChar.get(enemy)) == Action.x1) {
              enemyXY.set(enemy * 2 + 1, enemyXY.get(enemy * 2 + 1) +
                      3 * (Action.y1 - Game.currMap.charYPosition(Game.enemyChar.get(enemy))));
            } else if (Game.currMap.charYPosition(Game.enemyChar.get(enemy)) == Action.y1) {
              enemyXY.set(enemy * 2, enemyXY.get(enemy * 2) +
                      3 * (Action.x1 - Game.currMap.charXPosition(Game.enemyChar.get(enemy))));
            } else {
              enemyXY.set(enemy * 2, enemyXY.get(enemy * 2) +
                      3 * (Action.x1 - Game.currMap.charXPosition(Game.enemyChar.get(enemy))));
              enemyXY.set(enemy * 2 + 1, enemyXY.get(enemy * 2 + 1) +
                      3 * (Action.y1 - Game.currMap.charYPosition(Game.enemyChar.get(enemy))));
            }
            if (Time == 25) {
              enemyXY.set(enemy * 2, 85);
              enemyXY.set(enemy * 2 + 1, -200);
              Action.move(Game.enemyChar.get(enemy), Action.x1, Action.y1);
            }
          }
          if (Time > 25){
            damageX = Game.currMap.charXPosition(Game.playerChar.get(Action.index));
            damageY = Game.currMap.charYPosition(Game.playerChar.get(Action.index));
            if (Time < 29) {
              Images.set(76, new ImageIcon("src/Images/Damage/10" + (Time * (-1) + 27) + ".png").getImage());
            }else if (Time < 48){
              Images.set(76, new ImageIcon("src/Images/Damage/100.png").getImage());
            }else if (Time < 51){
              Images.set(76, new ImageIcon("src/Images/Damage/10" + (Time - 48) + ".png").getImage());
            }
          }
          if (Time == 50){
            Images.set(76, ImageIcons.get(0).getImage());
            enemy++;
            Time = 0;
            if (Game.playerChar.get(Action.index).getCurrHealth() <= 0){
              Game.currMap.removeChar(Game.playerChar.get(Action.index));
              Game.playerChar.remove(Game.playerChar.get(Action.index));
            }
            if (!Game.currMap.contains(Game.playerChar)) {
              Game.state = 3;
              lose = 1;
            }
          }
        }else if (Action.ai == 2) {
          Time++;
          if (Game.currMap.charXPosition(Game.enemyChar.get(enemy)) == Action.x1){
            enemyXY.set(enemy * 2 + 1, enemyXY.get(enemy * 2 + 1) +
                    3 * (Action.y1 - Game.currMap.charYPosition(Game.enemyChar.get(enemy))));
          }else if (Game.currMap.charYPosition(Game.enemyChar.get(enemy)) == Action.y1){
            enemyXY.set(enemy * 2, enemyXY.get(enemy * 2) +
                    3 * (Action.x1 - Game.currMap.charXPosition(Game.enemyChar.get(enemy))));
          }else {
            enemyXY.set(enemy * 2, enemyXY.get(enemy * 2) +
                    3 * (Action.x1 - Game.currMap.charXPosition(Game.enemyChar.get(enemy))));
            enemyXY.set(enemy * 2 + 1, enemyXY.get(enemy * 2 + 1) +
                    3 * (Action.y1 - Game.currMap.charYPosition(Game.enemyChar.get(enemy))));
          }
          if (Time == 25) {
            enemyXY.set(enemy * 2, 85);
            enemyXY.set(enemy * 2 + 1, -200);
            Action.move(Game.enemyChar.get(enemy), Action.x1, Action.y1);
            Time = 0;
            enemy++;
          }
        }
        if (enemy == Game.enemyChar.size()) {
          Game.enemyTurn = false;
          Game.state = 1;
          enemy = 0;
        }
      }else if (Game.state == 3) {
        for (int i = 0; i < Game.playerChar.size() ; i++) {
          Images.set(i + 2, new ImageIcon("src/Images/" + Game.playerChar.get(i).getName() +
                    "/S1.png").getImage());
        }
        if (lose == 0) {
          Images.set(48, new ImageIcon("src/Images/End.png").getImage());
        }else {
          Images.set(48, new ImageIcon("src/Images/Lose.png").getImage());
        }
        Game.Animation = 1;
        Time++;
        if (Time == 30) {
          Time = 0;
          Game.End = 0;
          Game.state = -1;
          Game.screen = 1;
          Images.set(1, ImageIcons.get(0).getImage());
          Images.set(48, ImageIcons.get(0).getImage());
          for (int i = 0; i < Game.playerChar.size(); i++) {
            Images.set(i + 2, ImageIcons.get(0).getImage());
            Game.currMap.removeChar(Game.playerChar.get(i));
          }
          for (int i = 0; i < 4; i++) {
            Images.set(i + 2, ImageIcons.get(0).getImage());
          }
          for (int i = 0; i < Game.obstacleChar.size(); i++) {
            Game.currMap.removeChar(Game.obstacleChar.get(i));
          }
          for (int i = 0; i < Game.enemyChar.size(); i++) {
            Images.set(i + 7, ImageIcons.get(0).getImage());
            Game.currMap.removeChar(Game.enemyChar.get(i));
          }
          for (int i = 0 ; i < 4 ; i++) {
            Images.set(56 + i, ImageIcons.get(0).getImage());
          }
          Game.playerChar.clear();
          Game.obstacleChar.clear();
          Game.enemyChar.clear();
          Game.itemList.clear();
          Game.listChar.clear();
          Game.Animation = 0;
          Game.listChar.add(new Marth());
          Game.listChar.add(new Hector());
          Game.listChar.add(new Kagero());
          Game.listChar.add(new Takumi());
          Game.listChar.add(new Sakura());
          Game.enemyChar.add(new EnemyChar("Axe"));
          Game.enemyChar.add(new EnemyChar("Sword"));
          Game.enemyChar.add(new EnemyChar("Axe"));
          Game.enemyChar.add(new EnemyChar("Sword"));
          Game.enemyChar.add(new EnemyChar("Axe"));
          Game.enemyChar.add(new EnemyChar("Sword"));
        }
      }
    }
  }

  @Override
  public void run() {
    while (true) {
      Cycle++;
      if (Cycle == 2) {
        cycle();
        repaint();
        Cycle = 0;
      }
      try {
        Thread.sleep(16);
      } catch (InterruptedException e) {
        cycle();
      }
    }
  }
}
