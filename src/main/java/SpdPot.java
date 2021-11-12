public class SpdPot extends consumable_Item {
    private final String name;
    private final String description;
    private int curr_usage;

    public SpdPot() {
        this.name = "Mercury's Blessing";
        this.description = "This potion will grant a character 1 extra points of speed once";
        this.curr_usage = 1;
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
    public int check_usage() {
        return this.curr_usage;
    }

    @Override
    public void use_item(Character avatar) {
        char_effect(avatar);
    }

    @Override
    public void char_effect(Character avatar) {
        avatar.increaseCurrSpeed(1);
    }

    @Override
    public void use() {
        System.out.println("You have used " + get_Name());
        this.curr_usage -= 1;
    }
}
