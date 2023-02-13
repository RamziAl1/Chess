package gamestate;

public class StateInfo {
    private int stateCode;
    private String stateMessage;

    public StateInfo(int stateCode, String stateMessage) {
        this.stateCode = stateCode;
        this.stateMessage = stateMessage;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateDescription() {
        return stateMessage;
    }

    public void setStateDescription(String stateMessage) {
        this.stateMessage = stateMessage;
    }
}
