import java.util.ArrayList;

public class Player {
    private String name, description;
    private Graph.Node currentLocation;
    private double endurance, strength, intelligence, luck, dexterity, charisma;
    private int maxHealth, currentHealth, maxMana, currentMana;
    private double physicalDamage, magicProficiency, dodgeChance;
    private ArrayList<Item> inventory = new ArrayList<>();
    private Animal pet = null;


    public Player(String name) {
        new Player(name, "");
    }

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        setStats();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Graph.Node getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Graph.Node currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean moveToRoom(String name) {

        return false;
    }

    //Stats//
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public double getPhysicalDamage() {
        return physicalDamage;
    }

    public void setPhysicalDamage(double physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public double getMagicProficiency() {
        return magicProficiency;
    }

    public void setMagicProficiency(double magicProficiency) {
        this.magicProficiency = magicProficiency;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Animal getPet() {
        if (pet == null) {
            System.out.println("you don't have a pet");
        }
        return pet;
    }

    public void setPet(Animal pet) {
        this.pet = pet;
    }

    public String getInventory() {
        String items = "";
        if (inventory.size() == 0) {
            return "Your pockets are empty.";
        }
        for (int i = 0; i < inventory.size(); i++) {
            items += inventory.get(i).getName();
            if (i == inventory.size() - 2) {
                items += ", ";
            }
        }
        items += ".";
        return items;
    }

    public Item getItemFromInventory(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(itemName)) {
                return inventory.get(i);
            }
        }
        return null;
    }

    public boolean addToInventory(Item item) {
        inventory.add(item);
        return true;
    }

    public Item removeFromInventory(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(itemName)) {
                return inventory.remove(i);
            }
        }
        return null;
    }

    public void setStats() {
        endurance = Math.random() * 10;
        strength = Math.random() * 10;
        intelligence = Math.random() * 10;
        luck = Math.random() * 50;
        dexterity = Math.random() * 10;
        charisma = Math.random() * 10;
        physicalDamage = 1 + strength + luck / 500;
        magicProficiency = 0.01 * intelligence + luck / 500;
        maxHealth = (int) (10 + (endurance * 0.8) + luck / 500);
        maxMana = (int) (10 + (intelligence) + luck / 500);
        dodgeChance = (0.05 * dexterity) + luck / 500;
    }


}