package movehandlers;

import board.*;
import common.*;
import pieces.*;

import java.util.Scanner;

public class PromotionMoveHandler extends MoveHandler {
    @Override
    public boolean handleMove(PiecesMetadata metadata, Move move) {
        if (isPromotionMove(metadata, move)) {
            Board board = Board.getBoard();
            board.movePiece(move);
            Scanner scan = SingletonScanner.getBoard();
            boolean isValidChoice = false;
            while (!isValidChoice) {
                System.out.println("What would you like to promote this piece to: " +
                        "Knight \t Bishop  \t Rook \t Queen");
                String name = scan.next();
                if (!name.equals("Knight") && !name.equals("Bishop") && !name.equals("Rook") && !name.equals("Queen"))
                    continue;
                int x = move.getEndSpot().getX();
                int y = move.getEndSpot().getY();
                PieceColor color = board.getPiece(move.getEndSpot()).getColor();
                Piece tmpPiece = new PieceFactory().createPiece(name, color);
                if (tmpPiece != null) {
                    board.setPiece(tmpPiece, new Spot(x, y));
                    isValidChoice = true;
                } else System.out.println("couldn't get piece");
            }
            return true;
        }
        if (nextMoveHandler != null)
            return nextMoveHandler.handleMove(metadata, move);
        else
            return false;
    }

    private boolean isPromotionMove(PiecesMetadata metadata, Move move) {
        Board board = Board.getBoard();
        if (!metadata.isMoveValid(move))
            return false;
        Spot startSpot = move.getStartSpot();
        Spot endSpot = move.getEndSpot();
        Piece piece = board.getPiece(startSpot);
        if (!(piece instanceof Pawn))
            return false;
        int edge;

        if (piece.getColor().equals(PieceColor.WHITE))
            edge = 0;
        else
            edge = board.getHeight() - 1;

        return endSpot.getX() == edge;
    }
}

