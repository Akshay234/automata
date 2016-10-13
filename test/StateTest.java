import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StateTest {
    @Test
    public void equals_should_give_true_when_state_is_same() {
        State q1 = new State("q1");
        assertTrue(q1.equals(q1));
    }

    @Test
    public void equals_should_give_false_when_state_is_not_same() {
        State q1 = new State("q1");
        State q2 = new State("q2");
        assertFalse(q1.equals(q2));
    }

    @Test
    public void mapWith_should_give_the_mapping_of_state_name_and_given_alphabet() {
        State q1 = new State("q1");
        String expectedMapping = "q1 0";
        assertEquals(expectedMapping, q1.mapWith('0'));
    }
}