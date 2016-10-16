import org.json.JSONException;

import java.io.IOException;

public interface Validator {
    public boolean validatePassCases() throws JSONException, IOException;
    public boolean validateFailCases() throws JSONException, IOException;
}
