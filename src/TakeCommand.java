import java.util.ArrayList;

public class TakeCommand extends Command {
    Graph g;
    Player p;
    String itemName;

    public TakeCommand(Graph g) {
        super(g);
        this.p = g.getFirstPlayer();
    }

    public void init(String userString) {
        this.itemName = getLastWordIn(userString);
    }

    private Item findItem(Player player) {
        ArrayList<Item> itemsInRoom = player.getCurrentLocation().getItems();
        for (int i = 0; i < itemsInRoom.size(); i++) {
            if (itemsInRoom.get(i).getName().equals(itemName)) {
                Item temp = itemsInRoom.get(i);
                player.getCurrentLocation().removeItem(itemName);
                return temp;
            }
        }
        return null;
    }

    @Override
    public void execute() {
        Item item = findItem(p);
        if (item != null) {
            g.getFirstPlayer().addToInventory(item);
        } else {
            System.out.println("ERROR!");
        }
    }
}
