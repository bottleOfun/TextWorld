public class AddCommand extends Command {
    String roomName;

    public AddCommand(Graph g) {
        super(g);
    }

    @Override
    public void execute() {
        roomName = name;
        g.addDirectedEdge(g.getFirstPlayer().getCurrentLocation().getName(), roomName);
    }
}
