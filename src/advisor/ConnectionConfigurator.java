package advisor;

public class ConnectionConfigurator {

    private static String accessServerPointUrl;

    private static String apiServerPointUrl;

    private final static String CLIENT_ID = "9955b8ae7d0942328db1966b8ccd0272";

    private final static String CLIENT_SECRET = "936a41d043484f53a7292ef86116f428";

    private final static String CATEGORIES_PATH = "/v1/browse/categories";

    private final static String PLAYLIST_PATH = "/v1/browse/categories//playlists";

    private final static String NEW_RELEASES_PATH = "/v1/browse/new-releases";

    private final static String FEATURED_PATH = "/v1/browse/featured-playlists";

    public static String getAccessServerPointUrl() {
        return accessServerPointUrl;
    }

    public static void setAccessServerPointUrl(String accessServerPointUrl) {
        ConnectionConfigurator.accessServerPointUrl = accessServerPointUrl;
    }

    public static String getClientId() {
        return CLIENT_ID;
    }

    public static String getClientSecret() {
        return CLIENT_SECRET;
    }

    public static String getApiServerPointUrl() {
        return apiServerPointUrl;
    }

    public static void setApiServerPointUrl(String apiServerPointUrl) {
        ConnectionConfigurator.apiServerPointUrl = apiServerPointUrl;
    }

    public static String getFeaturedPath() {
        return FEATURED_PATH;
    }

    public static String getCategoriesPath() {
        return CATEGORIES_PATH;
    }

    public static String getNewReleasesPath() {
        return NEW_RELEASES_PATH;
    }

    public static String getPlaylistPath() {
        return PLAYLIST_PATH;
    }
}
