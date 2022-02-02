package advisor;

import java.io.IOException;

public interface Task {

    void handleNewReleases();

    void handleFeatured();

    void handleCategories() throws IOException, InterruptedException;

    void handlePlaylists(String playlistName);

    void authenticate() throws IOException, InterruptedException;

}
