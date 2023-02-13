package movehandlers;

import board.*;
import common.*;
import pieces.*;

public class CastlingMoveHandler extends MoveHandler {
    @Override
    public boolean handleMove(PiecesMetadata metadata, Move move) {
        if (isCastlingMove(metadata, move)) {
            Board board = Board.getBoard();

            int x = move.getStartSpot().getX();
            int y = move.getStartSpot().getY();
            int yDistance = move.getStartSpot().getY() - move.getEndSpot().getY();
            int direction = yDistance < 0 ? 1 : -1;
            Move rookMove = new Move(move.getEndSpot(), new Spot(x, y + direction));
            Move kingMove = new Move(move.getStartSpot(), new Spot(x, y + 3 * direction));
            board.movePiece(rookMove);
            board.movePiece(kingMove);

            return true;
        }
        if (nextMoveHandler != null)
            return nextMoveHandler.handleMove(metadata, move);
        else
            return false;
    }

    private boolean isCastlingMove(PiecesMetadata metadata, Move move) {
        Board board = Board.getBoard();

        Spot startSpot = move.getStartSpot();
        Spot endSpot = move.getEndSpot();
        Piece piece1 = board.getPiece(startSpot);
        Piece piece2 = board.getPiece(endSpot);

        if (!(piece1 instanceof King) || !(piece2 instanceof Rook)
                || piece1.hasBeenMoved() || piece2.hasBeenMoved()
                || !piece1.getColor().equals(piece2.getColor())
        )
            return false;

        int yDistance = endSpot.getY() - startSpot.getY();
        int direction = yDistance > 0 ? 1 : -1;

        for (int i = 0; i < 4; i++) {
            int x = startSpot.getX();
            int y = startSpot.getY() + i * direction;
            if (metadata.isSpotThreatened(piece1.getColor(), new Spot(x, y))
                    || (!board.isSpotEmpty(new Spot(x, y)) && y != startSpot.getY() && y != endSpot.getY()))
                return false;
        }
        return true;
    }
}
