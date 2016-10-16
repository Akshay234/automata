import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class FileParser {
    private String filePath;

    public FileParser(String filePath) {
        this.filePath = filePath;
    }


    public JSONArray parse() throws IOException, JSONException {
        File file = new File(filePath);
        String fileData = FileUtils.readFileToString(file).replace("\\","").replaceFirst("\"","");
        JSONArray data = new JSONArray(fileData);

        return data;
    }


}
