public class Item {
    private String name, descritpion;
    private double damgageValue, healingValue;

    public Item(String name, String descritpion){
        this.name = name;
        this.descritpion = descritpion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }
}