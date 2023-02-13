package gamestate;

import common.*;

import java.util.List;

public class GameStateChecker implements StateChecker {
    private final List<GameStateCheck> checks;

    public GameStateChecker(List<GameStateCheck> checks) {
        this.checks = checks;
    }

    @Override
    public StateInfo checkState(PiecesMetadata metadata, PieceColor playerColor) {
        StateInfo highestPriorityState = new StateInfo(-1, "");
        for (GameStateCheck gs : checks) {
            StateInfo currentState = gs.getState(metadata, playerColor);
            if (currentState.getStateCode() > highestPriorityState.getStateCode())
                highestPriorityState = currentState;
        }

        return highestPriorityState;
    }

    @Override
    public StateInfo checkIllegalStates(PiecesMetadata metadata, PieceColor playerColor) {
        StateInfo highestPriorityState = new StateInfo(-1, "");
        for (GameStateCheck gs : checks) {
            if (gs.isIllegalForCurrentPlayer()) {
                StateInfo currentState = gs.getState(metadata, playerColor);
                if (currentState.getStateCode() > highestPriorityState.getStateCode())
                    highestPriorityState = currentState;
            }
        }
        return highestPriorityState;
    }
}
