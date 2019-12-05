package main;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class Transition {
    private String currentState;
    private String readSymbol;
    private String writeSymbol;
    private String direction;
    private String nextState;
    private String key;
    private String[] value = new String[3];
    // linked hash map keeps elements in insertion order
    private LinkedHashMap<String, String[]> transitions = new LinkedHashMap<>(); // put in main and add to Main.lhm so it's accessible

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

        // TODO remove hardcoded numbers
        key = currentState + readSymbol;
        value[0] = nextState;
        value[1] = writeSymbol;
        value[2] = direction;
    }


    public String getKey() {
        return key;
    }

    private void printArray(String[] value) {
        for (String elem : value) {
            System.out.print(elem);
        }
    }

//    @Override
//    public String toString() {
//        String r = null;
//        for (String val : transitions.get(key)) {
//            r += val + " ";
//        }
//
//        return r;
//    }

    public String[] getValue() {
        return value;
    }

    public String[] getTransitionFunction(String currentState, String currentSymbol) {
        String key = currentState + currentSymbol; // could put this in transitionOn
        return transitions.get(key);
    }

    public void transitionOn(String currentState, String currentSymbol) {
        String[] t = getTransitionFunction(currentState, currentSymbol);
//        Tape.write(t[0]); // static or instance? if static, need to pass in the TM for method?
//        goToState(t[2]);
//        Tape.move(t[1]);
    }

}
