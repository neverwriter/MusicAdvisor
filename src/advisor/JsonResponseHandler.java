package advisor;

import advisor.content.categories.Category;
import advisor.content.newrelease.NewRelease;
import advisor.content.playlists.Playlist;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Objects;

public class JsonResponseHandler {

    private static JsonResponseHandler instance;

    private final ArrayList<Playlist> featuredPlaylists = new ArrayList<>();

    private final ArrayList<Playlist> playlists = new ArrayList<>();

    private final ArrayList<Category> categories = new ArrayList<>();

    private final ArrayList<NewRelease> newReleases = new ArrayList<>();

    private JsonResponseHandler() {
    }

    public static JsonResponseHandler getInstance() {
        if (instance == null) {
            instance = new JsonResponseHandler();
        }
        return instance;
    }

    public void getFeaturedPlaylistFromResponseString(String responseFromSpotify) throws JsonProcessingException {

        JsonElement jsonElement = JsonParser.parseString(responseFromSpotify);

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray jsonArray = jsonObject.get("playlists").getAsJsonObject().get("items").getAsJsonArray();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        featuredPlaylists.clear();

        jsonArray.forEach(jsonElement1 -> {
            try {
                Playlist playlist = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), Playlist.class);
                featuredPlaylists.add(playlist);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });

    }

    public void getCategoriesFromResponseString(String responseFromSpotify) {

        JsonElement jsonElement = JsonParser.parseString(responseFromSpotify);

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray jsonArray = jsonObject.get("categories").getAsJsonObject().get("items").getAsJsonArray();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        categories.clear();

        jsonArray.forEach(jsonElement1 -> {
            try {
                Category category = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), Category.class);
                categories.add(category);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });


    }

    public void getNewReleasesFromResponseString(String responseFromSpotify) {

        JsonElement jsonElement = JsonParser.parseString(responseFromSpotify);

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray jsonArray = jsonObject.get("albums").getAsJsonObject().get("items").getAsJsonArray();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        newReleases.clear();

        jsonArray.forEach(jsonElement1 -> {
            try {
                NewRelease newRelease = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), NewRelease.class);
                newReleases.add(newRelease);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        });



    }

    public void getPlaylistFromResponseString(String responseFromSpotify) throws JsonProcessingException {

        if (!responseFromSpotify.equals("error")) {

            JsonElement jsonElement = JsonParser.parseString(responseFromSpotify);

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            JsonArray jsonArray = jsonObject.get("playlists").getAsJsonObject().get("items").getAsJsonArray();

            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            playlists.clear();

            jsonArray.forEach(jsonElement1 -> {
                try {
                    Playlist playlist = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), Playlist.class);
                    playlists.add(playlist);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            });


        }
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public String getCategoryIdByName(String categoryName) {
        String categoryId = null;

        for (Category category : categories) {

            if (category.getName().equals(categoryName)) {
                categoryId = category.getId();
            }

        }

        return Objects.requireNonNullElse(categoryId, "Unknown category name.");

    }

    public Error handleErrorMassage(String responseFromSpotify) {

        JsonObject jsonObject = JsonParser.parseString(responseFromSpotify).getAsJsonObject();

        JsonObject errorObject = jsonObject.getAsJsonObject("error");


        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Error error = null;

        try {
            error = objectMapper.readValue(errorObject.toString(), Error.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return error;

    }

    public ArrayList<Playlist> getFeaturedPlaylists() {
        return featuredPlaylists;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<NewRelease> getNewReleases() {
        return newReleases;
    }
}
