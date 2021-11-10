public class AtkPot extends consumable_Item {
    private final String name;
    private final String description;
    private int curr_usage;
    public AtkPot() {
        this.name = "Fighting Spirit";
        this.description = "This potion will increase a character's attack points by 10, and can be used only once";
        this.curr_usage = 100;
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
        avatar.increaseCurrAttack(10);
        use();
    }

    @Override
    public void use() {
        System.out.println("You have used " + get_Name());
        this.curr_usage -= 100;
    }
}
