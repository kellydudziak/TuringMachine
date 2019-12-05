package main;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Turing Machines have a control unit and tape.
 */
public class TuringMachine {
    Tape tape;
    State[] states;
    String blank;
    ControlUnit controlUnit;
    String machineName;

    /**
     * Constructs a Turing Machine.
     * @param name the name of the Machine. E.g. its function or language
     * @param control the machine's control unit
     * @param tape the machine's tape
     */
    public TuringMachine(String name, ControlUnit control, Tape tape) {
        machineName = name;
        controlUnit = control;
        this.tape = tape;
        blank = this.tape.getBlankSymbol();
        states = control.getStates();
    }

    /**
     * Executes the transition functions on the tape and prints whether the
     * string is accepted.
     * @return whether the input on tape is accepted by the machine
     */
    public boolean run() {
        System.out.println("\n{" + machineName + "}, w = " + tape.getInputString());

        String[] t;

        // run the machine on the tape
        do {
            // gets the transition function for the current state/symbol combination
            String currentStateName = controlUnit.getCurrentState().getStateName();
            String currentSymbol = tape.read();
            t = controlUnit.getTransitionMap().get(currentStateName + currentSymbol);

            // if there is a transition defined, execute it
            if (t != null) {
                controlUnit.goToState(t[0]);
                tape.write(t[1]);
                tape.move(t[2]);
            }
        }
        while (t != null); // if t is null, the transition is undefined and machine halts

        // halt and return true if string is accepted/calculation was performed successfully
        boolean f = controlUnit.getCurrentState().getIsFinal();
        System.out.println(f);
        return f;
    }

    /**
     * Prints all transition functions.
     */
    public void printTransitions() {
        LinkedHashMap<String, String[]> transitions = controlUnit.getTransitionMap();

        System.out.println("");
        for (String key : transitions.keySet()) {
            System.out.println(key + " " + Arrays.toString((transitions.get(key))));
        }
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
}
