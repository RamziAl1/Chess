package pieces;

import board.Move;
import board.Board;
import board.Spot;
import common.PieceColor;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super("Rook", color);
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

        boolean canMove = true;
        //vertical movement check
        if (xDistance != 0 && yDistance == 0) {
            int direction = xDistance > 0 ? 1 : -1;
            for (int i = 1; i < Math.abs(xDistance); i++)
                if (!board.isSpotEmpty(new Spot(startSpot.getX() + i * direction, startSpot.getY())))
                    canMove = false;
        } else //horizontal movement check
            if (xDistance == 0 && yDistance != 0) {
                int direction = yDistance > 0 ? 1 : -1;
                for (int i = 1; i < Math.abs(yDistance); i++)
                    if (!board.isSpotEmpty(new Spot(startSpot.getX(), startSpot.getY() + i * direction)))
                        canMove = false;
            } else
                canMove = false;

        return canMove;
    }
}
