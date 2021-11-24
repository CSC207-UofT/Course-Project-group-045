public abstract class Character {
    private final String name;
    private boolean ally;
    private int currHealth, maxHealth, attack, speed, mana;
    private boolean actionUsed;


    public Character(String name, int maxHealth, int attack, int speed, boolean ally) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.speed = speed;
        this.ally = ally;
        this.currHealth = maxHealth;
        this.actionUsed = false;
        this.mana = 0;
    }

    public void increase_mana() {
        if (this.mana < 3) {
            this.mana++;
        }
    }

    public int get_mana() {
        return this.mana;
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

    public boolean is_Ally() {
        return this.ally;
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
        return this.actionUsed;
    }

    public void restoreAction(){
        this.actionUsed = false;
    }

    public void useAction(){
        this.actionUsed = true;
    }



}
