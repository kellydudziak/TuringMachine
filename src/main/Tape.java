package main;

import java.util.Stack;

/**
 * Tape includes symbols written on the tape and a
 * blank symbol.
 */
public class Tape {
    private Stack<String> rightStack; //could be char?
    private Stack<String> leftStack;
    private String currentSymbol;
    private String blankSymbol;
    private String inputString;

    /**
     * Construct a tape with two stacks and a current cell.
     * @param input
     * @param blankSymbol
     */
    public Tape(String input, String blankSymbol) {
        inputString = input;
        this.blankSymbol = blankSymbol;
        rightStack = new Stack<String>();
        leftStack = new Stack<String>();

        // divide input string into individual characters
        // and put on the tape
        char[] inputChar = input.toCharArray();
        for (char ch : inputChar) {
            leftStack.push(String.valueOf(ch));
        }
        // reverse so string is in correct order and
        // all input is to the right of the read/write head
        while (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }
        // positions read/write head at first non-blank element
        currentSymbol = rightStack.pop();
    }

    /**
     * Print entire tape in order without changing it.
     */
    public void printTape() {
        // print left stack in order
        if (!leftStack.isEmpty()) {
            System.out.print(leftStack);
        }
        // print current symbol
            System.out.print("  " + currentSymbol + "  ");

        // print right stack in reverse order
        if (!rightStack.isEmpty()) {
            System.out.print(reverse(rightStack) + "\n");
        } else {
            System.out.println("");
        }
    }

    /**
     * Create a copy of a stack and reverse the clone.
     * @param stack the stack to reverse
     * @return the reversed stack
     */
    public static Stack<String> reverse(Stack<String> stack) {
        Stack<String> copy = (Stack<String>) stack.clone();
        Stack<String> reversed = new Stack<>();

        while (!copy.isEmpty()) {
            reversed.push(copy.pop());
        }

        return reversed;
    }

    /**
     * Moves read/write head and prints the new tape.
     * @param direction the direction to move. Assumes input is only
     *                  r for right or l for left.
     */
    public void move(String direction) {
            if (direction.equalsIgnoreCase("r")) {
                moveRight();
            } else {
                moveLeft();
            }
        printTape();
    }

    // move read/write head to point at the symbol to the right
    // of the current symbol
    private void moveRight() {
        if (!currentSymbol.equalsIgnoreCase(blankSymbol)) { // don't push blanks to the stack
            leftStack.push(currentSymbol);
        }

        // as long as the right stack has elements, the top becomes
        // the new current symbol. If the stack is empty, the new current
        // symbol is the blank.
        if (!rightStack.isEmpty()) {
            currentSymbol = rightStack.pop();
        } else {
            currentSymbol = blankSymbol;
        }
    }

    private void moveLeft() {
        if (!currentSymbol.equalsIgnoreCase(blankSymbol)) {
            rightStack.push(currentSymbol);
        }

        if (!leftStack.isEmpty()) {
            currentSymbol = leftStack.pop();
        } else {
            currentSymbol = blankSymbol;
        }
    }

    /**
     * Gets the symbol currently being read by head.
     * @return the current symbol
     */
    public String read() {
        return currentSymbol;
    }

    /**
     * Overwrites the current symbol.
     * @param symbol the new symbol to put on tape
     */
    public void write(String symbol) {
        currentSymbol = symbol;
    }

    /**
     * Changes to tape symbols to given string.
     * @param string the string to replace the current tape
     * @return the new tape
     */
    public Tape resetTape(String string) {
        return new Tape(string, blankSymbol);
    }

    // getters

    public String getBlankSymbol() {
        return blankSymbol;
    }

    public Stack<String> getRightStack() {
        return rightStack;
    }

    public Stack<String> getLeftStack() {
        return leftStack;
    }

    public String getInputString() {
        return inputString;
    }
}
