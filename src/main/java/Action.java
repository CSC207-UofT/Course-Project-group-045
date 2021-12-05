import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.math.*;

public class Action {

    static ArrayList <Integer> Ranges = new ArrayList<>();
    static int x, y;

    public static void attack(Character attacker, Character target) {
        if (x != 0 && y != 0){
            move(attacker, x, y);
            x = 0;
            y = 0;
        }
        if (Game.ultimateSelect) {
            attacker.ultimate(target);
            Game.ultimateSelect = false;
            attacker.useAction();
        }else {
            if (Game.enemyChar.contains(target)) {
                target.reduceCurrHealth(attacker.getAttack());
                attacker.decrease_Meter();
                attacker.reduceCurrHealth(10);
                System.out.println(attacker.getName() + " attacked " + target.getName() + " for " + attacker.getAttack() +
                        " damage!");
                attacker.useAction();
            }else {
                target.increaseCurrHealth(attacker.getAttack());
                attacker.decrease_Meter();
                System.out.println("Sakura healed " + target.getName() + " for " + attacker.getAttack() +
                        " health!");
                attacker.useAction();
            }
        }
    }

    public static boolean attackable(Character attacker, Character target){
        if (target == null){
            x = 0;
            y = 0;
            return false;
        }
        if (Math.abs(Game.currMap.charXPosition(attacker) -
                Game.currMap.charXPosition(target)) + Math.abs(Game.currMap.charYPosition(attacker)
                - Game.currMap.charYPosition(target)) <= attacker.get_Range()){
            x = Game.currMap.charXPosition(attacker);
            y = Game.currMap.charYPosition(attacker);
            return true;
        }
        for (int i = 0 ; i < 2 ; i++){
            if (moveable(attacker, Game.currMap.charXPosition(target) + i*2 - 1, Game.currMap.charYPosition(target))){
                x = Game.currMap.charXPosition(target) + i*2 - 1;
                y = Game.currMap.charYPosition(target);
                return true;
            }
        }
        for (int i = 0 ; i < 2 ; i++){
            if (moveable(attacker, Game.currMap.charXPosition(target), Game.currMap.charYPosition(target) + i*2 - 1)){
                x = Game.currMap.charXPosition(target);
                y = Game.currMap.charYPosition(target) + i*2 - 1;
                return true;
            }
        }
        x = 0;
        y = 0;
        return false;
    }

    public static void AI(Character selected){
        int closest = closest(selected);
        int range = Math.abs(Game.currMap.charXPosition(selected) -
                Game.currMap.charXPosition(Game.playerChar.get(closest))) + 
                Math.abs(Game.currMap.charYPosition(selected)
                - Game.currMap.charYPosition(Game.playerChar.get(closest)));
        ArrayList <Integer> Data1 = GetData("src/Data/X1.txt");
        ArrayList <Integer> Data2 = GetData("src/Data/Y1.txt");
        for (int i = 0 ; i < 12 ; i++){
            if (moveable(selected, Game.currMap.charXPosition(selected) + Data1.get(i), 
                    Game.currMap.charYPosition(selected) + Data2.get(i))){
                if (Math.abs(Game.currMap.charXPosition(selected) + Data1.get(i) -
                        Game.currMap.charXPosition(Game.playerChar.get(closest))) +
                        Math.abs(Game.currMap.charYPosition(selected) + Data2.get(i)
                                - Game.currMap.charYPosition(Game.playerChar.get(closest))) == range - 2){
                    move(selected, Game.currMap.charXPosition(selected) + Data1.get(i), 
                            Game.currMap.charYPosition(selected) + Data2.get(i));
                    break;
                }
            }
        }
    }

    public static boolean inRange(Character selected){
        for (int i = 0 ; i < Ranges.size() ; i++){
            if (Ranges.get(i) < (selected.getSpeed() + selected.get_Range())){
                return true;
            }
        }
        return false;
    }

    public static int check(Character selected){
        int x = 0;
        for (int i = 0 ; i < Game.playerChar.size() ; i++){
            if (attackable(selected, Game.playerChar.get(i))){
                x++;
            }
        }
        return x;
    }

