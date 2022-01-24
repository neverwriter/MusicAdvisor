package advisor;

import java.util.Locale;

public class Menu {

    public static void menu() {

        String[] commands = CommandReader.readCommand();

        Command command = Command.valueOf(commands[0].toUpperCase(Locale.ROOT));

        while (!command.equals(Command.EXIT)) {
            Task task = new MenuTask();
            switch (command) {
                case NEW:

                    task.handleNewReleases();
                    break;

                case FEATURED:
                    task.handleFeatured();
                    break;

                case CATEGORIES:
                    task.handleCategories();
                    break;

                case PLAYLISTS:
                    task.handlePlaylists(commands[1]);
                    break;

                default:
                    System.out.println("Unknown command");
            }

            commands = CommandReader.readCommand();

            command = Command.valueOf(commands[0].toUpperCase(Locale.ROOT));
        }

    }

}
