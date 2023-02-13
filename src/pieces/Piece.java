package pieces;

import board.Move;
import board.Board;
import common.*;

import java.util.Objects;

public abstract class Piece {
    private final String symbol;
    private final PieceColor color;
    private int movedAmount;

    protected Piece(String symbol, PieceColor color) {
        this.symbol = symbol;
        this.color = color;
        movedAmount = 0;
    }

    public boolean isMoveValid(Move move) {
        Board board = Board.getBoard();
        if (board.isOutOfRange(move))
            return false;
        if (board.isSpotEmpty(move.getStartSpot()))
            return false;
        if (move.getStartSpot().equals(move.getEndSpot()))
            return false;

        return true;
    }

    public boolean isAllyPiece(Piece piece) {
        return piece != null && color.equals(piece.getColor());
    }

    public PieceColor getColor() {
        return color;
    }

    public int getMovedAmount() {
        return movedAmount;
    }

    public boolean hasBeenMoved() {
        return movedAmount != 0;
    }

    public void pieceGotMoved() {
        movedAmount++;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return movedAmount == piece.movedAmount && Objects.equals(symbol, piece.symbol) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, color, movedAmount);
    }
}
