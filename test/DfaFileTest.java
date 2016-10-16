import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DfaFileTest {
    JSONArray fileData;

    @Before
    public void setUp() throws Exception {
        String fileName = "resource/examples.json";
        FileParser fileParser = new FileParser(fileName);
        fileData = fileParser.parse();
    }

    @Test
    public void validatePassCases_will_return_true_when_all_pass_cases_of_the_file_are_valid() throws IOException, JSONException {
        for (int i = 0; i < fileData.length(); i++) {
            DfaCasesValidator dfaCasesValidator = new DfaCasesValidator(fileData.getJSONObject(i));
            assertTrue(dfaCasesValidator.validatePassCases());
        }
    }

    @Test
    public void validateFailCases_will_return_true_when_all_fail_cases_of_the_file_are_valid() throws IOException, JSONException {
        for (int i = 0; i < fileData.length(); i++) {
            DfaCasesValidator dfaCasesValidator = new DfaCasesValidator(fileData.getJSONObject(i));
            assertTrue(dfaCasesValidator.validateFailCases());
        }
    }
}
