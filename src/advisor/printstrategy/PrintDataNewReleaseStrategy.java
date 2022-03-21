package advisor.printstrategy;

import advisor.JsonResponseHandler;
import advisor.TextPrinter;
import advisor.content.Content;
import advisor.content.newrelease.NewRelease;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrintDataNewReleaseStrategy implements PrintDataStrategy {

    private final JsonResponseHandler jsonResponseHandler = JsonResponseHandler.getInstance();

    private final ArrayList<NewRelease> newReleases = jsonResponseHandler.getNewReleases();

    private final Content<NewRelease> newReleaseContent = new Content<>(newReleases);

    @Override
    public void execute(int pageModificator) {


        if (pageModificator == -1) {
            if (newReleaseContent.getNumberOfCurrentPage() <= 1) {

                TextPrinter.printNoMorePages();
                return;

            }
        } else if (pageModificator == 1) {
            if (newReleaseContent.getNumberOfCurrentPage() >= newReleaseContent.getMaxPageNumber()) {

                TextPrinter.printNoMorePages();
                return;

            }
        }

        newReleaseContent.setNumberOfCurrentPage(newReleaseContent.getNumberOfCurrentPage() + pageModificator);

        for (int i = newReleaseContent.getNumberOfItemOnPage()
                * newReleaseContent.getNumberOfCurrentPage()
                - newReleaseContent.getNumberOfItemOnPage();
             i < newReleaseContent.getNumberOfItemOnPage() * newReleaseContent.getNumberOfCurrentPage();
             i++) {
            System.out.println(newReleaseContent.getContentList().get(i).getName());
            System.out.print("[");

            List<Map<String, Object>> artists = newReleaseContent.getContentList().get(i).getArtists();

            System.out.print(artists.get(0).get("name").toString());

            if (artists.size() > 1) {

                for (int j = 1; j < artists.size(); j++) {
                    System.out.print(", " + artists.get(j).get("name").toString());
                }
            }
            System.out.print("]\n");
            System.out.println(newReleaseContent.getContentList().get(i).getExternal_urls().get("spotify").toString() + "\n");


        }

        TextPrinter.printPageNumber(newReleaseContent.getNumberOfCurrentPage(), newReleaseContent.getMaxPageNumber());

    }
}
