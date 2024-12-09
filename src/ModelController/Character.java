package ModelController;

public class Character {
    private int character_id;
    private String character_name;
    private String character_class;
    private double character_hp;
    private double character_mp;

    /*
    We have chosen to use a separate class to control our inventory, this way the character class does not have sole -
    responsibility over the parameters of the inventories and does not need to do weight control checks.
     */
    private InventoryManager inventoryManager;

    public Character(){}// Empty constructor
    public Character(int character_id, String character_name, String character_class, double character_hp, double character_mp, InventoryManager inventoryManager) {
        this.character_id = character_id;
        this.character_name = character_name;
        this.character_class = character_class;
        this.character_hp = character_hp;
        this.character_mp = character_mp;
        this.inventoryManager = inventoryManager;
    }// Full Constructor

    //region Getters & Setters
    public int getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(int character_id) {
        this.character_id = character_id;
    }

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

    public String getCharacter_class() {
        return character_class;
    }

    public void setCharacter_class(String character_class) {
        this.character_class = character_class;
    }

    public double getCharacter_hp() {
        return character_hp;
    }

    public void setCharacter_hp(double character_hp) {
        this.character_hp = character_hp;
    }

    public double getCharacter_mp() {
        return character_mp;
    }

    public void setCharacter_mp(double character_mp) {
        this.character_mp = character_mp;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }
    //endregion



}// End
