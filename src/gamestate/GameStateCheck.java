package gamestate;

import common.*;

public interface GameStateCheck {

    StateInfo getState(PiecesMetadata metadata, PieceColor playerColor);

    boolean isIllegalForCurrentPlayer();

}
