public class Irelia extends Character {
    public Irelia(boolean team) {
        super(new Object[]{"Irelia", 100, 40, 2, team, 4, 1});
    }

    public void ultimate(Character target) {
        this.reset_Meter();
        target.reduceCurrHealth(this.getAttack());
        this.increaseCurrHealth((int)(0.4 * this.getAttack()));
        this.useAction();
    }
}
