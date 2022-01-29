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

public class Server {

    public static void runServer() throws IOException {
        HttpServer server = HttpServer.create();

        server.bind(new InetSocketAddress(8080), 0);

        server.createContext("/",
                new HttpHandler() {
                    public void handle(HttpExchange exchange) throws IOException {
                        String hello = "<html><head></head><body><h1>Hello, API<h1/></body></html>";
                        String code = exchange.getRequestURI().getQuery();
                        exchange.sendResponseHeaders(200, code.length());
                        exchange.getResponseBody().write(code.getBytes());
                        exchange.getResponseBody().close();
                    }
                }
        );

        server.start();
    }

    public static String sendRequest(String link) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(link))
//                .POST(HttpRequest.BodyPublishers.ofString("{\"client_id\": \"9955b8ae7d0942328db1966b8ccd0272\"," +
//                        "\"redirect_uri\":\"http://localhost:8080\"" +
//                        "\"response_type\":\"code\"}"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.uri());
        System.out.println(response.headers());
        System.out.println(response.body());

        return response.body();

    }

}
