
public abstract class Character {
    private int NAME = 0, MAXHEALTH = 1, CURRHEALTH = 1,
            ATTACK = 2, SPEED = 3,  MAXMETER = 4, RANGE = 5, STUNNED = 0;
    private final String name;
    private int currHealth, maxHealth, attack, speed, max_meter, meter, range, stunned;
    private boolean actionUsed;

    /* String name, int maxHealth, int attack, int speed, int max_meter, int range, int stunned*/
    public Character(Object[] stats) {
        this.name = (String) stats[NAME];
        this.maxHealth = (int) stats[MAXHEALTH];
        this.attack = (int) stats[ATTACK];
        this.speed = (int) stats[SPEED];
        this.currHealth = (int) stats[CURRHEALTH];
        this.actionUsed = false;
        this.meter = (int) stats[MAXMETER];
        this.max_meter = (int) stats[MAXMETER];
        this.range = (int) stats[RANGE];
        this.stunned = 0;
    }

    public int get_Stun() {
        return this.stunned;
    }

    public void increase_Stun(int n) {
        this.stunned += n;
    }

    public void reduce_Stun() {
        this.stunned -= 1;
    }

    public int get_Range() {
        return this.range;
    }

    public void decrease_Meter() {
        if (this.meter > 0) {
            this.meter--;
        }
    }

    public void reset_Meter() {
        this.meter = this.max_meter;
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

