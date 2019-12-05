package main

import kotlinx.coroutines.delay
import java.io.File

object Main {
    private var abAlphabet = arrayOf("a", "b")
    private var binaryAlphabet = arrayOf("1", "0")
    @JvmStatic
    fun main(args: Array<String>) {
        //val machine : TuringMachine = makeMult3TM() // works
        //val machine : TuringMachine = SizeIsMultOf3("aaa") // everything is null when we get to runHalt()
        //val machine : TuringMachine = makeBConverterTM() // works
        val machine : TuringMachine = makeWWTM() //infinite blanks
        //val machine : TuringMachine = makeMultiplierTM()
        //val machine : TuringMachine = oddSizeTM() // works
        //val machine : TuringMachine = makeEvenCheckerTM() //always false, faulty conversion between a/b and 1/0
 //       machine.tape.printTape();
//        machine.printTransitions();

       // machine.traverse();
        machine.runHalt();
        //machine.test(); //always false?


//        val hm = LinkedHashMap<String, Transition>()
//        hm["q0"] = Transition("q0", "a", "a", "r","qf");
//        hm["qf"] = Transition("qf","a", "a", "r",  "qf")


//        CoroutineScope(Default).launch {
//            fakeApiRequest()
//        }


        // load file
        // build machine (create new TM object)
        // prompt user for input
        // calculate + print steps
        // print results
        // use coroutines
    }

    private fun loadFile(file: File) {
        // load and parse file
        // construct a TM
    }

    // hardcoded example
    private fun makeBConverterTM() : TuringMachine {
        // make states
        val states : Array<State> = arrayOf(State(true, false, "q0"),
                State(false, true, "q1"))

        // make tape
        val tape = Tape("abaabaa", "*", abAlphabet) // assume alphabet and transitions agree

        // make transitions
        val transitions = arrayOf(Transition("q0", "a", "q0", "b", "r"),
                Transition("q0", "b", "q0", "b", "R"),
                Transition("q0", "*", "q1", "*", "L"))

        // TODO make a better way to give initial state to control unit
        val cu = ControlUnit(states, transitions, states[0])

        return  TuringMachine("b converter", cu, tape)
    }

    private fun makeMult3TM() : TuringMachine {
        val states = arrayOf(State(true, true, "q0"),
                State(false, false, "q1"),
                State(false, false, "q2"))

        val transitions = arrayOf(Transition("q0", "a", "q1", "a", "r"),
                Transition("q0", "b", "q1", "b", "r"),
                Transition("q1", "a", "q2", "a", "r"),
                Transition("q1", "b", "q2", "b", "r"),
                Transition("q2", "a", "q0", "a", "r"),
                Transition("q2", "b", "q0", "b", "r"))
        val controlUnit = ControlUnit(states, transitions, states[0])
        var tape = Tape("ababbbaaabbbabababbbabbaaaaaab", "*", abAlphabet)

        return TuringMachine("multiple of three", controlUnit, tape)
    }

    // TODO this can't handle the blank symbol
    private fun makeWWTM() : TuringMachine {
        val states = arrayOf(State(true, false, "q0"),
                State(false, false, "q1"),
                State(false, false, "q2"),
                State(false, false, "q3"),
                State(false, false, "q4"),
                State(false, false, "q5"),
                State(false, false, "q6"),
                State(false, false, "q7"),
                State(false, false, "q8"),
                State(false, false, "q9"),
                State(false, false, "q10"),
                State(false, true, "qf"))

        val transitions = arrayOf(
                // change symbol in first w +
                Transition("q0", "a", "q1", "0", "r"),
                Transition("q0", "b", "q1", "1", "r"),

                // check for even length +
                Transition("q1", "a", "q2", "a", "r"),
                Transition("q1", "b", "q2", "b", "r"),

                // move right until first 0, 1, or blank, then back up left once
                Transition("q2", "a", "q2", "a", "r"),
                Transition("q2", "b", "q2", "b", "r"),
                Transition("q2", "0", "q3", "0", "l"),
                Transition("q2", "1", "q3", "1", "l"),
                Transition("q2", "*", "q3", "*", "l"), // double check this one

                // change symbol in second w +
                Transition("q3", "a", "q4", "0", "l"),
                Transition("q3", "b", "q4", "1", "l"),

                // move left until first 0 or 1, then back up right once and start over +
                Transition("q4", "a", "q4", "a", "l"),
                Transition("q4", "b", "q4", "b", "l"),
                Transition("q4", "0", "q0", "0", "r"),
                Transition("q4", "1", "q0", "1", "r"),

                // transition into second part when all input is changed
                // TODO what is star in solution?
                // only time you can write a blank is when you read one
                Transition("q0", "0", "q5", "*", "l"),
                Transition("q0", "1", "q6", "*", "l"),

                // move left until blank then back up right once
                Transition("q5", "0", "q5", "0", "l"),
                Transition("q5", "1", "q5", "*", "l"),
                Transition("q5", "*", "q7", "*", "r"),
                Transition("q6", "0", "q6", "*", "l"),
                Transition("q6", "1", "q6", "1", "l"),
                Transition("q6", "*", "q8", "*", "r"),

                // mark it if equal to corresponding symbol in second w
                Transition("q7", "0", "q9", "*", "r"),
                Transition("q8", "1", "q9", "*", "r"),

                // check if finished
                Transition("q9", "*", "qf", "*", "r"),

                // find first 0 or 1 in second w and start over
                // skip the rest of first w
                Transition("q9", "*", "q10", "*", "r"),
                Transition("q9", "0", "q9", "0", "r"),

                // skip the beginning of second w
                Transition("q9", "1", "q9", "0", "r"),
                Transition("q10", "*", "q10", "*", "r"),

                // start over
                Transition("q10", "0", "q5", "*", "l"),
                Transition("q10", "1", "q6", "*", "l"))

        val controlUnit = ControlUnit(states, transitions, states[0])   // TODO change states so first is always initial and last is always final
        val tape = Tape("aa", "*", abAlphabet)

        return TuringMachine("ww", controlUnit, tape)
    }

