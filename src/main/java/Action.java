import java.util.*;

public class Action {

    static ArrayList <Integer> Ranges = new ArrayList<>();
    static int x, y;

    public static void attack(Character attacker, Character target) {
        if (x != 0 && y != 0){
            move(attacker, x, y);
            x = 0;
            y = 0;
        }
        target.reduceCurrHealth(attacker.getAttack());
        System.out.println(attacker.getName() + " attacked " + target.getName() + " for " + attacker.getAttack() + " damage!");
        System.out.println(target.getName() + "'s health is now " + target.getCurrHealth());
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
                - Game.currMap.charYPosition(target)) <= attacker.getRange()){
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

    public static void AI(Character actor){
        Game.state = 3;
        distances(Game.currMap.charXPosition(actor), Game.currMap.charYPosition(actor));
        ArrayList<Character> possibleTar = new ArrayList<>();
        for (Character playerTar : Game.playerChar){
            if (attackable(actor, playerTar)){
                possibleTar.add(playerTar);
            }
        }
        if (possibleTar.size() == 1){
            Action.attack(actor, possibleTar.get(0));
        }
        else if (possibleTar.size() > 0){
            Character lowestHealth = possibleTar.get(0);
            for (Character playerTar : possibleTar){
                if (playerTar.getCurrHealth() < lowestHealth.getCurrHealth()){
                    lowestHealth = playerTar;
                }
                Action.attack(actor, lowestHealth);
            }

        }
        else{
            int possibleX = -1;
            int possibleY = -1;
            int closestYet = 100;
            for (int i = 1; i < Game.currMap.col + 1; i++){
                for (int j = 1; j < Game.currMap.row + 1; j++) {
                    if (moveable(actor, i, j)) {
                        distances(i, j);
                        if (Ranges.get(closest()) < closestYet) {
                            possibleX = i;
                            possibleY = j;
                            closestYet = Ranges.get(closest());
                        }
                    }
                }
            }
            if (!(possibleX == -1)) {
                Action.move(actor, possibleX, possibleY);
            }
        }
    }
    public static boolean inRange(Character selected){
        for (int i = 0 ; i < Game.playerChar.size() ; i++){
            if (Ranges.get(i) < (selected.getSpeed() + selected.getRange())){
                return true;
            }
        }
        return false;
    }

    public static void distances(int x, int y){
        Ranges.clear();
        for (int i = 0 ; i < Game.playerChar.size() ; i++){
            Ranges.add(Math.abs(x - Game.currMap.charXPosition(Game.playerChar.get(i)))
                    + Math.abs(y - Game.currMap.charYPosition(Game.playerChar.get(i))));
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
                    - y) <= attacker.getRange()){
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

    public static void performAI(Character actor){
        ArrayList<Character> possibleTar = new ArrayList<>();
        for (Character player : Game.playerChar) {
            if (Action.attackable(player, actor)) {
                possibleTar.add(player);
            }
        }

    }
}
