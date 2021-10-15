import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        Map map1 = new Map();
        ArrayList<Character> PlayerChar = new ArrayList<Character>();
        ArrayList<Character> EnemyChar = new ArrayList<Character>();
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
                // checks if input is a character name
                if (isValidInput(inputString, PlayerChar)) {
                    Character user = getCharacterByName(inputString, PlayerChar);
                    // checks if character's action has been used
                        if (user.isActionUsed()) {
                            System.out.println("Character has already used action");
                        } else {
                            System.out.println("enter name of target");
                            String targetStr = sc.nextLine();
                            // checks if input is an enemy name
                            if (isValidInput(targetStr, EnemyChar)) {
                                Character target = getCharacterByName(targetStr, EnemyChar);
                                Action.attack(user, target);
                                if (target.getCurrHealth() <= 0) {
                                    System.out.println(target.getName() + " perished");
                                    EnemyChar.remove(target);
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
    public static boolean isValidInput(String input, ArrayList<Character> list){
        for (Character character: list){
            if (input.equals(character.getName())) {

                return true;
            }
        }
        return false;
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
