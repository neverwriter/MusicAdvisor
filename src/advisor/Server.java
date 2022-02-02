package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;


public class Server {
    private static String code = "";

    private static String accessToken;

    public static void runServer() throws IOException, InterruptedException {
        HttpServer server = HttpServer.create();

        server.bind(new InetSocketAddress(8080), 0);

        server.createContext("/",
                new HttpHandler() {
                    boolean got = false;
                    public void handle(HttpExchange exchange) throws IOException {

                        String notFound = "Authorization code not found. Try again.";

                        String succeed = "Got the code. Return back to your program.";

                        String query = exchange.getRequestURI().getQuery();

                        if(query == null) query = "";
                        String response;

                        if (query.contains("code")){
                            got = true;
                            code = query.substring(5);

                        }

                        if (got){
                            response = succeed;
                        } else{
                            response = notFound;
                        }

                        exchange.sendResponseHeaders(200, response.length());
                        exchange.getResponseBody().write(response.getBytes());
                        exchange.getResponseBody().close();
                    }

                }
        );

        server.start();

        String link = ConnectionConfigurator.getAccessServerPointUrl() +
                "/authorize?client_id=9955b8ae7d0942328db1966b8ccd0272&" +
                "redirect_uri=http://localhost:8080&" +
                "response_type=code";


        System.out.println("use this link to request the access code:");
        System.out.println(link);

        sendRequestForAuthCode(link);

        System.out.println("waiting for code...");

        while(code.equals("")){
            Thread.sleep(100);
        }

        System.out.println("code received");

        server.stop(0);

        accessToken = extractAccessToken(getAccessToken(code));

    }

    public static void sendRequestForAuthCode(String link) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(link))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    }

    /**
     * Sends HTTP POST request in purpose to received Access Token
     *
     * @param code String type code received in previous step of authorization. Code is needed to ask for access Token
     * @return respond body containing access token
     * @throws IOException
     * @throws InterruptedException
     */

    private static String getAccessToken(String code) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        String originalInput = ConnectionConfigurator.getClientId() + ":" + ConnectionConfigurator.getClientSecret();
        String encodedData = Base64.getEncoder().encodeToString(originalInput.getBytes());
        String authorizationHeaderString = "Basic " + encodedData;

        HttpRequest request = HttpRequest.newBuilder().headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", authorizationHeaderString)
                .uri(URI.create(ConnectionConfigurator.getAccessServerPointUrl() + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&code=" + code + "&redirect_uri=http://localhost:8080"))
                .build();

        System.out.println("making http request for access_token...");
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Success!");

        return response.body();

    }

    private static String extractAccessToken(String responseFromSpotify){
        String[] strings = responseFromSpotify.split("\"");

        return strings[3];
    }

    static String getInfoFromSpotifyApi(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(uri))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }


}
