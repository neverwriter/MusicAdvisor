package advisor.content;

import advisor.ConnectionConfigurator;

import java.util.ArrayList;

public class Content<T> {

    private ArrayList<T> contentList = new ArrayList<>();

    private final int numberOfItemOnPage;

    private int numberOfCurrentPage;

    private final int maxPageNumber;

    public Content(ArrayList<T> contentList) {
        this.contentList = contentList;
        this.numberOfItemOnPage = ConnectionConfigurator.getNumberOfItemOnPage();
        this.numberOfCurrentPage = 1;
        this.maxPageNumber = calcMaxPageNumber(contentList, ConnectionConfigurator.getNumberOfItemOnPage());
    }


    private int calcMaxPageNumber(ArrayList<T> contentList, int numberOfItemOnPage){

        if(contentList.size() % numberOfItemOnPage != 0){
          return contentList.size() / numberOfItemOnPage + 1;
        } else {
          return contentList.size() / numberOfItemOnPage;
        }
    }

    public ArrayList<T> getContentList() {
        return contentList;
    }

    public int getNumberOfItemOnPage() {
        return numberOfItemOnPage;
    }

    public int getNumberOfCurrentPage() {
        return numberOfCurrentPage;
    }

    public int getMaxPageNumber() {
        return maxPageNumber;
    }

    public void setNumberOfCurrentPage(int numberOfCurrentPage) {
        this.numberOfCurrentPage = numberOfCurrentPage;
    }
}
