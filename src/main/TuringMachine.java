package main;

import main.State;
import main.Tape;
import main.Transition;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Turing Machines have Control Unit and Tape.
 * Each Turing Machine as a set of transitions, states, final and first states,
 * tape alphabet, input alphabet, etc.
 * Control unit = finite state automata with transitions?
 * M = (Q,E,R,D,Q0,*,F), where star is the blank symbol
 */
    // each TM should have a hashmap with transition functions, a tape, list of states
    // create and run each TM from here?
public class TuringMachine {
    LinkedHashMap<String, String[]> transitionFxns;
    Tape tape;
    State[] states;
    String blank;
    LinkedHashMap<String, String[]> transitions = new LinkedHashMap<>(5);
    ControlUnit controlUnit;
    String machineName;
//    String[] finalStates;
//    String[] initialStates;

    public TuringMachine(Tape tape) {

    }
    public TuringMachine(String name, ControlUnit control, Tape tape) {
        machineName = name;
        controlUnit = control;
        this.tape = tape;
        blank = this.tape.getBlankSymbol();
        states = control.getStates();
        transitions = makeTransitions(control.getTransitions());
    }

    public TuringMachine(State[] states, String blankSymbol, Tape tape, Transition[] transitions) {
        this.tape = tape;
        this.states = states;
        blank = blankSymbol;
        this.transitions = makeTransitions(transitions);
    }

    public TuringMachine() {

    }

    // takes an array of Transition and returns the hash map
    private LinkedHashMap<String, String[]> makeTransitions(Transition[] tArray) {
        for (Transition t : tArray) {
            String key = t.getKey();
            String[] value = t.getValue();

            transitions.put(key, value);
        }
        return transitions;
    }
    // TODO format so it looks like the transition functions in the book
    public void printTransitions() {
        System.out.println("");
        for (String key : transitions.keySet()) {
            System.out.println(key + " " + Arrays.toString((transitions.get(key))));
        }
        System.out.println("");
    }

    private void populateTape() {

    }

    private void printTransition() {

    }

    // TODO remove hardcoded indices

    /**
     * Performs transition functions on tape.
     * @return true if transitions are undefined in a final state
     */
    public boolean run() {
        // while current symbol is not blank and current state is not final
        // perform transition
        // if there is an undefined transition, halt and return true if current state is final
        System.out.println("");
        while (!tape.read().equalsIgnoreCase(blank) && // while current symbol is not blank and current state is not final
                !controlUnit.getCurrentState().getIsFinal()) {
            // get rule for current state and symbol
            String currentStateName = controlUnit.getCurrentState().getStateName();
            String currentSymbol = tape.read();
            String[] t = transitions.get(currentStateName + currentSymbol);
            System.out.println("(" + currentStateName + ", " + currentSymbol + "): " +Arrays.toString(t));
            if (t != null) {
                controlUnit.goToState(t[0]);
                tape.write(t[1]);
                tape.move(t[2]);
            } else {
                return controlUnit.getCurrentState().getIsFinal();
            }
        }
        return false;

//        String currentStateName = controlUnit.getCurrentState().getStateName();
//        String currentSymbol = tape.read();
//        String[] t = transitions.get(currentStateName + currentSymbol);
//
//        // if there is a transition for the this state and symbol, execute it
//        // if not, return true if current state is final (machine accepts string)
//        while (t != null) { // TODO different condition? is this why machine is writing a bunch of a's for mult 3?
//            try {
//                System.out.println("(" + currentStateName + ", " + currentSymbol + "): " + Arrays.toString(t));
//                controlUnit.goToState(t[0]);
//                tape.write(t[1]);
//                tape.move(t[2]);
//            } catch (NullPointerException e) {
//                return controlUnit.getCurrentState().getIsFinal();
//            }
//        }
//        return false;
    }

    public boolean runHalt() {
        System.out.println("\n" + machineName);
        String[] t;
        do {
            String currentStateName = controlUnit.getCurrentState().getStateName();
            String currentSymbol = tape.read();
            t = transitions.get(currentStateName + currentSymbol);
            if (t != null) { // redundant?
                controlUnit.goToState(t[0]);
                tape.write(t[1]);
                tape.move(t[2]);
            }
        }
        while (t != null);
        boolean f = controlUnit.getCurrentState().getIsFinal();
        System.out.println(f);
        return f; // is there a print and return option
    }

    /**
     * Moves read/write head all the way to the right then
     * all the way to the left.
     */
    public void traverse() {
        while (!tape.getRightStack().isEmpty()) {
            tape.move("r");
        }
        while (!tape.getLeftStack().isEmpty()) {
            tape.move("l");
        }
    }

    public void test() {
        String[] testStrings = {"a", "aab", "aabaab"};

        for (String s : testStrings) { // this gives me an empty stack exception when I include the empty string
            tape = tape.resetTape(s);
            //traverse();
            runHalt();
        }
    }
}
