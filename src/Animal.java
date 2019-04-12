import java.util.ArrayList;

public abstract class Animal {
    protected int health, mana;
    protected Graph.Node currentLocation;
    protected ArrayList<Item> inventory;
    protected String name, description, toPlayerDialogue;
    protected Player master;

    public Animal(String name, String description, String toPlayerDialogue, Graph.Node currentLocation) {
        this.name = name;
        this.description = description;
        this.toPlayerDialogue = toPlayerDialogue;
        this.currentLocation = currentLocation;
    }

    public abstract void moveToRoom();

    public Graph.Node getCurrentLocation() {
        return currentLocation;
    }

    public void speak(String playerName) {
        System.out.println(toPlayerDialogue);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setCurrentLocation(Graph.Node currentLocation) {
        this.currentLocation = currentLocation;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
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

    public String getToPlayerDialogue() {
        return toPlayerDialogue;
    }

    public void setToPlayerDialogue(String toPlayerDialogue) {
        this.toPlayerDialogue = toPlayerDialogue;
    }

    public Player getMaster() {
        return master;
    }

    public void setMaster(Player master) {
        this.master = master;
    }
}
