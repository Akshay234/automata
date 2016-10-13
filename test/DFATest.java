import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DFATest {
    Map<String, Object> args;
    State q1;
    State q2;
    State q3;

    @Before
    public void setUp() throws Exception {
        args = new HashMap<>();
        q1 = new State("q1");
        q2 = new State("q2");
        q3 = new State("q3");
        List<Character> instructions = new ArrayList<>();
        instructions.add('0');
        instructions.add('1');
        instructions.add('0');
        args.put("instructions", instructions);
        args.put("initialState", q1);
        Map<String, State> stateMappingWithInstruction = new HashMap<>();
        stateMappingWithInstruction.put("q1 0", q1);
        stateMappingWithInstruction.put("q1 1", q2);
        stateMappingWithInstruction.put("q2 0", q3);
        args.put("transition", new Transition(stateMappingWithInstruction));
    }

    @Test
    public void DFA_should_give_true_when_given_string_pass() {
        args.put("finalState", q3);
        DFA dfa = DFA.create(args);
        assertTrue(dfa.processInstructions());
    }

    @Test
    public void DFA_should_give_false_when_given_string_fails() {
        args.put("finalState", q2);
        DFA dfa = DFA.create(args);
        assertFalse(dfa.processInstructions());
    }
}