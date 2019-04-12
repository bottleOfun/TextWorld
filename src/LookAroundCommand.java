public class LookAroundCommand extends Command {
    public LookAroundCommand(Graph g) {
        super(g);
    }

    @Override
    public void execute() {
        System.out.println(g.getFirstPlayer().getCurrentLocation().getDescription());
        System.out.println("Neighbors are: " + g.getFirstPlayer().getCurrentLocation().getNeighborNames());
        if (g.getFirstPlayer().getCurrentLocation().getItems().size() != 0) {
            System.out.println("You see:" + g.getFirstPlayer().getCurrentLocation().getItemNames() + " laying on the floor");
        }
        if (g.getFirstPlayer().getCurrentLocation().checkForAnimals()) {
            System.out.println("Woah! a wild:" + g.getFirstPlayer().getCurrentLocation().getAnimalNamesInNode() + "!");
            for (int i = 0; i < g.getFirstPlayer().getCurrentLocation().getAnimalsListInNode().size(); i++) {
                double chanceToSpeak = Math.random();
                if(chanceToSpeak >=0.5){
                    System.out.println(g.getFirstPlayer().getCurrentLocation().getAnimalsListInNode().get(i).getToPlayerDialogue());
                }
            }
        }
    }
}
