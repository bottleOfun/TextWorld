public class LookAtCommand extends Command {
    public LookAtCommand(Graph g) {
        super(g);
    }

    @Override
    public void execute() {
        g.getFirstPlayer().getItemFromInventory(name);
    }
}

