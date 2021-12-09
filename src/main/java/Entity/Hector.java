package Entity;

import Entity.Character;

public class Hector extends Character {
    public Hector() {
        super(new Object[]{"Hector", 100, 20, 2, 2, 1, 0});
    }

    public void ultimate(Character target) {
        this.reset_Meter();
        target.reduceCurrHealth(40);
        target.increase_Stun(2);
        this.useAction();
    }
}
