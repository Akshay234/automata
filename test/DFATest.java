import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DfaTest {
    private final String NAME = "name";
    private final String INITIAL_STATE = "initialState";
    private final String FINAL_STATES = "finalStates";
    private final String TRANSITION = "transition";
    Map<String, Object> args;

    @Before
    public void setUp() throws Exception {
        args = new HashMap<>();
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        String name = "String end with 01";
        Map<String, State> transitionsDetail = new HashMap<>();
        transitionsDetail.put("q1 0", q2);
        transitionsDetail.put("q1 1", q1);
        transitionsDetail.put("q2 0", q2);
        transitionsDetail.put("q2 1", q3);
        transitionsDetail.put("q3 0", q2);
        transitionsDetail.put("q3 1", q1);
        Set<State> finalStates = new HashSet<>();
        finalStates.add(q3);

        args.put(NAME, name);
        args.put(INITIAL_STATE, q1);
        args.put(FINAL_STATES, finalStates);
        args.put(TRANSITION, new Transition(transitionsDetail));
    }

    @Test
    public void Dfa_should_be_able_to_process_when_valid_instructions_are_given() {
        String instructions = "101";
        Dfa dfa = Dfa.create(args);
        assertTrue(dfa.process(instructions));
    }

    @Test
    public void Dfa_should_not_be_able_to_process_when_invalid_instructions_are_given() {
        String instructions = "010";
        Dfa dfa = Dfa.create(args);
        assertFalse(dfa.process(instructions));
    }
}