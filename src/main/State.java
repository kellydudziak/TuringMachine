package main;

/**
 * State has a name and can be initial or final.
 */
public class State {
    private final boolean isInitial;
    private final boolean isFinal;
    private final String stateName;

    /**
     * Constructs a new State.
     * @param initial whether this is an initial state
     * @param finalState whether this is a final/accept state
     * @param name name of the state
     */
    public State(boolean initial, boolean finalState, String name) {
        isInitial = initial;
        isFinal = finalState;
        stateName = name;
    }

    // getters

    public boolean getIsInitial() {
        return isInitial;
    }

    public boolean getIsFinal() {
        return isFinal;
    }

    public String getStateName() {
        return stateName;
    }

}
