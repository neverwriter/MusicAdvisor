package advisor;

import java.io.IOException;

public interface Task {

    void handleNewReleases();

    void handleFeatured();

    void handleCategories();

    void handlePlaylists(String playlistName);

    void authenticate() throws IOException, InterruptedException;

}
