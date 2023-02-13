package gamestate;

import common.*;

public interface StateChecker {

    StateInfo checkState(PiecesMetadata metadata, PieceColor playerColor);
    StateInfo checkIllegalStates(PiecesMetadata metadata, PieceColor playerColor);
}
