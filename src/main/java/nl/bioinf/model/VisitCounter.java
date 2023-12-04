package nl.bioinf.model;

public class VisitCounter {
    private int counter;

    public VisitCounter() {
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void visit() {
        this.counter++;
    }
}
