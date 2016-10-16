import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class DfaGeneratorTest {
    
    @Test
    public void DfaGenerator_will_return_dfa_when_tuple_has_all_required_data() throws JSONException, IOException {
        String name = "odd number of zeroes";
        String inputs = "{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]}";
        JSONObject tuple = new JSONObject(inputs);
        DfaGenerator dfaGenerator = new DfaGenerator(name, tuple);
        Dfa dfa = dfaGenerator.generate();
        assertEquals(Dfa.class, dfa.getClass());
    }
}