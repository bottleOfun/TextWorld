public class QuitCommand extends Command {
    public QuitCommand(Graph g) {
        super(g);
    }

    @Override
    public void execute() {
        System.out.println("you quit :(");
    }
}
