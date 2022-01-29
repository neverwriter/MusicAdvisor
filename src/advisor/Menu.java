package advisor;

import java.io.IOException;
import java.util.Locale;

public class Menu {

    public static void menu() {

        Authentication authentication = new Authentication(false);
        try {
        String[] commands = CommandReader.readCommand();

        Command command = Command.valueOf(commands[0].toUpperCase(Locale.ROOT));

        Task task = new AuthTaskDecorator(new MenuTask());

        while (!command.equals(Command.EXIT)) {

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

                    case AUTH:
                        task = new MenuTask();
                        task.authenticate();
                        break;

                    default:
                        System.out.println("Unknown command");
                }



            commands = CommandReader.readCommand();

            command = Command.valueOf(commands[0].toUpperCase(Locale.ROOT));
        }

        } catch (IllegalArgumentException | IOException | InterruptedException exception) {
            System.out.println("Unknown command");
        }

    }

}
