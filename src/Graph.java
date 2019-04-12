import java.util.*;

public class Graph {
    private HashMap<String, Node> nodes;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Animal> allAnimals = new ArrayList<>();


    public Graph() {
        nodes = new HashMap<>();
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public HashMap getHashNodes() {
        return nodes;
    }

    public void addNode(String name) {
        addNode(name, "this is a type of room.");
    }

    public void addNode(String name, String description) {
        Node n = new Node(name, description);
        nodes.put(name, n);
    }

    public void destroyNode(String name) {
        Node n = nodes.get(name);
        ArrayList<String> neighborNames = n.getNeighborNamesList();
        n.removeAllNeighbors();
        for (int i = 0; i < neighborNames.size(); i++) {
            nodes.get(neighborNames.get(i)).removeNeighbor(n.getName());
        }
        nodes.remove(n);
    }

    public void addDirectedEdge(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
    }

    public void addUndirected(String name1, String name2) {
        Node n1 = getNode(name1);
        Node n2 = getNode(name2);
        n1.addNeighbor(n2);
        n2.addNeighbor(n1);
    }

    //finds path from start to end with a length of 5
    public ArrayList<Node> findPath(Node start, Node end) {
        ArrayList<Graph.Node> startNeighbors = start.getNeighborList();
        ArrayList<Graph.Node> path = new ArrayList<>();

        //check for endRoom in neighbors, up to the 1th neighbor
        for (Node n : startNeighbors) {
            if (n == end) {
                path.add(end);
                return path;
            }
        }

        //check for endRoom in neighbors, up to the 2th neighbor
        for (Node n : startNeighbors) {
            for (Node n2 : n.getNeighborList()) {
                if (n2 == end) {
                    path.add(n2);
                    path.add(n);
                    return path;
                }
            }
        }

        //check for endRoom in neighbors, up to the 3th neighbor
        for (Node n : startNeighbors) {
            for (Node n2 : n.getNeighborList()) {
                for (Node n3 : n2.getNeighborList()) {
                    if (n3 == end) {
                        path.add(n3);
                        path.add(n2);
                        path.add(n);
                        return path;
                    }
                }
            }
        }

        //check for endRoom in neighbors, up to the 4th neighbor
        for (Node n : startNeighbors) {
            for (Node n2 : n.getNeighborList()) {
                for (Node n3 : n2.getNeighborList()) {
                    for (Node n4 : n3.getNeighborList()) {
                        if (n4 == end) {
                            path.add(n4);
                            path.add(n3);
                            path.add(n2);
                            path.add(n);
                            return path;
                        }
                    }
                }
            }
        }
        return path;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Animal> getAnimalsListInGraph() {
        return allAnimals;
    }

    public String getAnimalNamesInGraph() {
        String names = "";
        for (int i = 0; i < allAnimals.size(); i++) {
            names += allAnimals.get(i).getName();
            names += ",";
        }
        names += ".";
        return names;
    }

    public void addAnimalInGraph(Animal animal) {
        allAnimals.add(animal);
    }

    public Animal removeAnimalInGraph(Animal animal) {
        for (int i = 0; i < allAnimals.size(); i++) {
            if (allAnimals.get(i).getName().equals(animal.getName())) {
                return allAnimals.remove(i);
            }
        }
        System.out.println("ERROR! Couldn't remove animal");
        return null;
    }

    public void updateAllCreatures(Graph g, Player p) {
        for (int i = 0; i < g.getAnimalsListInGraph().size(); i++) {
            Animal temp = g.getAnimalsListInGraph().get(i);
            if (doesAnimalNeedData(temp)) {
                String animalType = checkAnimalType(temp);
                if (animalType.equals("Wumpus"))
                    ((Wumpus) g.getAnimalsListInGraph().get(i)).updateData(g, p);
                if (animalType.equals("PopStar"))
                    ((PopStar) g.getAnimalsListInGraph().get(i)).updateData(g, p);
            }
            g.getAnimalsListInGraph().get(i).moveToRoom();

        }
    }

    private String checkAnimalType(Animal temp) {
        if (temp instanceof PopStar) return "PopStar";
        if (temp instanceof Wumpus) return "Wumpus";
        return null;
    }

    private boolean doesAnimalNeedData(Animal temp) {
        if (temp instanceof PopStar || temp instanceof Wumpus) {
            return true;
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Player> getPlayerListInGraph() {
        return players;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public Player getFirstPlayer() {
        return players.get(0);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public class Node {
        private String name;
        private HashMap<String, Node> neighbors;
        private String description;
        private ArrayList<Item> items = new ArrayList<>();
        private ArrayList<Animal> animals = new ArrayList<>();


        private Node(String name, String description) {
            neighbors = new HashMap<>();
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getNeighborNames() {
            String names = "";
            for (String name : neighbors.keySet()) {
                names += name + ", ";
            }
            names += ".";
            return names;
        }

        public ArrayList<String> getNeighborNamesList() {
            ArrayList<String> neighbor = new ArrayList<>();
            for (String name : neighbors.keySet()) {
                neighbor.add(name);
            }
            return neighbor;
        }

        public ArrayList<Node> getNeighborList() {
            return (new ArrayList<>(neighbors.values()));
        }

        public Node getRandomNeighbor() {
            ArrayList<Graph.Node> nodes = new ArrayList<>(neighbors.values());
            if (neighbors.size() == 0) {
                return this;
            }
            return nodes.get((int) (Math.random() * neighbors.size()));
        }

        public Node getNeighbor(String name) {
            return neighbors.get(name);
        }

        private void removeNeighbor(String name) {
            neighbors.remove(name);
        }

        private void addNeighbor(Node n) {
            if (!neighbors.containsKey(n.getName())) {
                neighbors.put(n.getName(), n);
            }
        }

        public void removeAllNeighbors() {
            neighbors.clear();
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public String getItemNames() {
            String names = "";
            for (int i = 0; i < items.size(); i++) {
                names += items.get(i).getName();
                if (i < items.size() - 1) names += ", ";
            }
            names += ".";
            return names;
        }

        public void addItem(String name) {
            items.add(new Item(name, "its an item"));
        }

        public void addItem(String name, String description) {
            items.add(new Item(name, description));
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public Item removeItem(String name) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(name)) {
                    return items.remove(i);
                }
            }
            return null;
        }

        public boolean destroyItem(String name) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(name)) {
                    items.remove(i);
                    return true;
                }
            }
            return false;
        }

        public ArrayList<Animal> getAnimalsListInNode() {
            return animals;
        }

        public String getAnimalNamesInNode() {
            String names = "";
            for (int i = 0; i < animals.size(); i++) {
                names += animals.get(i).getName();
                names += ",";
            }
            names += ".";
            return names;
        }

        public void addAnimalInNode(Animal animal) {
            animals.add(animal);
        }

        public Animal removeAnimalInNode(Animal animal) {
            for (int i = 0; i < animals.size(); i++) {
                if (animals.get(i).getName().equals(animal.getName())) {
                    return animals.remove(i);
                }
            }
            System.out.println("ERROR! Couldn't remove animal");
            return null;
        }


        public boolean checkForAnimals() {
            if (animals.size() > 0) {
                return true;
            }
            return false;
        }
    }
}