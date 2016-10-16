import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class DfaCasesValidatorTest {

    @Test
    public void checkPassCases_should_give_true_when_all_given_pass_cases_are_valid() throws JSONException, IOException {
        JSONObject testData = new JSONObject("{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}");
        DfaCasesValidator dfaCasesValidator = new DfaCasesValidator(testData);
        assertTrue(dfaCasesValidator.validatePassCases());
    }

    @Test
    public void checkFailCases_should_give_true_when_all_fail_cases_are_valid() throws JSONException, IOException {
        JSONObject testData = new JSONObject("{\"name\":\"odd number of zeroes\",\"type\":\"dfa\",\"tuple\":{\"states\":[\"q1\",\"q2\"],\"alphabets\":[\"1\",\"0\"],\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}},\"start-state\":\"q1\",\"final-states\":[\"q2\"]},\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"],\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}");
        DfaCasesValidator dfaCasesValidator = new DfaCasesValidator(testData);
        assertTrue(dfaCasesValidator.validateFailCases());
    }
}