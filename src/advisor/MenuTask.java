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
        System.out.println("Mellow Morning");
        System.out.println("Wake Up and Smell the Coffee");
        System.out.println("Monday Motivation");
        System.out.println("Songs to Sing in the Shower");
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

        String link = "https://accounts.spotify.com/authorize?client_id=9955b8ae7d0942328db1966b8ccd0272&redirect_uri=http://localhost:8080&response_type=code";

        Server.runServer();

        Server.sendRequest(link);
        System.out.println(link);

        Thread.sleep(2000);

        System.out.println("---SUCCESS---");
    }
}
