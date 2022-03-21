package advisor.content.newrelease;

import java.util.List;
import java.util.Map;

public class NewRelease {
    String name;
    List<Map<String, Object>> artists;
    Map<String, Object> external_urls;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<String, Object>> getArtists() {
        return artists;
    }

    public void setArtists(List<Map<String, Object>> artists) {
        this.artists = artists;
    }

    public Map<String, Object> getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(Map<String, Object> external_urls) {
        this.external_urls = external_urls;
    }
}
