import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.math.*;

public class Game extends JFrame implements MouseListener{
    static int state = -1;
    static int X = 1;
    static int Y = 1;
    static int Allowed = 0;
    static int Selected = 2;
    static int Animation = 0;

    public void mousePressed(MouseEvent e) {
        if (Animation == 0) {
            X = (int) (Math.ceil((e.getX() - 355) / 75));
            Y = (int) (Math.ceil((e.getY() - 180) / 75));
            if (X == 1 && Y == 1) {
                if (Selected == 1) {
                    Selected = 2;
                    Allowed = 0;
                    state = 0;
                } else {
                    Selected = 0;
                    Allowed = 1;
                }
            } else if (X == 2 && Y == 1) {
                if (Selected == 0) {
                    Selected = 2;
                    Allowed = 0;
                    state = 0;
                } else {
                    Selected = 1;
                    Allowed = 1;
                }
            } else {
                Allowed = 0;
                Selected = 2;
                state = 0;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (Animation == 0) {
            if (Allowed == 1) {
                state++;
            }
        }
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
