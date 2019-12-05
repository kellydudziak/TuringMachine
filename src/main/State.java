package main;

/**
 *
 */
public class State {
    private final boolean isInitial;
    private final boolean isFinal;
    private final String stateName;
    private State currentState;

    public State(boolean initial, boolean finalState, String name) {
        isInitial = initial; // use separate list of final and initial states to go through and set q[0].isInitial = true etc
        isFinal = finalState;
        stateName = name;
    }

    public boolean getIsInitial() {
        return isInitial;
    }

    public boolean getIsFinal() {
        return isFinal;
    }

    public String getStateName() {
        return stateName;
    }

//    public void goToState(State nextState) {
//        currentState = nextState;
//    }
//
//    public State getCurrentState() {
//        return currentState;
//    }

}
