package Entity;

import Entity.Character;

public class HpPot extends consumable_Item {
    private final String name;
    private final String description;
    private int curr_usage;
    public HpPot() {
        this.name = "HP";
        this.description = "This potion will restore a character's health by 10 points and can be used only once";
        this.curr_usage = 20;
    }
    public void use() {
        System.out.println("You have used " + get_Name());
    }

    @Override
    public int check_usage() {
        return this.curr_usage;
    }

    @Override
    public void char_effect(Character avatar) {
        int excess_health;
        int total_health = avatar.getCurrHealth() + 20;
        int increase_value = 20;
        if (total_health > avatar.getMaxHealth()) {
            excess_health = total_health - avatar.getMaxHealth();
            increase_value -= excess_health;
        }
        avatar.increaseCurrHealth(increase_value);
        use();
    }

    @Override
    public String get_Name() {
        return this.name;
    }

    @Override
    public String get_Description() {
        return this.description;
    }

    @Override
    public void use_item(Character avatar) {
        System.out.println("HP");
        char_effect(avatar);
    }
}
