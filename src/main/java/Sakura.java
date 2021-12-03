public class Sakura extends Character {
    public Sakura() {
        super(new Object[]{"Sakura", 75, 20, 2, 3, 1, 0});
    }

    public void ultimate(Character target) {
        int heal_Boost = (int) (target.getCurrHealth()*0.2);
        this.increaseCurrHealth(heal_Boost);
    }
}
