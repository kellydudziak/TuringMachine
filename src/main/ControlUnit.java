package main;

import java.util.LinkedHashMap;

/**
 * Contains the states and transition functions.
 */
public class ControlUnit {
    private State currentState;
    private State[] states;
    private Transition[] transitions;
    private LinkedHashMap<String, State> stateMap;
    private LinkedHashMap<String, String[]> transitionMap;

    /**
     * Contruct a new control unit.
     * @param states the set of states
     * @param transitions the set of transition functions
     * @param startState the initial state
     */
    ControlUnit(State[] states, Transition[] transitions, State startState) {
        currentState = startState;
        this.states = states;
        this.stateMap = new LinkedHashMap<>(states.length);
        for (State s : states) {
            stateMap.put(s.getStateName(), s);
        }

        this.transitions = transitions;
        transitionMap = addTransitionsToMap();
    }

    /**
     * Moves to a new current state.
     * @param nextState the state to go to
     */
    public void goToState(String nextState) {
        // get state from name
        State next = stateMap.get(nextState);
        currentState = next;
    }

    /**
     * Populates the control unit's transition hash map.
     * @return the hash map of transitions
     */
    public LinkedHashMap<String, String[]> addTransitionsToMap() {
        LinkedHashMap<String, String[]> map = new LinkedHashMap<>();
        for (Transition t : transitions) {
            String key = t.getKey();
            String[] value = t.getValue();

            map.put(key, value);
        }
        return map;
    }

    // getters

    public State getCurrentState() {
        return currentState;
    }

    public State[] getStates() {
        return states;
    }

    public LinkedHashMap<String, String[]> getTransitionMap() {
        return transitionMap;
    }
}