    private fun makeEvenCheckerTM() : TuringMachine {
        val states = arrayOf(State(true, false, "q0"),
                State(false, false, "q1"),
                State(false, false, "q2"),
                State(false, false, "q3"),
                State(false, true, "q4"))
        val transitions = arrayOf(
                // change symbol in first w +
                Transition("q0", "a", "q1", "0", "r"),
                Transition("q0", "b", "q1", "1", "r"),

                // check for even length +
                Transition("q1", "a", "q2", "a", "r"),
                Transition("q1", "b", "q2", "b", "r"),

                // move right until first 0, 1, or blank, then back up left once
                Transition("q2", "a", "q2", "a", "r"),
                Transition("q2", "b", "q2", "b", "r"),
                Transition("q2", "0", "q3", "0", "l"),
                Transition("q2", "1", "q3", "1", "l"),
                Transition("q2", "*", "q3", "*", "l"), // double check this one

                // change symbol in second w +
                Transition("q3", "a", "q4", "0", "l"),
                Transition("q3", "b", "q4", "1", "l"),

                // move left until first 0 or 1, then back up right once and start over +
                Transition("q4", "a", "q4", "a", "l"),
                Transition("q4", "b", "q4", "b", "l"),
                Transition("q4", "0", "q0", "0", "r"),
                Transition("q4", "1", "q0", "1", "r"))
                // is it true that if we see a blank symbol at q4, the string should be accepted?
                // should get blank symbol as current when everything is in the right stack, but don't

        val controlUnit = ControlUnit(states, transitions, states[0])   // TODO change states so first is always initial and last is always final
        val tape = Tape("ab", "*", abAlphabet)

        return TuringMachine("even checker", controlUnit, tape)
    }

//    private fun makeMultiplierTM() : TuringMachine {
//
//    }
    private fun oddSizeTM() : TuringMachine {
    val states = arrayOf(State(true, false, "q0"),
            State(false, false, "q1"),
            State(false, true, "qf"))

    val transitions = arrayOf(
            Transition("q0", "a", "q1", "a", "r"),
            Transition("q0", "b", "q1", "b", "r"),
            Transition("q1", "a", "q0", "a", "r"),
            Transition("q1", "b", "q0", "b", "r"),
            Transition("q1", "*", "qf", "*", "r"))

    val controlUnit = ControlUnit(states, transitions, states[0])
    var tape = Tape("a", "*", abAlphabet)

    return TuringMachine("|w| is odd", controlUnit, tape)
}

//    private fun makeAnBnCnTM() : TuringMachine {
//
//    }

    private fun enumerate(tm: TuringMachine, i: Int) {
        // using tm's alphabet, find shortest strings in
        // alphabetical order and print in order (return a
        // list of them?) up to i elements

        // recursive, uses coroutines
    }

    private suspend fun fakeApiRequest() {
        val result1 = getResult1FromApi()
        println("debug: $result1")
    }

    private suspend fun getResult1FromApi() : String {
        logThread("getResult1FromApi")
        delay(1000)
        return "RESULT1"
    }

    private fun logThread(methodName: String) {
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }

    private fun loadTransitions(file: File) {
        // while file has next line, make transition(params) and add to hashmap
    }

    fun run() {
        // loop through transitions until state is final
        // and current symbol is blank
    }

}
