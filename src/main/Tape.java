package main;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Tape has an alphabet and any input symbols that exist
 * on it already.
 */
public class Tape {
    public String[] alphabet;
    private Stack<String> rightStack; //could be char?
    private Stack<String> leftStack;
    private String currentSymbol;
    private String blankSymbol;

    // symbols on tapes, also need to be able to change
    // symbols indefinitely; circular array? list

    public Tape() {
        alphabet = new String[0];
    }

    public Tape(String[] a) {
        alphabet = a;
    }

    public Tape(String input, String blankSymbol, String[] alphabet) {
        this.alphabet = alphabet;
        this.blankSymbol = blankSymbol;

        char[] inputChar = input.toCharArray();
        rightStack = new Stack<String>();
        leftStack = new Stack<String>();

        // push input to left stack, will be in reverse order
        for (char ch : inputChar) {
            leftStack.push(String.valueOf(ch));
        }

        // push onto right stack so head is at the top of stack
        // and string is in order
        while (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }

        currentSymbol = rightStack.pop();
    }

    /**
     * Print entire tape in order without changing it.
     */
    // TODO format output
    // TODO original print tape prints stack backwards, alternate moves are backwards
    // plus adds an extra a on the last go round
    public void printTape() {
        // print left stack backwards
        if (!leftStack.isEmpty()) {
            System.out.print(leftStack);
//            for (String s : leftStack) {
//                System.out.print(s + " ");
//            }
        }
        // print current symbol
            System.out.print("  " + currentSymbol + "  ");

        // print right stack in order
        if (!rightStack.isEmpty()) {
            System.out.print(reverse(rightStack) + "\n");
        } else {
            System.out.println("\n");
        }
    }

    public static Stack<String> reverse(Stack<String> stack) {
        Stack<String> copy = (Stack<String>) stack.clone(); // passes values not pointer??
        Stack<String> reversed = new Stack<>();

        while (!copy.isEmpty()) {
            reversed.push(copy.pop());
        }
        //System.out.println("Original stack " + stack + " reversed stack " + reversed);
        return reversed;
    }

    public void move(String direction) {
            if (direction.equalsIgnoreCase("r")) {
                moveRight();
            } else {
                moveLeft();
            }
            // TODO handle case when direction is not r or l
        printTape();
        // else halt
    }

    private void moveRight() {
        if (!currentSymbol.equalsIgnoreCase(blankSymbol)) { // don't push blanks to the stack?
            leftStack.push(currentSymbol);
        }

            if (!rightStack.isEmpty()) {
                currentSymbol = rightStack.pop();
            } else {
                currentSymbol = blankSymbol;
            }
    }

    private void moveLeft() {
        if (!currentSymbol.equalsIgnoreCase(blankSymbol)) { // don't push blanks to the stack
            rightStack.push(currentSymbol);
        }

        if (!leftStack.isEmpty()) {
            currentSymbol = leftStack.pop();
        } else {
            currentSymbol = blankSymbol;
        }
    }

    public String read() {
        return currentSymbol;
    }

    public String stackPeek(Stack<String> stack) {
        try {
            stack.peek();
        } catch (EmptyStackException e) {
            System.err.println(e);
            stack.push(blankSymbol);
        }
        return stack.peek();
    }

    public void write(String symbol) {
        currentSymbol = symbol;
    }

    public String getBlankSymbol() {
        return blankSymbol;
    }

    public Stack<String> getRightStack() {
        return rightStack;
    }

    public Stack<String> getLeftStack() {
        return leftStack;
    }

    public Tape resetTape(String string) {
        return new Tape(string, blankSymbol, alphabet);
    }
}
