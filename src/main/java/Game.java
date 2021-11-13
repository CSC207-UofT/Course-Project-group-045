import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Game extends JFrame implements MouseListener{
    static int state = -1;
    static int X = -1;
    static int Y = -1;
    static int Allowed = 0;
    static int Selected = -1;
    static int ESelected = -1;
    static int Animation = 0;
    static int Map = 1;
    static ArrayList <String> Chars = new ArrayList<>();
    static ArrayList <Integer> SelectedChars = new ArrayList<>();
    static ArrayList <Integer> UnitLocationX = new ArrayList<>();
    static ArrayList <Integer> UnitLocationY = new ArrayList<>();
    static ArrayList <Integer> EnemyLocationX = new ArrayList<>();
    static ArrayList <Integer> EnemyLocationY = new ArrayList<>();
    static Character selectedChar;
    static Character selectedTar;
    static int selectedMoveX, selectedMoveY = -1;
    static Map currMap;

    public void mousePressed(MouseEvent e) {
        X = (int) (Math.ceil((e.getX() - 205) / 75));
        Y = (int) (Math.ceil((e.getY() + 45) / 75));
        AllyCheck();
        Character currTile = currMap.getCharByPos(X, Y);
        if (selectedChar != null && currMap.getEnemyList().contains(currTile) &&
                Action.attackable(selectedChar, currTile)){
            selectedTar = currTile;
        }
        if (selectedChar != null && currTile == null && Action.moveable(selectedChar, X, Y)){
            selectedMoveX = X;
            selectedMoveY = Y;
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

    private void AllyCheck() {
        Selected = -1;
        if (Game.UnitLocationX.contains(X)) {
            for (int i = 0; i < Game.UnitLocationX.size(); i++) {
                if (Game.UnitLocationX.get(i) == X && Game.UnitLocationY.get(i) == Y) {
                    Selected = i + 1;
                    selectedChar = currMap.getCharByPos(UnitLocationX.get(i), UnitLocationY.get(i));
                    break;
                }
            }
        }
    }

    public Game() {
        initUI();
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
        ArrayList<Character> playerChar = new ArrayList<>();
        ArrayList<Character> enemyChar = new ArrayList<>();
        PlayerChar player1 = new PlayerChar("Irelia", 75, 40, 2);
        PlayerChar player2 = new PlayerChar("Zed", 75, 60, 3);
        PlayerChar player3 = new PlayerChar("Jhin", 100, 60, 2);
        PlayerChar player4 = new PlayerChar("Leona", 150, 20, 2);
        PlayerChar player5 = new PlayerChar("Senna", 100, 20, 2);
        EnemyChar enemy1 = new EnemyChar("Enemy 1", 100, 10, 2);
        EnemyChar enemy2 = new EnemyChar("Enemy 2", 100, 10, 2);
        playerChar.add(player1);
        playerChar.add(player2);
        playerChar.add(player3);
        playerChar.add(player4);
        playerChar.add(player5);
        enemyChar.add(enemy1);
        enemyChar.add(enemy2);
        SelectedChars.add(0, 1);
        SelectedChars.add(1, 2);
        SelectedChars.add(2, 3);
        SelectedChars.add(3, 4);
        Chars.add(0, "");
        Chars.add(1, "Marth");
        Chars.add(2, "Hector");
        Chars.add(3, "Irelia");
        Chars.add(4, "Robin");
        Chars.add(5, "Sakura");
        map1.addEnemyChar(enemy1, 0,0);
        map1.addPlayerChar(player1, 5,7);
        Scanner sc = new Scanner(System.in);
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
        //while win/lose not fulfilled
        while (!playerChar.isEmpty() && !enemyChar.isEmpty()) {
            System.out.println("Player Turn"); //indicate player turn
            //while at least one player character still has an action
            while(checkActions(playerChar)){
                while (selectedChar != null){
                    while (selectedTar != null){
                        Action.attack(selectedChar, selectedTar);
                        selectedChar = null;
                        selectedTar = null;
                    }
                    while (selectedMoveX != -1 && selectedMoveY != -1){
                        Action.move();
                        selectedMoveY = -1;
                        selectedMoveX = -1;
                    }
                }
            }

        }
    }

    public static boolean checkActions(ArrayList<Character> list){
        for (Character character : list){
            if (!(character.isActionUsed())){
                return true;
            }

        }
        return false;
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
