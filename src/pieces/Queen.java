package pieces;

import board.Move;
import board.Board;
import board.Spot;
import common.PieceColor;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        super("Queen", color);
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
            } else //diagonal movement check
                if (Math.abs(xDistance) == Math.abs(yDistance) && xDistance != 0) {
                    int verticalDirection = xDistance > 0 ? 1 : -1;
                    int horizontalDirection = yDistance > 0 ? 1 : -1;

                    for (int i = 1; i < Math.abs(xDistance); i++) {
                        int x = startSpot.getX() + i * verticalDirection;
                        int y = startSpot.getY() + i * horizontalDirection;
                        if (!board.isSpotEmpty(new Spot(x, y)))
                            canMove = false;
                    }
                } else
                    canMove = false;

        return canMove;
    }
}
