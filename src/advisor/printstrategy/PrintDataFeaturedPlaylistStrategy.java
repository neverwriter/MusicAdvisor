package advisor.printstrategy;

import advisor.JsonResponseHandler;
import advisor.TextPrinter;
import advisor.content.Content;
import advisor.content.playlists.Playlist;

import java.util.ArrayList;

public class PrintDataFeaturedPlaylistStrategy implements PrintDataStrategy{

    private final JsonResponseHandler jsonResponseHandler = JsonResponseHandler.getInstance();

    private final ArrayList<Playlist> featuredPlaylists = jsonResponseHandler.getFeaturedPlaylists();

    private final Content<Playlist> playlistContent = new Content<>(featuredPlaylists);

    @Override
    public void execute(int pageModificator) {


        if (pageModificator == -1) {
            if (playlistContent.getNumberOfCurrentPage() <= 1) {

                TextPrinter.printNoMorePages();
                return;

            }
        } else if (pageModificator == 1) {
            if (playlistContent.getNumberOfCurrentPage() >= playlistContent.getMaxPageNumber()) {

                TextPrinter.printNoMorePages();
                return;

            }
        }

        playlistContent.setNumberOfCurrentPage(playlistContent.getNumberOfCurrentPage() + pageModificator);

        for (int i = playlistContent.getNumberOfItemOnPage()
                * playlistContent.getNumberOfCurrentPage()
                - playlistContent.getNumberOfItemOnPage();
             i < playlistContent.getNumberOfItemOnPage() * playlistContent.getNumberOfCurrentPage();
             i++) {

            System.out.println(playlistContent.getContentList().get(i).getName());
            System.out.println(playlistContent.getContentList().get(i).getExternal_urls().get("spotify") + "\n");

        }

        TextPrinter.printPageNumber(playlistContent.getNumberOfCurrentPage(), playlistContent.getMaxPageNumber());

    }
}
