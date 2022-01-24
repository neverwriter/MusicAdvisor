package advisor;

public interface Task {

    void handleNewReleases();

    void handleFeatured();

    void handleCategories();

    void handlePlaylists(String playlistName);

    void authenticate();

}
