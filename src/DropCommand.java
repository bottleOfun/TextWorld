public class DropCommand extends Command {
    public DropCommand(Graph g) {
        super(g);
    }

    @Override
    public void execute() {
        String itemName = name;
        Item item = g.getFirstPlayer().removeFromInventory(itemName);
        if (item == null) {
            System.out.println("ERROR! Try again.");
        } else {
            System.out.println("dropped: " + item.getName());
            g.getFirstPlayer().getCurrentLocation().addItem(item);
        }
    }
}
