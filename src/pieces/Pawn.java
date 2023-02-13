package pieces;

import board.Move;
import board.Board;
import board.Spot;
import common.PieceColor;

public class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super("Pawn", color);
    }

    @Override
    public boolean isMoveValid(Move move) {
        Board board = Board.getBoard();
        if (!super.isMoveValid(move))
            return false;

        Spot startSpot = move.getStartSpot();
        Spot endSpot = move.getEndSpot();
        int xDistance = endSpot.getX() - startSpot.getX();
        int yDistance = endSpot.getY() - startSpot.getY();

        int side;
        if (board.getPiece(startSpot).getColor().equals(PieceColor.WHITE))
            side = -1;
        else side = 1;
        if (xDistance == side && yDistance == 0 && board.isSpotEmpty(endSpot))
            return true;
        if (xDistance == 2 * side && yDistance == 0 && board.isSpotEmpty(new Spot(startSpot.getX() + side, startSpot.getY()))
                && !board.getPiece(startSpot).hasBeenMoved())
            return true;
        if (xDistance == side && Math.abs(yDistance) == 1 && !board.isSpotEmpty(endSpot))
            return true;

        return false;
    }

}
