package advisor;

/**
 * <h3>Music Advisor Program<h3/>
 * Main functionality is to give advice on which music to pick<br>
 * using connection to Spotify API
 *
 * @author Patryk Lewczuk<br>
 */

public class Main {

    private final static String DEFAULT_ACCESS_SERVER_POINT_URL = "https://accounts.spotify.com";

    private final static String DEFAULT_API_SERVER_POINT_URL = "https://api.spotify.com";

    public static void main(String[] args) {

        argumentsHandling(args);

        Menu.menu();

        TextPrinter.printGoodbye();

    }

    private static void argumentsHandling(String[] args) {

        if (args.length >= 2) {
            for (int i = 0; i < args.length; i++) {

                if (args[i].equals("-access")) {
                    ConnectionConfigurator.setAccessServerPointUrl(args[i + 1]);
                }

                if (args[i].equals("-resource")) {
                    ConnectionConfigurator.setApiServerPointUrl(args[i + 1]);
                }

            }
        } else {
            ConnectionConfigurator.setAccessServerPointUrl(DEFAULT_ACCESS_SERVER_POINT_URL);
            ConnectionConfigurator.setApiServerPointUrl(DEFAULT_API_SERVER_POINT_URL);
        }

    }
}
