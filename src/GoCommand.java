public class GoCommand extends Command {
    public GoCommand(Graph g) {
        super(g);
    }

    @Override
    public void execute() {
        String room = name;
        if (room == null) {
            System.out.println("ERROR! Try again.");
        } else {
            if (g.getHashNodes().containsKey(room)) {
                g.getFirstPlayer().setCurrentLocation(g.getNode(room));
            } else {
                System.out.println("ERROR! Try again.");
            }
        }
    }
}
