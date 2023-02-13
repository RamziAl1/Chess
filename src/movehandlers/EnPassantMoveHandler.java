package movehandlers;

import board.*;
import common.*;
import pieces.*;

public class EnPassantMoveHandler extends MoveHandler {
    @Override
    public boolean handleMove(PiecesMetadata metadata, Move move) {
        if (isEnPassantMove(move)) {
            Board board = Board.getBoard();
            board.movePiece(move);
            int x = move.getStartSpot().getX();
            int y = move.getEndSpot().getY();
            board.resetTile(new Spot(x, y));

            return true;
        }
        if (nextMoveHandler != null)
            return nextMoveHandler.handleMove(metadata, move);
        else
            return false;
    }

    public boolean isEnPassantMove(Move move) {
        Board board = Board.getBoard();
        Spot startSpot = move.getStartSpot();
        Spot endSpot = move.getEndSpot();
        if (board.isOutOfRange(move) || !(board.getPiece(startSpot) instanceof Pawn))
            return false;
        Piece pawn = board.getPiece(startSpot);

        int xDistance = endSpot.getX() - startSpot.getX();
        int yDistance = endSpot.getY() - startSpot.getY();

        int side;
        if (pawn.getColor().equals(PieceColor.WHITE))
            side = -1;
        else side = 1;
        //check diagonal movement
        if (xDistance != side || Math.abs(yDistance) != 1 || !board.isSpotEmpty(endSpot))
            return false;
        //check if move is in range
        if (board.isOutOfRange(startSpot.getX(), endSpot.getY()))
            return false;
        Piece enemyPawn = board.getPiece(new Spot(startSpot.getX(), endSpot.getY()));
        if (!(enemyPawn instanceof Pawn) || pawn.isAllyPiece(enemyPawn) || enemyPawn.getMovedAmount() != 1)
            return false;

        return true;
    }
}
