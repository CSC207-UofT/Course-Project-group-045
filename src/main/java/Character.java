public abstract class Character {
    private final String name;

    private int currHealth, maxHealth, attack, speed;
    private boolean actionUsed;

    public Character(String name, int maxHealth, int attack, int speed) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.speed = speed;
        currHealth = maxHealth;
        actionUsed = false;
    }

    public String getName(){
        return name;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getCurrHealth(){
        return this.currHealth;
    }

    public void reduceCurrHealth(int damage){
        this.currHealth = this.currHealth - damage;
    }

    public void increaseCurrHealth(int boost) {
        this.currHealth += boost;
    }

    public void increaseCurrAttack(int power) {
        this.attack += power;
    }

    public void increaseCurrSpeed(int stamina) {
        this.speed += stamina;
    }

    public boolean isActionUsed(){
        return actionUsed;
    }

    public void restoreAction(){
        this.actionUsed = false;
    }

    public void useAction(){
        this.actionUsed = true;
    }


/*    public void attack(Character user, Character target){
        target.reduceCurrHealth(user.getAttack());
        System.out.println(this.name + " attacked " + target.getName() + " for " + user.getAttack() + " damage!");
        System.out.println(target.getName() + "'s health is now " + target.getCurrHealth());
        this.actionUsed = true;
    }*/


}
