package movehandlers;

import board.*;
import common.*;

public interface IMoveHandler {
    void setNext(IMoveHandler nextMoveHandler);

    boolean handleMove(PiecesMetadata metadata, Move move);
}
