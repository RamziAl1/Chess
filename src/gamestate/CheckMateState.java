package gamestate;

import board.*;
import common.*;

public class CheckMateState implements GameStateCheck {

    @Override
    public StateInfo getState(PiecesMetadata metadata, PieceColor playerColor) {
        Board board = Board.getBoard();

        StateInfo stateInfo = new StateInfo(0, "");
        Spot kingSpot = metadata.findKingLocation(playerColor);
        boolean isChecked = metadata.isSpotThreatened(playerColor, kingSpot);
        if (!isChecked)
            return stateInfo;

        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                Spot next = new Spot(kingSpot.getX() + i, kingSpot.getY() + j);
                if (metadata.isMoveValid(new Move(kingSpot, next))
                        && !metadata.isSpotThreatened(playerColor, next)) {
                    return stateInfo;
                }
            }
        stateInfo.setStateDescription("CHECKMATE\n" + playerColor + " loses");
        stateInfo.setStateCode(2);

        return stateInfo;
    }

    @Override
    public boolean isIllegalForCurrentPlayer() {
        return false;
    }
}
