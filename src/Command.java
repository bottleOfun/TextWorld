public abstract class Command {
    public String commandName;
    public String name;
    public Graph g;

    public Command(Graph g) {
        this.g = g;
    }

    public void init(String userString) {
        this.name = getLastWordIn(userString);
    }

    public String getLastWordIn(String userString) {
        if (userString.contains("<") && userString.contains(">")) {
            int start = userString.indexOf("<");
            int end = userString.indexOf(">");
            return userString.substring(start + 1, end);
        } else {
            return null;
        }
    }

    public abstract void execute();

}
