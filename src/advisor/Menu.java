package advisor;

import advisor.printstrategy.*;

import java.io.IOException;
import java.util.Locale;

public class Menu {

    private static final PrintDataContext printDataContext = new PrintDataContext();

    private static final int PREV_PAGE_MODIFICATOR = -1;

    private static final int NEUTRAL_PAGE_MODIFICATOR = 0;

    private static final int NEXT_PAGE_MODIFICATOR = 1;

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

                        printDataContext.setStrategy(new PrintDataNewReleaseStrategy());

                        printDataContext.executeStrategy(NEUTRAL_PAGE_MODIFICATOR);
                        break;

                    case FEATURED:
                        task.handleFeatured();

                        printDataContext.setStrategy(new PrintDataFeaturedPlaylistStrategy());

                        printDataContext.executeStrategy(NEUTRAL_PAGE_MODIFICATOR);
                        break;

                    case CATEGORIES:
                        task.handleCategories();

                        printDataContext.setStrategy(new PrintDataCategoryStrategy());

                        printDataContext.executeStrategy(NEUTRAL_PAGE_MODIFICATOR);
                        break;

                    case PLAYLISTS:
                        task.handlePlaylists(commands[1]);

                        printDataContext.setStrategy(new PrintDataPlaylistStrategy());

                        printDataContext.executeStrategy(NEUTRAL_PAGE_MODIFICATOR);
                        break;

                    case AUTH:
                        task = new MenuTask();
                        task.authenticate();
                        break;

                    case NEXT:
                        printDataContext.executeStrategy(NEXT_PAGE_MODIFICATOR);
                        break;

                    case PREV:
                        printDataContext.executeStrategy(PREV_PAGE_MODIFICATOR);
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
