package advisor;

public class TextPrinter {
    private final static String GOODBYE = "---GOODBYE!---";
    private final static String SUCCESS = "---SUCCESS---";

    public static void printGoodbye(){
        System.out.println(GOODBYE);
    }

    public static void printSuccess(){
        System.out.println(SUCCESS);
    }

    public static void printPageNumber(int currentPageNumber, int maxPageNumber){
        System.out.printf("---PAGE %d OF %d---\n", currentPageNumber, maxPageNumber);
    }

    public static void printNoMorePages(){
        System.out.println("No more pages.");
    }
}
