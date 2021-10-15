import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Game extends JFrame implements MouseListener{
    static int state = -1;
    static int X = 1;
    static int Y = 1;
    static int Allowed = 0;
    static int Selected = -1;
    static int ESelected = -1;
    static int Animation = 0;

    public void mousePressed(MouseEvent e) {
        if (Animation == 0) {
            X = (int) (Math.ceil((e.getX() - 355) / 75));
            Y = (int) (Math.ceil((e.getY() - 180) / 75));
            if (X == 1 && Y == 1) {
                if (Selected != -1) {
                    Selected = -1;
                    Allowed = 0;
                    state = 0;
                } else {
                    Selected = 0;
                    Allowed = 1;
                }
            } else if (X == 2 && Y == 1) {
                if (Selected != -1) {
                    Selected = -1;
                    Allowed = 0;
                    state = 0;
                } else {
                    Selected = 1;
                    Allowed = 1;
                }
            }else if (X == 1 && Y == 2) {
                if (Selected != 0) {
                    Selected = -1;
                    ESelected = -1;
                    Allowed = 0;
                    state = 0;
                } else {
                    ESelected = 0;
                    Allowed = 1;
                }
            }else if (X == 2 && Y == 2) {
                if (Selected != 1) {
                    Selected = -1;
                    ESelected = -1;
                    Allowed = 0;
                    state = 0;
                } else {
                    ESelected = 1;
                    Allowed = 1;
                }
            } else {
                Allowed = 0;
                Selected = -1;
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
        Map map1 = new Map();
        ArrayList<Character> PlayerChar = new ArrayList<>();
        ArrayList<Character> EnemyChar = new ArrayList<>();
        PlayerChar player1 = new PlayerChar("Player 1");
        PlayerChar player2 = new PlayerChar("Player 2");
        EnemyChar enemy1 = new EnemyChar("Enemy 1");
        EnemyChar enemy2 = new EnemyChar("Enemy 2");
        PlayerChar.add(player1);
        PlayerChar.add(player2);
        EnemyChar.add(enemy1);
        EnemyChar.add(enemy2);
        map1.addEnemyChar(enemy1, enemy2);
        map1.addPlayerChar(player1, player2);
        Scanner sc = new Scanner(System.in);
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });

        while(!PlayerChar.isEmpty() && !EnemyChar.isEmpty()) {
            for (Character currChar : PlayerChar) {
                System.out.println("player character " + currChar.getName() +
                        " at position " + map1.charPosition(currChar) + ", health " + currChar.getCurrHealth() + "/" + currChar.getMaxHealth());
            }
            for (Character currChar : EnemyChar) {
                System.out.println("enemy character " + currChar.getName() +
                        " at position " + map1.charPosition(currChar) + ", health " + currChar.getCurrHealth() + "/" + currChar.getMaxHealth());
            }
            System.out.println("Player Turn");
            // loop runs while at least one player character has action available
            while (checkActions(PlayerChar)) {
                System.out.println("enter character name to perform attack");
                String inputString = sc.nextLine();
                Character user = getCharacterByName(inputString, PlayerChar);
                // checks if user is null
                if (!(user == null)) {
                    // checks if character's action has been used
                        if (user.isActionUsed()) {
                            System.out.println("Character has already used action");
                        } else {
                            System.out.println("enter name of target");
                            String targetStr = sc.nextLine();
                            Character target = getCharacterByName(targetStr, EnemyChar);
                            // checks if target it null
                            if (!(target == null)) {

                                Action.attack(user, target);
                                if (target.getCurrHealth() <= 0) {
                                    System.out.println(target.getName() + " perished");
                                    EnemyChar.remove(target);
                                    map1.removeChar(target);
                                }
                            } else {
                                System.out.println("Not a valid target");
                            }
                        }
                }
                else{
                    System.out.println("not a valid character name");
                }

            }
            // refreshes all actions
            setActions(PlayerChar);
            System.out.println("Enemy Turn");

        }
    if (EnemyChar.isEmpty()){
        System.out.println("Victory for the Righteous");
    }
    else{
        System.out.println("Democracy dies in Darkness");
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