    private static ArrayList <Integer> GetData(String Location) {
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

    public static void distances(Character selected){
        Ranges.clear();
        for (int i = 0 ; i < Game.playerChar.size() ; i++){
            Ranges.add(Math.abs(Game.currMap.charXPosition(selected) -
                    Game.currMap.charXPosition(Game.playerChar.get(i))) + Math.abs(Game.currMap.charYPosition(selected)
                    - Game.currMap.charYPosition(Game.playerChar.get(i))));
        }
    }

    public static int closest(Character selected){
        int index = 0;
        int value = 1000;
        for (int i = 0 ; i < 4 ; i++){
            if (Ranges.get(i) < value){
                index = i;
                value = Ranges.get(i);
            }
        }
        return index;
    }

    public static boolean moveable(Character selected, int x , int y){
        if (Game.currMap.getCharByPos(x, y) == null && x <= Game.currMap.col && y <= Game.currMap.row
                && x > 0 && y > 0 &&
                selected.getSpeed() >= Math.abs(Game.currMap.charXPosition(selected) - x) +
                        Math.abs(Game.currMap.charYPosition(selected) - y)) {
            if (Math.abs(Game.currMap.charXPosition(selected) - x) +
                    Math.abs(Game.currMap.charYPosition(selected) - y) == 2){
                if (range2Check(selected, x, y)){
                    return true;
                }
            }else{
                return true;
            }
        }
        return false;
    }

    public static boolean range2Check(Character selected, int x , int y){
        if (Game.currMap.charXPosition(selected) == x | Game.currMap.charYPosition(selected) == y) {
            int x1 = Game.currMap.charXPosition(selected);
            int y1 = Game.currMap.charYPosition(selected);
            int dx = x - Game.currMap.charXPosition(selected);
            int dy = y - Game.currMap.charYPosition(selected);
            if (Game.currMap.getObsclOrEnemy(x1 + dx/2, y1 + dy/2) == null){
                return true;
            }
        }else {
            if (Game.currMap.getObsclOrEnemy(Game.currMap.charXPosition(selected), y) == null |
                    Game.currMap.getObsclOrEnemy(x, Game.currMap.charYPosition(selected)) == null){
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean boardAttackable(Character attacker, int x, int y){
        if ((Game.enemyChar.contains(Game.currMap.getCharByPos(x, y)) | Game.currMap.getCharByPos(x, y) == null)
                && x <= Game.currMap.col && y <= Game.currMap.row && x > 0 && y > 0){
            if (Math.abs(Game.currMap.charXPosition(attacker) - x) + Math.abs(Game.currMap.charYPosition(attacker)
                    - y) <= attacker.get_Range()){
                return true;
            }
            for (int i = 0 ; i < 2 ; i++){
                if (moveable(attacker, x + i*2 - 1, y)){
                    return true;
                }
            }
            for (int i = 0 ; i < 2 ; i++){
                if (moveable(attacker, x, y + i*2 - 1)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean boardHealable(Character attacker, int x, int y){
        if (attacker == Game.listChar.get(4) && Game.playerChar.contains(Game.currMap.getCharByPos(x, y)) &&
                x <= Game.currMap.col && y <= Game.currMap.row && x > 0 && y > 0){
            if (Math.abs(Game.currMap.charXPosition(attacker) - x) + Math.abs(Game.currMap.charYPosition(attacker)
                    - y) <= attacker.get_Range() && Game.currMap.getCharByPos(x, y).getCurrHealth() !=
                    Game.currMap.getCharByPos(x, y).getMaxHealth()){
                return true;
            }
            for (int i = 0 ; i < 2 ; i++){
                if (moveable(attacker, x + i*2 - 1, y) && Game.currMap.getCharByPos(x, y).getCurrHealth() !=
                        Game.currMap.getCharByPos(x, y).getMaxHealth()){
                    return true;
                }
            }
            for (int i = 0 ; i < 2 ; i++){
                if (moveable(attacker, x, y + i*2 - 1) && Game.currMap.getCharByPos(x, y).getCurrHealth() !=
                        Game.currMap.getCharByPos(x, y).getMaxHealth()){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean boardMoveable(Character selected, int x , int y){
        if ((Game.currMap.getCharByPos(x, y) == null | Game.playerChar.contains(Game.currMap.getCharByPos(x, y))) &&
                x <= Game.currMap.col && y <= Game.currMap.row && x > 0 && y > 0 &&
                selected.getSpeed() >= Math.abs(Game.currMap.charXPosition(selected) - x) +
                        Math.abs(Game.currMap.charYPosition(selected) - y)) {
            if (Math.abs(Game.currMap.charXPosition(selected) - x) +
                    Math.abs(Game.currMap.charYPosition(selected) - y) == 2){
                if (range2Check(selected, x, y)){
                    return true;
                }
            }else{
                return true;
            }
        }
        return false;
    }

    public static void move(Character selected, int x, int y) {
        Game.currMap.removeChar(selected);
        Game.currMap.addChar(selected, x, y);
        selected.useAction();
    }

    public static void useItem(Character selected, consumable_Item item) {
        if (item instanceof HpPot) {
            selected.increaseCurrHealth(item.check_usage());
            if (selected.getCurrHealth() > selected.getMaxHealth()){
                selected.reduceCurrHealth(selected.getCurrHealth() - selected.getMaxHealth());
            }
        }
        if (item instanceof AtkPot) {
            selected.increaseCurrAttack(item.check_usage());
        }
    }
}
