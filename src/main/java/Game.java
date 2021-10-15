import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        ArrayList<Character> map = new ArrayList<Character>();
        PlayerChar player1 = new PlayerChar("Player");
        EnemyChar enemy1 = new EnemyChar("Enemy");
        map.add(player1);
        map.add(enemy1);
        Scanner sc = new Scanner(System.in);

        while(map.contains(player1) || map.contains(enemy1)) {
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
