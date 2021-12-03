public class Hector extends Character {
    public Hector() {
        super(new Object[]{"Hector", 100, 30, 2, 4, 1, 0});
    }

    public void ultimate(Character target) {
        target.reduceCurrHealth(30);
        target.increase_Stun(2);
    }
}
