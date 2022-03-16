package advisor.printstrategy;

import advisor.JsonResponseHandler;
import advisor.newrelease.NewRelease;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintDataNewReleaseStrategy implements PrintDataStrategy {

    private final JsonResponseHandler jsonResponseHandler = JsonResponseHandler.getInstance();

    @Override
    public void execute() {

        ArrayList<NewRelease> newReleases = jsonResponseHandler.getNewReleases();

        for (NewRelease newRelease : newReleases
        ) {
            System.out.println(newRelease.getName());
            System.out.print("[");

            List<Map<String, Object>> artists = newRelease.getArtists();

            System.out.print(artists.get(0).get("name").toString());

            if (artists.size() > 1) {

                for (int i = 1; i < artists.size(); i++) {
                    System.out.print(", " + artists.get(i).get("name").toString());
                }
            }
            System.out.print("]\n");
            System.out.println(newRelease.getExternal_urls().get("spotify").toString() + "\n");
        }

    }
}
