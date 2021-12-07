public class Takumi extends Character {
    public Takumi() {
        super(new Object[]{"Takumi", 80, 40, 2, 3, 1, 0});
    }

    public void ultimate(Character target) {
        this.reset_Meter();
        target.reduceCurrHealth((int) (this.getAttack() * 1.5));
    }

}