import java.util.ArrayList;
import java.util.Map;

public class Wumpus extends Animal {
    private Player p;
    private Graph g;

    public Wumpus(String name, String description, String toPlayerDialogue, Graph.Node currentLocation, Graph g, Player p) {
        super(name, description, toPlayerDialogue, currentLocation);
        currentLocation.addAnimalInNode(this);
        updateData(g,p);
    }

    public void updateData(Graph g, Player p){
        this.g = g;
        this.p = p;
    }

    @Override
    public void moveToRoom() {
        ArrayList<Graph.Node> nodes = currentLocation.getNeighborList();

        if (nodes.size() >= 2) {
            do {
                Graph.Node node = currentLocation.getRandomNeighbor();
                if (node != p.getCurrentLocation()) {
                    currentLocation.removeAnimalInNode(this);
                    currentLocation = currentLocation.getRandomNeighbor();
                    currentLocation.addAnimalInNode(this);
                    break;
                }
            } while (true);
        } else if (p.getCurrentLocation() == currentLocation) {
            currentLocation.removeAnimalInNode(this);
            currentLocation = currentLocation.getRandomNeighbor();
            currentLocation.addAnimalInNode(this);
        }

    }
}
