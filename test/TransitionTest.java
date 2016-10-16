import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TransitionTest {

    @Test
    public void process_should_give_the_next_state_according_to_the_instruction_provided() {
        Map<String, State> paths = new HashMap<>();
        State q1 = new State("q1");
        State q2 = new State("q2");
        paths.put("q1 0", q1);
        paths.put("q1 1", q2);
        Transition transition = new Transition(paths);
        State nextState = transition.process(q1, '1');
        assertEquals(q2, nextState);
    }
}