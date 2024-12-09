package ModelController;

public class Weapon extends Item {
    private int weapon_type;

    public Weapon() {
        super();
    }

    public Weapon(int item_id, int item_type, String item_name, String item_description, double item_weight, double item_value, int weapon_type) {
        super(item_id, item_type, item_name, item_description, item_weight, item_value);
        this.weapon_type = weapon_type;
    }

    //region Getters & Setters
    public int getWeapon_type() {
        return weapon_type;
    }

    public void setWeapon_type(int weapon_type) {
        this.weapon_type = weapon_type;
    }

    @Override
    public int getItem_id() {
        return super.getItem_id();
    }

    @Override
    public void setItem_id(int item_id) {
        super.setItem_id(item_id);
    }

    @Override
    public int getItem_type() {
        return super.getItem_type();
    }

    @Override
    public void setItem_type(int item_type) {
        super.setItem_type(item_type);
    }

    @Override
    public String getItem_name() {
        return super.getItem_name();
    }

    @Override
    public void setItem_name(String item_name) {
        super.setItem_name(item_name);
    }

    @Override
    public String getItem_description() {
        return super.getItem_description();
    }

    @Override
    public void setItem_description(String item_description) {
        super.setItem_description(item_description);
    }

    @Override
    public double getItem_weight() {
        return super.getItem_weight();
    }

    @Override
    public void setItem_weight(double item_weight) {
        super.setItem_weight(item_weight);
    }

    @Override
    public double getItem_value() {
        return super.getItem_value();
    }

    @Override
    public void setItem_value(double item_value) {
        super.setItem_value(item_value);
    }

    @Override
    public void showItemInfo() {
        super.showItemInfo();
    }

    @Override
    public void useItem() {
        super.useItem();
    }
    //endregion

}// End
