package movehandlers;

import java.util.Objects;

public abstract class MoveHandler implements IMoveHandler{
    protected IMoveHandler nextMoveHandler;
    @Override
    public void setNext(IMoveHandler nextMoveHandler) {
        if (nextMoveHandler == null)
            throw new IllegalArgumentException();
        this.nextMoveHandler = nextMoveHandler;
    }
}
