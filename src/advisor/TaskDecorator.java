package advisor;

public class TaskDecorator implements Task {
    private Task task;

    public TaskDecorator(Task task) {
        this.task = task;
    }

    @Override
    public void handleNewReleases() {
        task.handleNewReleases();
    }

    @Override
    public void handleFeatured() {
        task.handleNewReleases();
    }

    @Override
    public void handleCategories() {
        task.handleCategories();
    }

    @Override
    public void handlePlaylists(String playlistName) {
        task.handlePlaylists(playlistName);
    }

    @Override
    public void authenticate() {
        task.authenticate();
    }
}
