import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.junit.Assert.assertEquals;

public class FileParserTest {
    private final String VALID_FILE = "resource/validFile.json";
    private final String INVALID_FILE = "resource/invalidFile.json";
    File file;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Before
    public void setUp() throws Exception {
        file = new File(VALID_FILE);
        file.createNewFile();
        String data = "[{\"name\":\"foobar\",\"type\":\"Nothing\"}]";
        writeStringToFile(file, data);
    }

    @Test
    public void parser_should_give_the_formated_data_and_should_not_throw_exception_when_file_is_present() throws IOException, JSONException {
        String validFile = VALID_FILE;
        FileParser fileParser = new FileParser(validFile);
        JSONArray formatedData = fileParser.parse();
        JSONObject formattedHash = formatedData.getJSONObject(0);
        assertEquals("foobar", formattedHash.get("name"));
        assertEquals("Nothing", formattedHash.get("type"));
    }

    @Test
    public void parser_should_throw_exception_when_file_is_not_present() throws IOException, JSONException {
        String invalid_file = INVALID_FILE;
        FileParser fileParser = new FileParser(invalid_file);
        exception.expect(IOException.class);
        fileParser.parse();
    }

    @After
    public void tearDown() throws Exception {
        file.delete();
    }
}