package main

import kotlinx.coroutines.delay
import java.io.File

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
//        runTransverse()
        runPrintTransitions()
//        runMachine()
    }

    private fun getTapeString() : String {
        print("Enter input string: ")
        return readLine()!!
    }

    private fun selectMachine(s : String) : TuringMachine {
        print("Select a machine: \n" +
                "1 = |w| is a multiple of 3 \n" +
                "2 = change all input to b \n" +
                "3 = |w| is odd \n")
        val input = readLine()!!

        when(input) {
            "1" -> return makeMult3TM(s)
            "2" -> return makeBConverterTM(s)
            "3" -> return makeOddSizeTM(s)
            else -> print("Invalid, here's #1")
        }
        return makeMult3TM(s)
    }

    // user selects a machine, gets input string, and runs them
    private fun runMachine() {
        do {
            selectMachine(getTapeString()).run()
            print("\nAnother? \n N to quit, Y to go again: ")
            val response = readLine()!!
        } while (response.equals("Y", ignoreCase = true))
    }

    // gets input, transverses tape
    private fun runTransverse() {
        do {
            makeMult3TM(getTapeString()).traverse()
            print("\nAnother? \n N to quit, Y to go again: ")
            val response = readLine()!!
        } while (response.equals("Y", ignoreCase = true))
    }

    // gets machine, prints its transitions
    private fun runPrintTransitions() {
        do {
            selectMachine("a").printTransitions()
            print("\nAnother? \n N to quit, Y to go again: ")
            val response = readLine()!!
        } while (response.equals("Y", ignoreCase = true))
    }

    // one of the first examples the book gives us
    private fun makeBConverterTM(tapeString : String): TuringMachine {
        // make states, initial state first
        val states: Array<State> = arrayOf(
                State(true, false, "q0"),
                State(false, true, "q1"))

        // make transitions
        val transitions = arrayOf(
                Transition("q0", "a", "q0", "b", "r"),
                Transition("q0", "b", "q0", "b", "R"),
                Transition("q0", "*", "q1", "*", "L"))

        // make control unit
        val cu = ControlUnit(states, transitions, states[0])
        // make tape
        var tape = Tape(tapeString, "*") // assume alphabet and transitions agree

        return TuringMachine("b converter", cu, tape)
    }

    private fun makeMult3TM(tapeString : String): TuringMachine {
        val states = arrayOf(
                State(true, true, "q0"),
                State(false, false, "q1"),
                State(false, false, "q2"))

        val transitions = arrayOf(
                Transition("q0", "a", "q1", "a", "r"),
                Transition("q0", "b", "q1", "b", "r"),
                Transition("q1", "a", "q2", "a", "r"),
                Transition("q1", "b", "q2", "b", "r"),
                Transition("q2", "a", "q0", "a", "r"),
                Transition("q2", "b", "q0", "b", "r"))

        val controlUnit = ControlUnit(states, transitions, states[0])
        var tape = Tape(tapeString, "*")

        return TuringMachine("|w| is a multiple of 3", controlUnit, tape)
    }


    private fun makeOddSizeTM(tapeString : String): TuringMachine {
        val states = arrayOf(
                State(true, false, "q0"),
                State(false, false, "q1"),
                State(false, true, "qf"))

        val transitions = arrayOf(
                Transition("q0", "a", "q1", "a", "r"),
                Transition("q0", "b", "q1", "b", "r"),
                Transition("q1", "a", "q0", "a", "r"),
                Transition("q1", "b", "q0", "b", "r"),
                Transition("q1", "*", "qf", "*", "r"))

        val controlUnit = ControlUnit(states, transitions, states[0])
        var tape = Tape(tapeString, "*")

        return TuringMachine("|w| is odd", controlUnit, tape)
    }

    private fun enumerate(tm: TuringMachine, i: Int) {
        // using tm's alphabet, find shortest strings in
        // alphabetical order and print in order (return a
        // list of them?) up to i elements

        // recursive, uses coroutines
    }

}
