public class Action {

    public static void attack(Character attacker, Character target) {
        target.reduceCurrHealth(attacker.getAttack());
        System.out.println(attacker.getName() + " attacked " + target.getName() + " for " + attacker.getAttack() + " damage!");
        System.out.println(target.getName() + "'s health is now " + target.getCurrHealth());
        attacker.useAction();
    }

    public static boolean attackable(Character attacker, Character target){
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
        return true;
    }
    public static void move() {}
}
