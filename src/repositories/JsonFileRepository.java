package repositories;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

    @Override
    public List<Map<String, String>> getAllRow(List<String> keys) {
        try {
            // create JsonFile If Does Not Exist
            boolean doesFileExist = createJsonFileIfDoesnNotExist();
            if (!doesFileExist) {
                return null;
            }

            // read the file
            FileReader fr = new java.io.FileReader(fileName);
            org.json.simple.parser.JSONParser parser = new JSONParser();

            // get old file content
            Object obj = parser.parse(fr);
            JSONArray jsonArray = (JSONArray) obj;

            List<Map<String, String>> output = new ArrayList<>();
            jsonArray.forEach(item -> {
                JSONObject jsonObject = (JSONObject) item;
                Map<String, String> map = new HashMap<>();
                keys.forEach(element -> {
                    if (jsonObject.get(element) != null) {
                        map.put(element, jsonObject.get(element).toString());
                    }
                });
                output.add(map);
            });
            parser.reset();
            fr.close();
            return output;
        } catch (Exception ex) {
            System.out.println("All rows retrieval failed");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, String>> getRandomRows(List<String> keys, int numberOfRows) {
        try {
            // create JsonFile If Does Not Exist
            boolean doesFileExist = createJsonFileIfDoesnNotExist();
            if (!doesFileExist) {
                return null;
            }

            // read the file
            FileReader fr = new java.io.FileReader(fileName);
            org.json.simple.parser.JSONParser parser = new JSONParser();

            // get old file content
            Object obj = parser.parse(fr);
            JSONArray jsonArray = (JSONArray) obj;

            List<Map<String, String>> output = new ArrayList<>();

            Random rand = new Random();
            for (int i = 0; i < numberOfRows; i++) {
                int randomIndex = rand.nextInt(jsonArray.size());
                JSONObject jsonObject = (JSONObject) jsonArray.get(randomIndex);
                Map<String, String> map = new HashMap<>();
                keys.forEach(element -> {
                    if (jsonObject.get(element) != null) {
                        map.put(element, jsonObject.get(element).toString());
                    }
                });
                output.add(map);
                jsonArray.remove(randomIndex);
            }
            parser.reset();
            fr.close();
            return output;
        } catch (Exception ex) {
            System.out.println("All rows retrieval failed");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, String>> getAllRowByCondition(List<String> keys, Map<String, String> conditions) {
        try {
            // create JsonFile If Does Not Exist
            boolean doesFileExist = createJsonFileIfDoesnNotExist();
            if (!doesFileExist) {
                return null;
            }

            // read the file
            FileReader fr = new java.io.FileReader(fileName);
            org.json.simple.parser.JSONParser parser = new JSONParser();

            // get old file content
            Object obj = parser.parse(fr);
            JSONArray jsonArray = (JSONArray) obj;

            List<Map<String, String>> output = new ArrayList<>();

            jsonArray.forEach(item -> {
                JSONObject jsonObject = (JSONObject) item;
                Map<String, String> map = new HashMap<>();

                // check if all required key is present
                keys.forEach(element -> {
                    if (jsonObject.get(element) != null) {
                        String isConditionMet = "1";
                        // check if each condition met
                        conditions.entrySet().forEach(entry -> {
                            if (jsonObject.get(entry.getKey()) != null && jsonObject.get(entry.getKey()).equals(entry.getValue())) {
                            } else {
                                isConditionMet.replace("1", "0");
                                return;
                            }
                        });
                        // if each condition met, add to map
                        // else, reset condition checking
                        if (isConditionMet.equals("1")) {
                            map.put(element, jsonObject.get(element).toString());
                        } else {
                            isConditionMet.replace("0", "1");
                        }
                    }
                });
                output.add(map);
            });

            parser.reset();
            fr.close();
            return output;
        } catch (Exception ex) {
            System.out.println("All rows retrieval failed");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateAllRowByCondition(List<String> keys, Map<String, String> conditions, Map<String, String> updatedValues) {
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

            List<Map<String, String>> output = new ArrayList<>();

            jsonArray.forEach(item -> {
                JSONObject jsonObject = (JSONObject) item;
                Map<String, String> map = new HashMap<>();

                // check if all required key is present
                keys.forEach(element -> {
                    if (jsonObject.get(element) != null) {
                        String isConditionMet = "1";
                        // check if each condition met
                        conditions.entrySet().forEach(entry -> {
                            if (jsonObject.get(entry.getKey()) != null && jsonObject.get(entry.getKey()).equals(entry.getValue())) {
                            } else {
                                isConditionMet.replace("1", "0");
                                return;
                            }
                        });
                        // if each condition met, update the value
                        // else, reset condition checking
                        if (isConditionMet.equals("1")) {
                            updatedValues.entrySet().forEach(entry -> {
                                if (jsonObject.get(entry.getKey()) != null) {
                                    jsonArray.remove(item);
                                    jsonObject.remove(entry.getKey());
                                    jsonObject.put(entry.getKey(), entry.getValue());
                                    jsonArray.add(jsonObject);
                                }
                            });
                        } else {
                            isConditionMet.replace("0", "1");
                        }
                    }
                });
            });

            // write to file
            FileWriter fw = new FileWriter(fileName);
            fw.write(jsonArray.toJSONString());
            fw.flush();
            fw.close();

            parser.reset();
            fr.close();
            return true;
        } catch (Exception ex) {
            System.out.println("All rows retrieval failed");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}

