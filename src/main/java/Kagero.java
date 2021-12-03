public class Kagero extends Character {
    public Kagero() {
        super(new Object[]{"Takumi", 75, 40, 2, 3, 1, 0});
    }

    public void ultimate(Character target) {
        double enemy_attack = target.getAttack()*0.2;
        target.reduceCurrHealth(50);
        if (target.getCurrHealth() == 0) {
            this.increaseCurrAttack((int)enemy_attack);
        }
        this.useAction();
    }
}
