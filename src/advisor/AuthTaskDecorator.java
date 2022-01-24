package advisor;

public class AuthTaskDecorator extends TaskDecorator{

    public AuthTaskDecorator(Task task) {
        super(task);
    }

    private void returnInformationToLogin(){
        System.out.println("Please, provide access for application.");
    }

    @Override
    public void handleNewReleases() {
        returnInformationToLogin();
    }

    @Override
    public void handleFeatured() {
        returnInformationToLogin();
    }

    @Override
    public void handleCategories() {
        returnInformationToLogin();
    }

    @Override
    public void handlePlaylists(String playlistName) {
        returnInformationToLogin();
    }

    @Override
    public void authenticate() {
        returnInformationToLogin();
    }
}
