package advisor;

import java.io.IOException;

public class MenuTask implements Task {

    private final JsonResponseHandler jsonResponseHandler = JsonResponseHandler.getInstance();



    @Override
    public void handleNewReleases() {



        try {
            jsonResponseHandler.getNewReleasesFromResponseString(
                    Server.getInfoFromSpotifyApi(
                            ConnectionConfigurator.getApiServerPointUrl()
                                    + ConnectionConfigurator.getNewReleasesPath()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void handleFeatured() {

        try {
            jsonResponseHandler
                    .getFeaturedPlaylistFromResponseString(
                            Server.getInfoFromSpotifyApi(
                                    ConnectionConfigurator.getApiServerPointUrl()
                                            + ConnectionConfigurator.getFeaturedPath()));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleCategories() throws IOException, InterruptedException {

        jsonResponseHandler.getCategoriesFromResponseString(Server.getInfoFromSpotifyApi(
                ConnectionConfigurator.getApiServerPointUrl()
                        + ConnectionConfigurator.getCategoriesPath()), true);
    }

    @Override
    public void handlePlaylists(String categoryName) {

        if (jsonResponseHandler.getCategories().isEmpty()) {

            try {
                jsonResponseHandler.getCategoriesFromResponseString(Server.getInfoFromSpotifyApi(
                        ConnectionConfigurator.getApiServerPointUrl()
                                + ConnectionConfigurator.getCategoriesPath()), false);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }

        String categoryId = jsonResponseHandler.getCategoryIdByName(categoryName);

        if (categoryId.equals("Unknown category name.")) {
            System.out.println(categoryId);
        } else {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(ConnectionConfigurator.getPlaylistPath());

            stringBuilder.insert(22, categoryId);

            try {
                jsonResponseHandler.getPlaylistFromResponseString(
                        Server.getInfoFromSpotifyApi(
                                ConnectionConfigurator.getApiServerPointUrl()
                                        + stringBuilder.toString()));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void authenticate() throws IOException, InterruptedException {

        Server.runServer();

    }
}
