package Entity;

import Entity.Character;

public class EnemyChar extends Character {
    public EnemyChar(String name) {
        super(new Object[]{name, 100, 10, 2, 1, 1, 0});
    }

    public void ultimate(Character target) {

    }
}
