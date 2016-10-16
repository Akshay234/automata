import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DfaCasesValidator implements Validator {
    private final JSONObject data;

    public DfaCasesValidator(JSONObject data) {
        this.data = data;
    }

    @Override
    public boolean validatePassCases() throws JSONException, IOException {
        JSONArray passCases = data.getJSONArray("pass-cases");
        Dfa dfa = createDfa();
        for (int i = 0; i < passCases.length(); i++) {
            if (!dfa.process(passCases.get(i).toString())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validateFailCases() throws JSONException, IOException {
        JSONArray failCases = data.getJSONArray("fail-cases");
        Dfa dfa = createDfa();
        for (int i = 0; i < failCases.length(); i++) {
            if (dfa.process(failCases.get(i).toString())) {
                return false;
            }
        }
        return true;
    }

    private Dfa createDfa() throws JSONException, IOException {
        return new DfaGenerator(data.getString("name"), data.getJSONObject("tuple")).generate();
    }

}
