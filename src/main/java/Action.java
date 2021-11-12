public class Action {

    public static void attack(Character attacker, Character target) {
        target.reduceCurrHealth(attacker.getAttack());
        System.out.println(attacker.getName() + " attacked " + target.getName() + " for " + attacker.getAttack() + " damage!");
        System.out.println(target.getName() + "'s health is now " + target.getCurrHealth());
        attacker.useAction();
    }

    public static void move() {}


}
