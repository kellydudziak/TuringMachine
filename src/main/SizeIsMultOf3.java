package main;

import main.*;

public class SizeIsMultOf3 extends TuringMachine {
    public SizeIsMultOf3(String name, ControlUnit control, Tape tape) {
        super(name, control, tape);
    }

    /**
     * give me a string and I'll give you the control unit, name, and tape
     * @param tapeString
     */
    public SizeIsMultOf3(String tapeString) {
        super();

        Tape tape = new Tape(tapeString, "*", new String[] {"a", "b"});
        new SizeIsMultOf3("|w| is a multiple of 3", getControlUnit(), tape);
    }

    private ControlUnit getControlUnit() {
        State[] states = {new State(true, true, "q0"),
                new State(false, false, "q1"),
                new State(false, false, "q2")};

        Transition[] transitions = {new Transition("q0", "a", "q1", "a", "r"),
                new Transition("q0", "b", "q1", "b", "r"),
                new Transition("q1", "a", "q2", "a", "r"),
                new Transition("q1", "b", "q2", "b", "r"),
                new Transition("q2", "a", "q0", "a", "r"),
                new Transition("q2", "b", "q0", "b", "r")};

        return new ControlUnit(states, transitions, states[0]);
    }

    public boolean runHalt() {
        return super.runHalt();
    }
}
