package pieces;

import board.Move;
import board.Spot;
import common.PieceColor;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super("Knight", color);

    }

    @Override
    public boolean isMoveValid(Move move) {
        if (!super.isMoveValid(move))
            return false;

        Spot startSpot = move.getStartSpot();
        Spot endSpot = move.getEndSpot();

        int xDistance = endSpot.getX() - startSpot.getX();
        int yDistance = endSpot.getY() - startSpot.getY();

        return Math.abs(xDistance * yDistance) == 2;
    }
}
