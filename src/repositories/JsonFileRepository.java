package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.JsonFileService;

public class JsonFileRepository implements JsonFileService {

    private final String fileName;

    public JsonFileRepository(String fileName) {
        this.fileName = fileName;
    }

    private boolean isJsonFileNameValid() {
        boolean doesJsonExtensionExists = fileName.contains(".json");
        if (doesJsonExtensionExists) {
            return true;
        }
        System.out.println(".json extension is not present in file name");
        return false;
    }

    private boolean createJsonFileIfDoesnNotExist() {
        try {
            // check if file name is valid
            boolean isfileNameValid = isJsonFileNameValid();
            if (!isfileNameValid) {
                return false;
            }
            File file = new File(fileName);
            // if file doesn't exist
            if (!file.exists()) {
                // create the file
                boolean isFileCreated = file.createNewFile();
                // if file creation failed, exit
                if (!isFileCreated) {
                    System.out.println("File creation failed");
                    return false;
                }
                // if file creation successful
                // initialize it with a blank json array
                FileWriter fr = new FileWriter(file);
                fr.write("[]");
                fr.close();
            }
            // note: this json file can't be blank initially
            // put {} or [] in file first, or it will generate
            // parse error
            return true;
        } catch (IOException ex) {
            System.out.println("File creation failed. Exception occured");
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean createItemsInJsonFile(Map<String, String> map) {
        try {
            // create JsonFile If Does Not Exist
            boolean doesFileExist = createJsonFileIfDoesnNotExist();
            if (!doesFileExist) {
                return false;
            }

            // read the file
            FileReader fr = new java.io.FileReader(fileName);
            org.json.simple.parser.JSONParser parser = new JSONParser();

            // get old file content
            Object obj = parser.parse(fr);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject myObject = new JSONObject();

            // add new file content in it
            map.entrySet().forEach(entry -> {
                myObject.put(entry.getKey(), entry.getValue());
            });

            jsonArray.add(myObject);

            FileWriter fw = new FileWriter(fileName);
            fw.write(jsonArray.toJSONString());
            fw.flush();
            fw.close();

            parser.reset();
            fr.close();
            return true;
        } catch (ParseException ex) {
            System.out.println("Json file parse failed. Exception occured.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            System.out.println("Json file update failed. Exception occured.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
