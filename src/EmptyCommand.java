public class EmptyCommand extends Command {
    @Override
    public void execute() {
        System.out.println("ERROR! commands are: 'go <roomname>', 'take <itemname>, 'drop <itemName>',\\n 'look around', 'inventory', 'look at <itemname> 'add <roomname>', 'quit'\"");
    }
}
