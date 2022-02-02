package advisor;

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

    private ArrayList<Playlist> playlists = new ArrayList<>();

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
}
