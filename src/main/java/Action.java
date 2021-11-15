public class Action {

    public static void attack(Character attacker, Character target) {
        target.reduceCurrHealth(attacker.getAttack());
        System.out.println(attacker.getName() + " attacked " + target.getName() + " for " + attacker.getAttack() + " damage!");
        System.out.println(target.getName() + "'s health is now " + target.getCurrHealth());
        attacker.useAction();
    }

    public static boolean attackable(Character attacker, Character target){
        if (target == null){
            return false;
        }
        if (Game.currMap.charXPosition(target) == Game.currMap.charXPosition(attacker) + 1 ||
                Game.currMap.charXPosition(target) == Game.currMap.charXPosition(attacker) - 1){
            return true;
        }
        if (Game.currMap.charYPosition(target) == Game.currMap.charYPosition(attacker) + 1 ||
                Game.currMap.charYPosition(target) == Game.currMap.charYPosition(attacker) - 1){
            return true;
        }
        return false;
    }

    public static boolean moveable(Character selected, int x , int y){
        System.out.println(Math.abs(Game.currMap.charXPosition(selected) - x) +
                Math.abs(Game.currMap.charYPosition(selected) - y));
        if (Game.currMap.getCharByPos(x, y) == null && x <= Game.currMap.col && y <= Game.currMap.row
                && x > 0 && y > 0 &&
                selected.getSpeed() >= Math.abs(Game.currMap.charXPosition(selected) - x) +
                        Math.abs(Game.currMap.charYPosition(selected) - y)) {
            return true;
        }

        return false;
    }
    public static void move(Character selected, int x, int y) {
        Game.currMap.removeChar(selected);
        Game.currMap.addChar(selected, x, y);
        selected.useAction();
    }
}
