public class Character {
    private String name;
    private int maxHealth;
    private int currHealth;
    private int attack;
    private int defense;
    private int speed;
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

    public void attack(Character user, Character target){
        target.reduceCurrHealth(user.getAttack());
        System.out.println(this.name + " attacked " + target.getName() + " for " + user.getAttack() + " damage!");
        System.out.println(target.getName() + "'s health is now " + target.getCurrHealth());
        this.actionUsed = true;
    }

}
