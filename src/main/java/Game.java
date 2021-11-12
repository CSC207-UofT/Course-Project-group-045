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

    public void mousePressed(MouseEvent e) {
        X = (int) (Math.ceil((e.getX() - 205) / 75));
        Y = (int) (Math.ceil((e.getY() + 45) / 75));
        AllyCheck();
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

    public static void main(String[] args){
        ArrayList<Character> map = new ArrayList<Character>();
        ArrayList<Character> PlayerChars = new ArrayList<Character>();
        ArrayList<Character> EnemyChars = new ArrayList<Character>();
        PlayerChar player1 = new PlayerChar("Player");
        EnemyChar enemy1 = new EnemyChar("Enemy");
        map.add(player1);
        map.add(enemy1);
        Scanner sc = new Scanner(System.in);
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
        SelectedChars.add(0,1);
        SelectedChars.add(1,2);
        SelectedChars.add(2,3);
        SelectedChars.add(3,4);
        Chars.add(0, "");
        Chars.add(1, "Marth");
        Chars.add(2, "Hector");
        Chars.add(3, "Irelia");
        Chars.add(4, "Robin");
        Chars.add(5, "Sakura");


        while(map.contains(player1) && map.contains(enemy1)) {
            System.out.println("player character at position " + map.indexOf(player1) + ", health " + player1.getCurrHealth() + "/" + player1.getMaxHealth());
            System.out.println("enemy character at position " + map.indexOf(enemy1) + " health " + enemy1.getCurrHealth() + "/" + enemy1.getMaxHealth());
            System.out.println("enter position character to perform action");
            int inputInt = sc.nextInt();
            System.out.println("type action to be performed");
            String inputStr = sc.nextLine();
            Character user = map.get(inputInt);
            System.out.println("enter position of target");
            int targetInt = sc.nextInt();
            Character target = map.get(targetInt);
            user.attack(user, target);
            

        }


    }
}
