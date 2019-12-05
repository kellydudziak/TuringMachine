//package test;
//
//import main.State;
//import main.Transition;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class StateTest {
//
//    @Test
//    public final void defaultStateIsNonFinal() {
//        State state = new State();
//        assertEquals(false, state.getIsFinal());
//    }
//
//    @Test
//    public final void defaultStateIsNotInitial() {
//        State state = new State();
//        assertEquals(false, state.getIsInitial());
//    }
//
//    @Test
//    public final void argsConstructorTest() {
//        State state = new State(false, true, "q0", new Transition[0]);
//        assertEquals(false, state.getIsInitial());
//        assertEquals(true, state.getIsFinal());
//        assertEquals("q0", state.getStateName());
//        assertEquals(new Transition[0], state.getTransitions());
//    }
//}
