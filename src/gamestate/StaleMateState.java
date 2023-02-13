package gamestate;

import board.Board;
import board.Spot;
import common.*;
import pieces.Piece;


public class StaleMateState implements GameStateCheck {
    @Override
    public StateInfo getState(PiecesMetadata metadata, PieceColor playerColor) {
        Board board = Board.getBoard();

        StateInfo stateInfo = new StateInfo(0, "");
        Spot kingSpot = metadata.findKingLocation(playerColor);
        boolean isChecked = metadata.isSpotThreatened(playerColor, kingSpot);
        if (isChecked)
            return stateInfo;

        for (int i = 0; i < board.getHeight(); i++)
            for (int j = 0; j < board.getWidth(); j++) {
                Piece piece = board.getPiece(new Spot(i, j));
                if (piece != null && piece.getColor().equals(playerColor) && metadata.canPieceMove(new Spot(i, j), playerColor)) {
                    return stateInfo;
                }
            }
        stateInfo.setStateDescription("STALEMATE\nDRAW");
        stateInfo.setStateCode(2);
        return stateInfo;
    }

    @Override
    public boolean isIllegalForCurrentPlayer() {
        return false;
    }
}
