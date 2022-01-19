package advisor;

public enum Command {
    EXIT("EXIT"),
    NEW("NEW"),
    FEATURED("FEATURED"),
    CATEGORIES("CATEGORIES"),
    PLAYLISTS("PLAYLISTS");


    private String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
