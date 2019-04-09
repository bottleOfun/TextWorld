import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Joe Shmoe", "Just an ordinary guy.");
        Graph g = new Graph();
        g.addNode("hall", "It's really damp and dark in here");
        g.addNode("closet", "You are surrounded by an endless sea of nothing");
        g.addNode("dungeon", "The air is moist and moss covers the cobble stone floor");
        g.addNode("bedroom", "There is a large bed.");
        g.addNode("bathroom", "You see a dank shower");
        //generateNodes(g);

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


        player.setCurrentLocation(g.getNode("hall"));

        // "game loop" where I get user input and move the player
        String response;
        Scanner in = new Scanner(System.in);

        do {
            //display the room and ask the player what they want to do
            System.out.println("You are currently in the " + player.getCurrentLocation().getName());
            System.out.println("What do you want to do? ----->");
            response = in.nextLine();

            ////////////////////////////////////////////////////////
            if (response.contains("go")) {
                String room = findName(response);
                if (room == null) {
                    System.out.println("ERROR! Try again.");
                } else {
                    if (g.getHashNodes().containsKey(room)) {
                        player.setCurrentLocation(g.getNode(room));
                    } else {
                        System.out.println("ERROR! Try again.");
                    }
                }
                ////////////////////////////////////////////////////////
            } else if (response.contains("take")) {
                Item item = findItemName(response, player);
                if (item == null) {
                    System.out.println("ERROR! Try again.");
                } else {
                    player.addToInventory(item);
                }
                ////////////////////////////////////////////////////////
            } else if (response.contains("drop")) {
                String itemName = findName(response);
                Item item = player.removeFromInventory(itemName);
                if (item == null) {
                    System.out.println("ERROR! Try again.");
                } else {
                    System.out.println("dropped: " + item.getName());
                    player.getCurrentLocation().addItem(item);
                }
                ////////////////////////////////////////////////////////
            } else if (response.equals("look around")) {
                System.out.println(player.getCurrentLocation().getDescription());
                System.out.println("Neighbors are: " + player.getCurrentLocation().getNeighborNames());
                if (player.getCurrentLocation().getItems().size() != 0) {
                    System.out.println("You see:" + player.getCurrentLocation().getItemNames() + " laying on the floor");
                }
                if (checkForAnimals(player.getCurrentLocation(), g)) {
                    System.out.println("Woah! a wild:" + getCurrentLocAnimalNames(player.getCurrentLocation(), g) + "!");
                    for (int i = 0; i < player.getCurrentLocation().getAnimalsListInNode().size(); i++) {
                        double chanceToSpeak = Math.random();
                        if(chanceToSpeak >=0.5){
                            System.out.println(player.getCurrentLocation().getAnimalsListInNode().get(i).getToPlayerDialogue());
                        }
                    }
                }
                ////////////////////////////////////////////////////////
            } else if (response.equals("inventory")) {
                System.out.println(player.getInventory());
                ////////////////////////////////////////////////////////
            } else if (response.contains("look at")) {
                String itemName = findName(response);
                if (player.getItemFromInventory(itemName) == null) {
                    System.out.println("ERROR! Try again.");
                } else {
                    Item item = player.getItemFromInventory(itemName);
                    System.out.println("Description: " + item.getDescritpion());
                }
                ////////////////////////////////////////////////////////
            } else if (response.contains("add")) {
                String room = findName(response);
                if (room != null) {
                    g.addDirectedEdge(player.getCurrentLocation().getName(), room);
                } else {
                    System.out.println("ERROR! Try again.");
                }
                ////////////////////////////////////////////////////////
            } else if (response.equals("quit")) {
                System.out.println("you quit :(");
                break;
            } else {
                System.out.println("error! commands are: 'go <roomname>', 'take <itemname>, 'drop <itemName>',\n 'look around', 'inventory', 'look at <itemname> 'add <roomname>', 'quit'");
            }

            ////////////////////////////////////////////////////////////

            g.updateAllCreatures(g, player);


            System.out.println("======================================================================================");
        } while (!response.equals("quit"));
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

    private static void generateNodes(Graph g) {


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