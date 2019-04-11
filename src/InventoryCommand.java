public class InventoryCommand extends Command {
    public InventoryCommand(Graph g) {
        super(g);
    }

    @Override
    public void execute() {
        System.out.println(g.getFirstPlayer().getInventory());
    }
}
