public class Irelia extends Character {
    public Irelia(boolean team) {
        super("Irelia", 100, 40, 2, team, 4);
    }

    public void ultimate(Character target) {
        this.reset_Meter();
        if (!(target.is_Ally())) {
            target.reduceCurrHealth(this.getAttack());
            this.increaseCurrHealth((int)(0.4 * this.getAttack()));
            this.useAction();
        }
        else {
            System.out.println("You can only select an enemy");
        }
    }
}
