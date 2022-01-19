package advisor;

import java.util.Locale;

public class Menu {

    public static void menu() {

        String[] commands = CommandReader.readCommand();

        Command command = Command.valueOf(commands[0].toUpperCase(Locale.ROOT));

        while (!command.equals(Command.EXIT)) {

            switch (command) {
                case NEW:
                    handleNewReleases();
                    break;

                case FEATURED:
                    handleFeatured();
                    break;

                case CATEGORIES:
                    handleCategories();
                    break;

                case PLAYLISTS:
                    handlePlaylists(commands[1]);
                    break;

                default:
                    System.out.println("Unknown command");
            }

            commands = CommandReader.readCommand();

            command = Command.valueOf(commands[0].toUpperCase(Locale.ROOT));
        }

    }

    private static void handleNewReleases() {
        System.out.println("---NEW RELEASES---");
        System.out.println("Mountains [Sia, Diplo, Labrinth]");
        System.out.println("Runaway [Lil Peep]");
        System.out.println("The Greatest Show [Panic! At The Disco]");
        System.out.println("All Out Life [Slipknot]");
    }

    private static void handleFeatured() {
        System.out.println("---FEATURED---");
        System.out.println("Mellow Morning");
        System.out.println("Wake Up and Smell the Coffee");
        System.out.println("Monday Motivation");
        System.out.println("Songs to Sing in the Shower");
    }

    private static void handleCategories() {
        System.out.println("---CATEGORIES---");
        System.out.println("Top Lists");
        System.out.println("Pop");
        System.out.println("Mood");
        System.out.println("Latin");
    }

    private static void handlePlaylists(String playlistType) {
        System.out.printf("---%s PLAYLISTS---\n", playlistType.toUpperCase(Locale.ROOT));
        System.out.println("Walk Like A Badass");
        System.out.println("Rage Beats");
        System.out.println("Arab Mood Booster");
        System.out.println("Sunday Stroll");
    }

}
