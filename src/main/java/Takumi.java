public class Takumi extends Character {
    public Takumi() {
        super(new Object[]{"Takumi", 75, 40, 2, 3, 3});
    }

    /*A few ideas about this is how we can instead use a use ultimate method where we can determine
    * range from get ultimate stats, attack power adn other effects through the
    * characters ultimate method. We could also use a reduce power method in the main char class
    * essentially the stats we give to the action class or player class will help determine
    * how the method in the action or player class can utilize it and articulate its values
    * Side note that might be important is the code smell and how bloated the main character class
    * is really getting one idea should be to perhaps put the general stuff like increase or decrease
    * health into another general class where you could send char stats and it finds the solution for
    * you */

    public void ultimate(Character target) {
        double half_health = target.getMaxHealth()*0.6;
        if (target.getCurrHealth() < half_health) {
            target.reduceCurrHealth(target.getCurrHealth());
            this.useAction();
        }
    }

}
