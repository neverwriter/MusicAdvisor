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

            jsonResponseHandler.getFeaturedPlaylistFromResponseString(Server.getFeaturedPlaylistInfo());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleCategories() {
        System.out.println("---CATEGORIES---");
        System.out.println("Top Lists");
        System.out.println("Pop");
        System.out.println("Mood");
        System.out.println("Latin");
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
