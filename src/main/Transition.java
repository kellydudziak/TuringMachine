package main;

import java.util.LinkedHashMap;

public class Transition {
    private String currentState;
    private String readSymbol;
    private String writeSymbol;
    private String direction;
    private String nextState;
    private String key;
    private String[] value = new String[3]; // [go to state, write symbol, move direction]
    // linked hash map keeps elements in insertion order
    private LinkedHashMap<String, String[]> transitions = new LinkedHashMap<>();

    /**
     * Creates a Transition and adds it to the LinkedHashMap.
     * @param current the current state
     * @param read the symbol in the current cell
     * @param write the symbol to write on the tape
     * @param dir left or right
     * @param next the state to transition to
     */
    public Transition(String current, String read, String next, String write, String dir) {
        currentState = current;
        readSymbol = read;
        writeSymbol = write;
        direction = dir;
        nextState = next;

        // preparing to add to machine's hash map
        key = currentState + readSymbol;
        value[0] = nextState;
        value[1] = writeSymbol;
        value[2] = direction;

    }

    // getters

    public String getKey() {
        return key;
    }


    public String[] getValue() {
        return value;
    }

//    public String[] getTransitionFunction(String currentState, String currentSymbol) {
//        String key = currentState + currentSymbol; // could put this in transitionOn
//        return transitions.get(key);
//    }

}
