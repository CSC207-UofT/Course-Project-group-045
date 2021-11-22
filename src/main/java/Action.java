
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
        if (attacker.getSpeed() + 1 >= Math.abs(Game.currMap.charXPosition(attacker) -
                        Game.currMap.charXPosition(target)) +
                        Math.abs(Game.currMap.charYPosition(attacker) - Game.currMap.charYPosition(target))) {
            ArrayList <Integer> list = new ArrayList <> ();
            list.add(0, Game.currMap.charXPosition(target) + 1);
            list.add(1, Game.currMap.charXPosition(target) - 1);
            list.add(2, Game.currMap.charYPosition(target) + 1);
            list.add(3, Game.currMap.charYPosition(target) - 1);
            for (int i = 0 ; i < 2 ; i++){
                if (moveable(attacker, list.get(i), Game.currMap.charYPosition(target))){
                    move(attacker, list.get(i), Game.currMap.charYPosition(target));
                    return true;
                }
            }
            for (int i = 0 ; i < 2 ; i++){
                if (moveable(attacker, Game.currMap.charXPosition(target), list.get(i + 2))){
                    move(attacker, Game.currMap.charXPosition(target), list.get(i + 2));
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean moveable(Character selected, int x , int y){
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

