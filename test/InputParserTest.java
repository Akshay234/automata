import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InputParserTest {
    
    @Test
    public void parser_should_give_the_same_number_of_dfa_according_to_the_number_of_inputs_given() throws IOException, JSONException {
        String inputs = "[{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}]";
        InputParser inputParser = new InputParser(inputs);
        Set<DFA> dfaCollection = inputParser.parse();
        assertEquals(1, dfaCollection.size());
    }
}