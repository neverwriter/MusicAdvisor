package advisor;

import java.io.IOException;
import java.util.Locale;

public class MenuTask implements Task {

    @Override
    public void handleNewReleases() {
        System.out.println("---NEW RELEASES---");
        System.out.println("Mountains [Sia, Diplo, Labrinth]");
        System.out.println("Runaway [Lil Peep]");
        System.out.println("The Greatest Show [Panic! At The Disco]");
        System.out.println("All Out Life [Slipknot]");
    }

    @Override
    public void handleFeatured() {
        System.out.println("---FEATURED---");
        try {
            JsonResponseHandler jsonResponseHandler = new JsonResponseHandler();

            jsonResponseHandler
                    .getFeaturedPlaylistFromResponseString(
                            Server.getInfoFromSpotifyApi(
                                    ConnectionConfigurator.getApiServerPointUrl()
                                    +ConnectionConfigurator.getFeaturedPath()));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleCategories() throws IOException, InterruptedException {
        System.out.println("---CATEGORIES---");
        JsonResponseHandler jsonResponseHandler = new JsonResponseHandler();
        jsonResponseHandler.getCategoriesFromResponseString(Server.getInfoFromSpotifyApi(
                ConnectionConfigurator.getApiServerPointUrl()
                        +ConnectionConfigurator.getCategoriesPath()));
    }

    @Override
    public void handlePlaylists(String playlistName) {
        System.out.printf("---%s PLAYLISTS---\n", playlistName.toUpperCase(Locale.ROOT));
        System.out.println("Walk Like A Badass");
        System.out.println("Rage Beats");
        System.out.println("Arab Mood Booster");
        System.out.println("Sunday Stroll");
    }

    @Override
    public void authenticate() throws IOException, InterruptedException {

        Server.runServer();

    }
}
