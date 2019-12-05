package test;

import junit.framework.*;
import main.Tape;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TapeTest {

    @Test
    public final void noArgsConstructorTapeShouldHaveEmptyArrayAlphabet() {
        Tape tape = new Tape();
        assertEquals(new String[0], tape.alphabet);
    }

    @Test
    public final void argsConstructorShouldHaveAlphabet() {
        String[] a = {"0", "1", "*"};
        Tape tape = new Tape(a);
        assertEquals(new String[] {"0", "1", "*"}, tape.alphabet);
    }
}
