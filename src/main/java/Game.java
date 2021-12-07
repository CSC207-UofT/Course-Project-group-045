import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Game extends JFrame implements MouseListener{
    static int state = 1;
    static int screen = 1;
    static int X = -1;
    static int Y = -1;
    static int X1 = -1;
    static int Y1 = -1;
    static int Combat = 0;
    static int Animation = 0;
    static int Map = 1;
    static int End = 0;
    static ArrayList <Character> playerChar = new ArrayList<>();
    static ArrayList <Character> listChar = new ArrayList<>();
    static ArrayList <Character> enemyChar = new ArrayList<>();
    static ArrayList <Character> obstacleChar = new ArrayList<>();
    static ArrayList <consumable_Item> itemList = new ArrayList<>();
    static Character selectedChar;
    static Character selectedEnemy;
    static boolean enemyTurn = false;
    static boolean itemSelect = false;
    static boolean ultimateSelect = false;
    static boolean confirmAttack = false;
    static boolean confirmMove = false;
    static Map currMap;

    public void mousePressed(MouseEvent e) {
        if (screen == 1) {
            X = e.getX();
            Y = e.getY();
            if (320 <= X && 250 <= Y && 700 >= X && 325 >= Y){
                if (playerChar.size() != 0) {
                    screen = 4;
                    generateMap(Map);
                    itemList.add(new HpPot());
                    itemList.add(new HpPot());
                    itemList.add(new AtkPot());
                    itemList.add(new AtkPot());
                }
            }else if (320 <= X && 335 <= Y && 700 >= X && 410 >= Y){
                screen = 2;
            }else if (320 <= X && 420 <= Y && 700 >= X && 495 >= Y){
                screen = 3;
            }
        }else if (screen == 2) {
            X = e.getX();
            Y = e.getY();
            if (290 <= X && 245 <= Y && 725 >= X && 330 >= Y){
                int choice = (int )(Math.ceil((X - 290) / 87));
                if (!playerChar.contains(listChar.get(choice)) && playerChar.size() <= 3){
                    playerChar.add(listChar.get(choice));
                }
            }else if (290 <= X && 605 <= Y && 500 >= X && 675 >= Y) {
                if (playerChar.size() != 0){
                    playerChar.remove(playerChar.size() - 1);
                }else {
                    screen = 1;
                }
            }else if (515 <= X && 605 <= Y && 725 >= X && 675 >= Y) {
                screen = 1;
            }
        }else if (screen == 3) {
            X = e.getX();
            Y = e.getY();
            if (290 <= X && 605 <= Y && 500 >= X && 675 >= Y) {
                screen = 1;
            }else if (515 <= X && 605 <= Y && 725 >= X && 675 >= Y) {
                screen = 1;
            }else if (335 <= X && 250 <= Y && 455 >= X && 410 >= Y) {
                Map = 1;
            }else if (560 <= X && 250 <= Y && 680 >= X && 410 >= Y) {
                Map = 2;
            }else if (335 <= X && 430 <= Y && 455 >= X && 590 >= Y) {
                Map = 3;
            }else if (560 <= X && 430 <= Y && 680 >= X && 590 >= Y) {
                Map = 4;
            }
        }else if (screen == 4) {
            if (Animation == 0) {
                X = (int) (Math.ceil((e.getX() - 205) / 75));
                Y = (int) (Math.ceil((e.getY() + 45) / 75));
                if (X == 9 && Y == 9){
                    state = 3;
                }
                if ((X == 2 && Y == 9) | (X < 1 && (Y == 8 | Y == 9))){
                }else if (0 > X | X > 6 | 0 > Y | Y > 8) {
                    selectedChar = null;
                    selectedEnemy = null;
                    itemSelect = false;
                    ultimateSelect = false;
                }
                combatAction();
            }
        }
    }


    public void mouseReleased(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }

    public Game() {
        initUI();
    }

    private ArrayList <Integer> getData(String Location) {
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

    private void generateMap(int Map) {
        ArrayList <Integer> Data = getData("src/Data/Map" + Map + ".txt");
        for (int i = 0 ; i < (Data.size() / 2) ; i++){
            obstacleChar.add(new EnemyChar("Obstacle" + i * 2));
            currMap.addChar(obstacleChar.get(i), Data.get(i * 2), Data.get(i * 2 + 1));
        }
        for (int i = 0; i < playerChar.size(); i++) {
            if (Map != 3) {
                currMap.addChar(playerChar.get(i), i + 2, 8);
            }else {
                int x = i + 1;
                if (x == 3 | x == 4){
                    x += 2;
                }
                currMap.addChar(playerChar.get(i), x, 8);
            }
        }
        for (int i = 0 ; i < 6 ; i++){
            if (Map != 4){
                currMap.addChar(enemyChar.get(i), i + 1, 1);
            }else {
                currMap.addChar(enemyChar.get(i), i + 1, 2);
            }
        }
    }

    private void combatAction() {
        if (!currMap.contains(enemyChar)) {
            End = 1;
        }
        //currtile is the map character associated with the tile clicked
        Character currTile = currMap.getCharByPos(X, Y);
        if (X == 1 && Y == 9) {
            selectedEnemy = null;
                        confirmAttack = false;
            confirmMove = false;
            itemSelect = false;
            for (int i = 0 ; i < playerChar.size() ; i++){
                playerChar.get(i).useAction();
                Game.state = 1;
                enemyTurn = true;
            }
        }else if (itemSelect) {
            if (X > 1 && 6 > X && Y == 7){
                if (itemList.size() != 0) {
                    Action.useItem(selectedChar, itemList.get(X - 2));
                    itemList.remove(X - 2);
                    itemSelect = false;
                }
            }else {
                itemSelect = false;
                selectedChar = null;
            }
            X = currMap.charXPosition(selectedChar);
            Y = currMap.charYPosition(selectedChar);
        }else if (confirmAttack){
            if (X == X1 && Y == Y1) {
                Action.attack(selectedChar, currTile);
                confirmAttack = false;
                confirmMove = false;
                selectedChar.useAction();
                if (currTile.getCurrHealth() <= 0) {
                    currMap.removeChar(currTile);
                }
                if (enemyChar.contains(currTile)) {
                    Combat = 1;
                }else {
                    selectedChar = null;
                    selectedEnemy = null;
                    if (checkActions(playerChar)) {
                        enemyTurn = true;
                        Game.state = 1;
                    }
                }
                X = -1;
                Y = -1;
            }else {
                confirmAttack = false;
                ultimateSelect = false;
                confirmMove = false;
                selectedChar = null;
                selectedEnemy = null;
                Action.x = 0;
                Action.y = 0;
            }
        }else if (confirmMove){
            if (X == X1 && Y == Y1){
                Action.move(selectedChar, X, Y);
                selectedChar.useAction();
                selectedChar = null;
                if (checkActions(playerChar)) {
                    enemyTurn = true;
                    Game.state = 1;
                }
            }
            confirmAttack = false;
            confirmMove = false;
            selectedChar = null;
        }else if (obstacleChar.contains(currTile)) {
            selectedChar = null;
            selectedEnemy = null;
            ultimateSelect = false;
        }else if (selectedChar != null) {
            //if the clicked tile is an enemy, perform the attack then deselect all characters
            if (X == 2 && Y == 9){
                itemSelect = true;
                X = currMap.charXPosition(selectedChar);
                Y = currMap.charYPosition(selectedChar);
                confirmMove = false;
                confirmAttack = false;
                ultimateSelect = false;
            }else if (X < 1 && (Y == 8 | Y == 9)){
                if (selectedChar.get_Meter() == 0) {
                    ultimateSelect = true;
                }
                X = currMap.charXPosition(selectedChar);
                Y = currMap.charYPosition(selectedChar);
            }else if (enemyChar.contains(currTile) && Action.attackable(selectedChar, currTile)) {
                if (!confirmAttack) {
                    selectedEnemy = currTile;
                    confirmAttack = true;
                    confirmMove = false;
                    X1 = X;
                    Y1 = Y;
                }
                /*if an empty tile is also selected and is able to be moved into, move the character to the position
                 then deselect all characters  */
            } else if (currTile == null && Action.moveable(selectedChar, X, Y)) {
                if (!confirmMove) {
                    confirmMove = true;
                    confirmAttack = false;
                    ultimateSelect = false;
                    X1 = X;
                    Y1 = Y;
                }
            }else if (selectedChar == listChar.get(4) && ultimateSelect && X == currMap.charXPosition(selectedChar) &&
                    Y == currMap.charYPosition(selectedChar)){
                confirmAttack = false;
                confirmMove = false;
                selectedChar.useAction();
                Action.attack(selectedChar, selectedChar);
                ultimateSelect = false;
                selectedChar = null;
            }else if (selectedChar == listChar.get(4) && currTile.getCurrHealth() != currTile.getMaxHealth() &&
                Action.attackable(selectedChar, currTile) && playerChar.contains(currTile)){
                if (!confirmAttack) {
                    selectedEnemy = currTile;
                    confirmAttack = true;
                    confirmMove = false;
                    X1 = X;
                    Y1 = Y;
                }
            }else if (!Action.moveable(selectedChar, X, Y)) {
                confirmMove = false;
                confirmAttack = false;
                ultimateSelect = false;
                selectedChar = null;
            }
            if (!confirmAttack && playerChar.contains(currTile)) {
                if (!currTile.isActionUsed()) {
                    confirmAttack = false;
                    confirmMove = false;
                    selectedEnemy = null;
                    selectedChar = currTile;
                }
            }
        }else{
            if (enemyChar.contains(currTile)) {
                selectedEnemy = currTile;
            }else {
                selectedEnemy = null;
            }
            if (playerChar.contains(currTile)) {
                if (!currTile.isActionUsed()) {
                    confirmAttack = false;
                    confirmMove = false;
                    selectedEnemy = null;
                    selectedChar = currTile;
                }
            }
        }
        if (!currMap.contains(enemyChar)) {
            End = 1;
        }
    }

    private void initUI() {
        add(new UI());
        setResizable(false);
        pack();
        setTitle("Course Project");
        setLocation(150,20);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addMouseListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Map map1 = new Map(6, 8);
        currMap = map1;
        listChar.add(new Marth());
        listChar.add(new Hector());
        listChar.add(new Kagero());
        listChar.add(new Takumi());
        listChar.add(new Sakura());
        enemyChar.add(new EnemyChar("Axe"));
        enemyChar.add(new EnemyChar("Sword"));
        enemyChar.add(new EnemyChar("Axe"));
        enemyChar.add(new EnemyChar("Sword"));
        enemyChar.add(new EnemyChar("Axe"));
        enemyChar.add(new EnemyChar("Sword"));
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }

    public static boolean checkActions(ArrayList <Character> list){
        for (Character character : list){
            if (!character.isActionUsed()){
                return false;
            }
        }
        return true;
    }

    public static void setActions(ArrayList<Character> list){
        for (Character character: list){
            character.restoreAction();
        }
    }


    public static Character getCharacterByName(String name, ArrayList<Character> list){
        for (Character character: list){
            if (name.equals(character.getName())){
                return character;
            }
        }
        return null;
    }
}
