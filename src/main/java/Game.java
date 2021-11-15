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
    static ArrayList<Character> playerChar;
    static Character selectedChar;
    static boolean itemSelect = false;
    static ArrayList<Item> itemList = new ArrayList<>();
    static Map currMap;

    public void mousePressed(MouseEvent e) {
        X = (int) (Math.ceil((e.getX() - 205) / 75));
        Y = (int) (Math.ceil((e.getY() + 45) / 75));
        AllyCheck();
        //currtile is the map character associated with the tile clicked
        Character currTile = currMap.getCharByPos(X, Y);
        //if a player character is already selected
        if (selectedChar != null){
            //if the clicked tile is an enemy, perform the attack then deselect all characters
            if(currMap.getEnemyList().contains(currTile) && Action.attackable(selectedChar, currTile)){
                Action.attack(selectedChar, currTile);
                System.out.println("attack");
                selectedChar = null;
            }
            /*if an empty tile is also selected and is able to be moved into, move the character to the position
             then deselect all characters  */
            if (currTile == null && Action.moveable(selectedChar, X, Y)){
                Action.move(selectedChar, X, Y);
                UnitLocationX.set(playerChar.indexOf(selectedChar), X);
                UnitLocationY.set(playerChar.indexOf(selectedChar), Y);
                selectedChar = null;

            }
            //presumeably a item is selected through the popup menu
            if (selectedChar != null && itemSelect ){
                //itemList.get().use_item(selectedChar);
            }
            if (X == 1 && Y == 9){
                itemSelect = true;
                //presumeably this triggers some popup in the UI listing all available items
            }
        }
        //if no character is currently selected and tile has a player character on it, select the player character
        if (selectedChar == null && playerChar.contains(currTile) && !currTile.isActionUsed()){
            selectedChar = currTile;
        }
        //if the click uses the last playable character action
        if (checkActionsUsed(playerChar)) {
            System.out.println("End of Player Turn");
            //this should ultimately be replaced with some method call that conducts the Enemy AI's turn
            setActions(playerChar);
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
        Map map1 = new Map(7, 9);
        currMap = map1;
        playerChar = new ArrayList<>();
        ArrayList<Character> enemyChar = new ArrayList<>();
        PlayerChar player1 = new PlayerChar("Marth", 75, 40, 2);
        PlayerChar player2 = new PlayerChar("Hector", 75, 60, 3);
        /*PlayerChar player3 = new PlayerChar("Jhin", 100, 60, 2);
        PlayerChar player4 = new PlayerChar("Leona", 150, 20, 2);
        PlayerChar player5 = new PlayerChar("Senna", 100, 20, 2);\
         */
        EnemyChar enemy1 = new EnemyChar("Axe 1", 100, 10, 2);
        EnemyChar enemy2 = new EnemyChar("Sword 2", 100, 10, 2);
        playerChar.add(player1);
        playerChar.add(player2);
        /*playerChar.add(player3);
        playerChar.add(player4);
        playerChar.add(player5);

         */
        currMap.addEnemyToList(enemy1);
        currMap.addEnemyToList(enemy2);
        /*SelectedChars.add(0, 1);
        SelectedChars.add(1, 2);
        SelectedChars.add(2, 3);
        SelectedChars.add(3, 4);
        Chars.add(0, "");
        Chars.add(1, "Marth");
        Chars.add(2, "Hector");
        Chars.add(3, "Irelia");
        Chars.add(4, "Robin");
        Chars.add(5, "Sakura");
        */
        map1.addChar(enemy1, 3,1);
        map1.addChar(enemy2, 4,1);
        map1.addChar(player1, 3,8);
        map1.addChar(player2, 4,8);
        for (int i = 0; i < playerChar.size(); i++){
            SelectedChars.add(i);
            System.out.print(SelectedChars.get(i));
            Chars.add(playerChar.get(i).getName());
            System.out.print(Chars.get(i));
            UnitLocationX.add(currMap.charXPosition(playerChar.get(i)));
            System.out.print(UnitLocationX.get(i));
            UnitLocationY.add(currMap.charYPosition(playerChar.get(i)));
            System.out.println(UnitLocationY.get(i));
        }
        for (int i = 0; i < currMap.getEnemyList().size(); i++){
            EnemyLocationX.add(currMap.charXPosition(currMap.getEnemyList().get(i)));
            EnemyLocationY.add(currMap.charYPosition(currMap.getEnemyList().get(i)));
        }

        Scanner sc = new Scanner(System.in);
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }

    public static boolean checkActionsUsed(ArrayList<Character> list){
        for (Character character : list){
            if (!(character.isActionUsed())){
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
