package advisor;

import advisor.categories.Category;
import advisor.playlists.Playlist;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class JsonResponseHandler {

    private final ArrayList<Playlist> playlists = new ArrayList<>();

    private final ArrayList<Category> categories = new ArrayList<>();

    public JsonResponseHandler() {
    }

    public void getFeaturedPlaylistFromResponseString(String responseFromSpotify) throws JsonProcessingException {

        JsonElement jsonElement = JsonParser.parseString(responseFromSpotify);

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray jsonArray = jsonObject.get("playlists").getAsJsonObject().get("items").getAsJsonArray();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        jsonArray.forEach(jsonElement1 -> {
            try {
                Playlist playlist = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), Playlist.class);
                playlists.add(playlist);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });

        for (Playlist playlist: playlists
             ) {
            System.out.println(playlist.getName());
            System.out.println(playlist.getExternal_urls().get("spotify")+"\n");
        }

    }

    public void getCategoriesFromResponseString(String responseFromSpotify) {

        JsonElement jsonElement = JsonParser.parseString(responseFromSpotify);

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray jsonArray = jsonObject.get("categories").getAsJsonObject().get("items").getAsJsonArray();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        jsonArray.forEach(jsonElement1 -> {
            try {
                Category category = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), Category.class);
                categories.add(category);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });

        for (Category category: categories
        ) {
            System.out.println(category.getName());
        }

    }
}
