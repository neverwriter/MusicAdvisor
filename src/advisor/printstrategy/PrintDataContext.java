package advisor.printstrategy;

public class PrintDataContext {

    private PrintDataStrategy printDataStrategy;

    public void setStrategy(PrintDataStrategy printDataStrategy) {
        this.printDataStrategy = printDataStrategy;
    }

    public void executeStrategy(int pageModificator) {
        printDataStrategy.execute(pageModificator);
    }
}
