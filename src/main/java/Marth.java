public class Marth extends Character {
    public Marth() {
        super(new Object[]{"Marth", 100, 30, 2, 4, 1});
    }

    public void ultimate(Character target) {
        this.reset_Meter();
        target.reduceCurrHealth(this.getAttack());
        this.increaseCurrHealth((int)(0.4 * this.getAttack()));
        this.useAction();
    }
}
