package main;

import java.util.LinkedHashMap;

/**
 * Contains the finite state automata; states, transition
 * functions, initial state
 */
public class ControlUnit {
    private State currentState;
    private State[] states;
    private Transition[] transitions;
    private LinkedHashMap<String, State> stateMap;

    ControlUnit(State[] states, Transition[] transitions, State startState) {
        currentState = startState;
        this.states = states;
        this.transitions = transitions;
        this.stateMap = new LinkedHashMap<String, State>(states.length);
        for (State s : states) {
            stateMap.put(s.getStateName(), s);
        }
    }

    public void goToState(String nextState) {
        // get state from name
        State next = stateMap.get(nextState);
        currentState = next;
    }

    public State getCurrentState() {
        return currentState;
    }

    public State[] getStates() {
        return states;
    }

    public Transition[] getTransitions() {
        return transitions;
    }
}
