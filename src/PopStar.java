import java.util.ArrayList;
import java.util.Map;

public class PopStar extends Animal {
    private Player p;
    private Graph g;

    public PopStar(String name, String description, String toPlayerDialogue, Graph.Node currentLocation, Graph g, Player p) {
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
        if (p.getCurrentLocation() != currentLocation) {
            ArrayList<Graph.Node> pathList = g.findPath(currentLocation, p.getCurrentLocation());
            if (pathList.size() >= 1) {
                currentLocation.removeAnimalInNode(this);
                currentLocation = pathList.get(0);
                currentLocation.addAnimalInNode(this);
            } else {
                currentLocation.removeAnimalInNode(this);
                currentLocation = currentLocation.getRandomNeighbor();
                currentLocation.addAnimalInNode(this);
            }
        }
    }
}
