package advisor.printstrategy;

import advisor.JsonResponseHandler;
import advisor.TextPrinter;
import advisor.content.Content;
import advisor.content.categories.Category;

import java.util.ArrayList;

public class PrintDataCategoryStrategy implements PrintDataStrategy {

    private final JsonResponseHandler jsonResponseHandler = JsonResponseHandler.getInstance();

    private final ArrayList<Category> categories = jsonResponseHandler.getCategories();

    private final Content<Category> categoryContent = new Content<>(categories);

    @Override
    public void execute(int pageModificator) {


        if (pageModificator == -1) {
            if (categoryContent.getNumberOfCurrentPage() <= 1) {

                TextPrinter.printNoMorePages();
                return;

            }
        } else if (pageModificator == 1) {
            if (categoryContent.getNumberOfCurrentPage() >= categoryContent.getMaxPageNumber()) {

                TextPrinter.printNoMorePages();
                return;

            }
        }

        categoryContent.setNumberOfCurrentPage(categoryContent.getNumberOfCurrentPage() + pageModificator);

        for (int i = categoryContent.getNumberOfItemOnPage()
                * categoryContent.getNumberOfCurrentPage()
                - categoryContent.getNumberOfItemOnPage();
             i < categoryContent.getNumberOfItemOnPage() * categoryContent.getNumberOfCurrentPage();
             i++) {

            System.out.println(categoryContent.getContentList().get(i).getName());

        }

        TextPrinter.printPageNumber(categoryContent.getNumberOfCurrentPage(), categoryContent.getMaxPageNumber());

    }
}
