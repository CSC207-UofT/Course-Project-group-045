public abstract class Character {
    private final String name;
    private boolean ally;
    private int currHealth, maxHealth, attack, speed, max_meter, meter;
    private boolean actionUsed;


    public Character(String name, int maxHealth, int attack, int speed, boolean ally, int max_meter) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.speed = speed;
        this.ally = ally;
        this.currHealth = maxHealth;
        this.actionUsed = false;
        this.meter = 0;
        this.max_meter = max_meter;
    }

    public void increase_Meter() {
        if (this.meter < this.max_meter) {
            this.meter++;
        }
    }

    public void reset_Meter() {
        this.meter = 0;
    }

    public int get_Meter() {
        return this.meter;
    }

    public int get_maxMeter() {
        return this.max_meter;
    }

    public abstract void ultimate(Character target);

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
