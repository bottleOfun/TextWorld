import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player player = new Player("Joe Shmoe", "Just an ordinary guy.");
        Graph g = new Graph();
        generateNodes(g, player);
        player.setCurrentLocation(g.getNode("hall"));
        g.addPlayer(player);

        HashMap<String, Command> commands = initCommands(g, player);

        // "game loop" where I get user input and move the player
        String response;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("You are currently in the " + player.getCurrentLocation().getName());
            System.out.println("What do you want to do? ----->");
            response = in.nextLine();
            Command command = lookupCommand(response, commands);
            command.execute();

            System.out.println("========================================================================================\n");

            g.updateAllCreatures(g, player);
        } while (!response.equals("quit"));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static HashMap<String, Command> initCommands(Graph g, Player p) {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("go", new GoCommand(g));
        commands.put("take", new TakeCommand(g));
        commands.put("drop", new DropCommand(g));
        commands.put("look around", new LookAroundCommand(g));
        commands.put("inventory", new InventoryCommand(g));
        commands.put("look at", new LookAtCommand(g));
        commands.put("add", new AddCommand(g));
        commands.put("quit", new QuitCommand(g));
        commands.put("", new EmptyCommand(g));
        return commands;

    }

    private static Command lookupCommand(String response, HashMap<String, Command> commands) {
        String commandWord = response;
        if (!response.contains("look")) {
            commandWord = getFirstWornIn(response);
        }
        Command c = commands.get(commandWord);
        if (c == null) {
            return commands.get("");
        }

        c.init(response);

        return c;
    }

    private static String getFirstWornIn(String response) {
        if (response.contains(" ")) {
            int index = response.indexOf(" ");
            return response.substring(0, index);
        } else {
            return response;
        }
    }

    private static String getCurrentLocAnimalNames(Graph.Node currentLocation, Graph g) {
        String names = "";
        for (int i = 0; i < currentLocation.getAnimalsListInNode().size(); i++) {

            if (currentLocation.getAnimalsListInNode().get(i).getCurrentLocation().equals(currentLocation)) {
                names += currentLocation.getAnimalsListInNode().get(i).getName();
                if (i < currentLocation.getAnimalsListInNode().size() - 1) names += ", ";
            }
        }
        return names;
    }

    private static boolean checkForAnimals(Graph.Node currentLocation, Graph g) {
        for (int i = 0; i < currentLocation.getAnimalsListInNode().size(); i++) {
            if (currentLocation.getAnimalsListInNode().get(i).getCurrentLocation().equals(currentLocation)) {
                return true;
            }
        }
        return false;
    }


    private static Item findItemName(String response, Player player) {
        String itemName = findName(response);
        ArrayList<Item> itemsInRoom = player.getCurrentLocation().getItems();
        for (int i = 0; i < itemsInRoom.size(); i++) {
            if (itemsInRoom.get(i).getName().equals(itemName)) {
                Item temp = itemsInRoom.get(i);
                player.getCurrentLocation().removeItem(itemName);
                return temp;
            }
        }
        return null;
    }

    private static void generateNodes(Graph g, Player player) {
        g.addNode("hall", "It's really damp and dark in here");
        g.addNode("closet", "You are surrounded by an endless sea of nothing");
        g.addNode("dungeon", "The air is moist and moss covers the cobble stone floor");
        g.addNode("bedroom", "There is a large bed.");
        g.addNode("bathroom", "You see a dank shower");

        g.getNode("hall").addItem(new Item("A Rock", "It looks useless..."));
        g.addDirectedEdge("hall", "dungeon");
        g.addUndirected("hall", "closet");
        g.addUndirected("closet", "bedroom");
        g.addUndirected("bedroom", "bathroom");

        g.addAnimalInGraph(new Chicken("chicken", "it's a ChIckEn", "bock bock", g.getNode("hall")));
        g.addAnimalInGraph(new Chicken("chicken", "it's a ChIckEn", "bock bock", g.getNode("hall")));
        g.addAnimalInGraph(new Chicken("chicken", "it's a ChIckEn", "bock bock", g.getNode("hall")));

        g.addAnimalInGraph(new Wumpus("Wumpus", "WumP WumP", "reeeeeeeeeeeeEEE", g.getNode("hall"), g, player));
        g.addAnimalInGraph(new PopStar("Popstar", "wryyyyyyyyyyyyyyyy", "Kono DiO", g.getNode("bathroom"), g, player));
    }

    private static String findName(String response) {
        if (response.contains("<") && response.contains(">")) {
            int start = response.indexOf("<");
            int end = response.indexOf(">");
            return response.substring(start + 1, end);
        } else {
            return null;
        }
    }

}