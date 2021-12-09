package Entity;

import Entity.Character;

public class Marth extends Character {
    public Marth() {
        super(new Object[]{"Marth", 90, 30, 2, 4, 1, 0});
    }

    public void ultimate(Character target) {
        this.reset_Meter();
        target.reduceCurrHealth(this.getAttack() + 10);
        this.increaseCurrHealth((int)(0.5 * (this.getAttack()) + 10));
        if (this.getCurrHealth() > this.getMaxHealth()){
            this.reduceCurrHealth(this.getCurrHealth() - this.getMaxHealth());
        }
        this.useAction();
    }
}