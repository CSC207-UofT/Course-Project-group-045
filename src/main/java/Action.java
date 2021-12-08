import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.math.*;

public class Action {

    static ArrayList <Integer> Ranges = new ArrayList<>();
    static int x, y, x1, y1, ai, index, ultimate;

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
            ultimate = 1;
        }else {
            ultimate = 0;
            if (Game.enemyChar.contains(target)) {
                target.reduceCurrHealth(attacker.getAttack());
                attacker.decrease_Meter();
                attacker.useAction();
            }else {
                target.increaseCurrHealth(attacker.getAttack());
                attacker.decrease_Meter();
                attacker.useAction();
            }
        }
    }

    public static void attackAI(Character attacker, Character target) {
        if (x != 0 && y != 0){
            x1 = x;
            y1 = y;
            x = 0;
            y = 0;
        }
        target.decrease_Meter();
        target.reduceCurrHealth(attacker.getAttack());
        attacker.useAction();
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
        distances(selected);
        ArrayList<Character> possibleTar = new ArrayList<>();
        for (int i = 0 ; i < Game.playerChar.size() ; i++){
            if (attackable(selected, Game.playerChar.get(i))){
                possibleTar.add(Game.playerChar.get(i));
            }
        }
        //check for scenario 2
        if (possibleTar.size() == 1){
            ai = 1;
            attackable(selected, possibleTar.get(0));
            Action.attackAI(selected, possibleTar.get(0));
            index = Game.playerChar.indexOf(possibleTar.get(0));
        }
        //check for scenario 3
        else if (possibleTar.size() > 0){
            Character lowestHealth = possibleTar.get(0);
            for (int i = 0 ; i < possibleTar.size() ; i++){
                if (possibleTar.get(i).getCurrHealth() < lowestHealth.getCurrHealth()){
                    lowestHealth = possibleTar.get(i);
                }
            }
            ai = 1;
            attackable(selected, lowestHealth);
            Action.attackAI(selected, lowestHealth);
            index = Game.playerChar.indexOf(lowestHealth);
        }
        //scenario 1
        else{
            distances(selected);
            int closest = closest();
            int range = Math.abs(Game.currMap.charXPosition(selected) -
                    Game.currMap.charXPosition(Game.playerChar.get(closest))) +
                    Math.abs(Game.currMap.charYPosition(selected)
                            - Game.currMap.charYPosition(Game.playerChar.get(closest)));
            ArrayList <Integer> Data1 = GetData("src/Data/X1.txt");
            ArrayList <Integer> Data2 = GetData("src/Data/Y1.txt");
            ArrayList <Integer> Spaces = new ArrayList<>();
            for (int i = 0 ; i < 12 ; i++){
                if (moveableAI(selected, Game.currMap.charXPosition(selected) + Data1.get(i),
                        Game.currMap.charYPosition(selected) + Data2.get(i))){
                    Spaces.add(range - (Math.abs(Game.currMap.charXPosition(selected) + Data1.get(i) -
                            Game.currMap.charXPosition(Game.playerChar.get(closest))) +
                            Math.abs(Game.currMap.charYPosition(selected) + Data2.get(i) -
                                    Game.currMap.charYPosition(Game.playerChar.get(closest)))));
                }else {
                    Spaces.add(-1000);
                }
            }
            int index = Spaces.indexOf(Collections.max(Spaces));
            if (Collections.max(Spaces) != -1000) {
                x1 = Game.currMap.charXPosition(selected) + Data1.get(index);
                y1 = Game.currMap.charYPosition(selected) + Data2.get(index);
            }else {
                x1 = Game.currMap.charXPosition(selected);
                y1 = Game.currMap.charYPosition(selected);
            }
            ai = 2;
        }
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

    public static int closest(){
        int index = 0;
        int value = 1000;
        for (int i = 0 ; i < Game.playerChar.size() ; i++){
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
                return rangeCheck(selected, x, y);
            }else{
                return true;
            }
        }
        return false;
    }

    public static boolean rangeCheck(Character selected, int x , int y){
        if (Game.currMap.charXPosition(selected) == x | Game.currMap.charYPosition(selected) == y) {
            int x1 = Game.currMap.charXPosition(selected);
            int y1 = Game.currMap.charYPosition(selected);
            int dx = x - Game.currMap.charXPosition(selected);
            int dy = y - Game.currMap.charYPosition(selected);
            return Game.currMap.getObsclOrEnemy(x1 + dx / 2, y1 + dy / 2) == null;
        }else {
            return Game.currMap.getObsclOrEnemy(Game.currMap.charXPosition(selected), y) == null |
                    Game.currMap.getObsclOrEnemy(x, Game.currMap.charYPosition(selected)) == null;
        }
    }

    public static boolean moveableAI(Character selected, int x , int y){
        if (Game.currMap.getCharByPos(x, y) == null && x <= Game.currMap.col && y <= Game.currMap.row
                && x > 0 && y > 0 &&
                selected.getSpeed() >= Math.abs(Game.currMap.charXPosition(selected) - x) +
                        Math.abs(Game.currMap.charYPosition(selected) - y)) {
            if (Math.abs(Game.currMap.charXPosition(selected) - x) +
                    Math.abs(Game.currMap.charYPosition(selected) - y) == 2){
                return rangeCheckAI(selected, x, y);
            }else{
                return true;
            }
        }
        return false;
    }

    public static boolean rangeCheckAI(Character selected, int x , int y){
        if (Game.currMap.charXPosition(selected) == x | Game.currMap.charYPosition(selected) == y) {
            int x1 = Game.currMap.charXPosition(selected);
            int y1 = Game.currMap.charYPosition(selected);
            int dx = x - Game.currMap.charXPosition(selected);
            int dy = y - Game.currMap.charYPosition(selected);
            return Game.currMap.getObsclOrAlly(x1 + dx / 2, y1 + dy / 2) == null;
        }else {
            return Game.currMap.getObsclOrAlly(Game.currMap.charXPosition(selected), y) == null |
                    Game.currMap.getObsclOrAlly(x, Game.currMap.charYPosition(selected)) == null;
        }
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
                if (rangeCheck(selected, x, y)){
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
