package board;


public class Move {
    private final Spot startSpot;
    private final Spot endSpot;

    public Move(Spot startSpot, Spot endSpot) {
        this.startSpot = startSpot;
        this.endSpot = endSpot;
    }

    public Spot getStartSpot() {
        return startSpot;
    }

    public Spot getEndSpot() {
        return endSpot;
    }

}
