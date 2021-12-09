package Entity;

import Entity.Character;

public class Kagero extends Character {
    public Kagero() {
        super(new Object[]{"Kagero", 80, 40, 2, 3, 1, 0});
    }

    public void ultimate(Character target) {
        this.reset_Meter();
        double enemy_attack = target.getAttack()*0.5;
        target.reduceCurrHealth(this.getAttack() + 15);
        if (target.getCurrHealth() == 0) {
            this.increaseCurrAttack((int)enemy_attack);
        }
        this.useAction();
    }
}