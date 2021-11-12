public class HpPot extends consumable_Item {
    private final String name;
    private final String description;
    private int curr_usage;
    public HpPot() {
        this.name = "Health Potion";
        this.description = "This potion will restore a character's health by 10 points and can be used only once";
        this.curr_usage = 100;
    }
    public void use() {
        System.out.println("You have used " + get_Name());
        this.curr_usage -= 100;
    }

    @Override
    public int check_usage() {
        return this.curr_usage;
    }

    @Override
    public void char_effect(Character avatar) {
        int excess_health;
        int total_health = avatar.getCurrHealth() + 10;
        int increase_value = 10;
        if (total_health > 100) {
            excess_health = total_health - 100;
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
        char_effect(avatar);
    }
}
