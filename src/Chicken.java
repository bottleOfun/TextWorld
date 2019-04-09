import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chicken extends Animal {

    public Chicken(String name, String description, String toPlayerDialogue, Graph.Node currentLocation) {
        super(name, description, toPlayerDialogue, currentLocation);
        currentLocation.addAnimalInNode(this);
    }

    @Override
    public void moveToRoom() {
        currentLocation.removeAnimalInNode(this);
        currentLocation = currentLocation.getRandomNeighbor();
        currentLocation.addAnimalInNode(this);
    }
}
