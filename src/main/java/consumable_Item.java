public abstract class consumable_Item implements Item {
    private String name;
    private String description;
    private int usage_value;
    private int curr_usage;
    public abstract void char_effect(Character avatar);
    public abstract void use();
}
