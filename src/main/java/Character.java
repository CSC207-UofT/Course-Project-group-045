public abstract class Character {
    private final String name;

    private int currHealth, attack, defense, speed;
    private final int maxHealth;
    private boolean actionUsed;

    public Character(String name) {
        this.name = name;
        maxHealth = 100;
        currHealth = maxHealth;
        attack = 10;
        defense = 10;
        speed = 10;
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

    public int getCurrHealth(){
        return this.currHealth;
    }

    public void reduceCurrHealth(int damage){
        this.currHealth = this.currHealth - damage;
    }

    public boolean isActionUsed(){
        return actionUsed;
    }

    public void restoreAction(){
        this.actionUsed = false;
    }

    public void attack(Character user, Character target){
        target.reduceCurrHealth(user.getAttack());
        System.out.println(this.name + " attacked " + target.getName() + " for " + user.getAttack() + " damage!");
        System.out.println(target.getName() + "'s health is now " + target.getCurrHealth());
        this.actionUsed = true;
    }


}
