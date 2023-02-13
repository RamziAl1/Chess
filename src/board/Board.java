package board;

import common.PieceColor;
import pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Board {

    private static final Board board = new Board();
    private final Piece[][] pieceHolders;
    private final int height;
    private final int width;
    private final List<Piece> capturedPieces;
    String[][] configuration;

    private Board() {
        this.height = 8;
        this.width = 8;
        pieceHolders = new Piece[8][8];
        this.configuration = new String[][]{{"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"},
                {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"}};
        capturedPieces = new ArrayList<>();
        initializeBoard();
    }

    public static Board getBoard() {
        return board;
    }

    private void initializeBoard() {
        PieceFactory fac = new PieceFactory();

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                pieceHolders[i][j] = null;

        for (int i = 0; i < configuration.length; i++)
            for (int j = 0; j < configuration[0].length; j++)
                setPiece(fac.createPiece(configuration[i][j], PieceColor.WHITE), new Spot(i + height - configuration.length, j));

        for (int i = 0; i < configuration.length; i++)
            for (int j = 0; j < configuration[0].length; j++)
                setPiece(fac.createPiece(configuration[i][j], PieceColor.BLACK), new Spot(configuration.length - 1 - i, j));
    }

    public void movePiece(Move move) {
        if (isOutOfRange(move))
            throw new IndexOutOfBoundsException();
        Piece tempPiece = getPiece(move.getStartSpot());
        if (tempPiece == null)
            throw new NoSuchElementException();
        tempPiece.pieceGotMoved();
        if (!isSpotEmpty(move.getEndSpot()))
            capturePiece(move.getEndSpot());

        setPiece(tempPiece, move.getEndSpot());
        resetTile(move.getStartSpot());
    }

    public boolean isOutOfRange(Move move) {
        return isOutOfRange(move.getStartSpot().getX(), move.getStartSpot().getY())
                || isOutOfRange(move.getEndSpot().getX(), move.getEndSpot().getY());
    }

    public boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= height || y < 0 || y >= width;
    }

    public boolean isSpotEmpty(Spot spot) {
        return pieceHolders[spot.getX()][spot.getY()] == null;
    }

    public void setPiece(Piece piece, Spot spot) {
        if (isOutOfRange(spot.getX(), spot.getY()))
            throw new IndexOutOfBoundsException();
        pieceHolders[spot.getX()][spot.getY()] = piece;
    }

    public Piece getPiece(Spot spot) {
        if (isOutOfRange(spot.getX(), spot.getY()))
            throw new IndexOutOfBoundsException();
        return pieceHolders[spot.getX()][spot.getY()];
    }

    public void capturePiece(Spot spot) {
        capturedPieces.add(getPiece(spot));
    }

    public void unCapturePiece(Piece piece) {
        capturedPieces.remove(piece);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Piece[][] getPieceHoldersCopy() {
        PieceFactory fac = new PieceFactory();
        Piece[][] newBoard = new Piece[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Piece tmpPiece = pieceHolders[i][j];
                if (tmpPiece != null)
                    newBoard[i][j] = fac.createPiece(tmpPiece.getSymbol(), tmpPiece.getColor());
                else
                    newBoard[i][j] = null;
            }
        }
        return newBoard;
    }

    public String[][] getConfiguration() {
        return configuration;
    }

    public List<Piece> getCapturedPieces() {
        return capturedPieces;
    }

    public void resetTile(Spot spot) {
        pieceHolders[spot.getX()][spot.getY()] = null;
    }

    public void viewBoard() {
        System.out.print(' ');
        for (int j = 0; j < width; j++)
            System.out.print("\t" + (char) (j + 'a') + " \t\t");
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print(8 - i);
            for (int j = 0; j < width; j++)
                System.out.print("\t" + getPieceDisplaySymbol(new Spot(i, j)) + "\t");
            System.out.print(' ');
            System.out.println();
        }
    }

    private String getPieceDisplaySymbol(Spot spot) {
        Piece piece = getPiece(spot);
        if (piece != null) {
            String firstChar;
            if (piece.getColor() == PieceColor.WHITE)
                firstChar = "w";
            else
                firstChar = "b";
            return firstChar + getPiece(spot).getSymbol();
        }
        return ". \t";
    }
}
