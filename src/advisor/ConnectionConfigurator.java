package advisor;

public class ConnectionConfigurator {

    private static String accessServerPointUrl;

    private final static String CLIENT_ID = "9955b8ae7d0942328db1966b8ccd0272";

    private final static String CLIENT_SECRET = "936a41d043484f53a7292ef86116f428";

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
}
