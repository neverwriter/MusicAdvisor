package advisor.content.playlists;

import java.util.Map;

public class Playlist {

    Map<String, Object> external_urls;
    String name;

    public Map<String, Object> getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(Map<String, Object> external_urls) {
        this.external_urls = external_urls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
