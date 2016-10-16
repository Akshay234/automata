import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class FileParser {

    private String fileName;

    public FileParser(String fileName) {
        this.fileName = fileName;
    }


    public JSONArray parse() throws IOException, JSONException {
        File file = new File(fileName);
        String fileData = FileUtils.readFileToString(file);
        JSONArray data = new JSONArray(fileData);

        return data;
    }


}
