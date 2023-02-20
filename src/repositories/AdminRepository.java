package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import services.AdminService;
import services.JsonFileService;

public class AdminRepository implements AdminService {

    private final JsonFileService jfs;

    public AdminRepository() {
        jfs = new JsonFileRepository("resources/databases/users.json");
    }

    @Override
    public boolean getLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    @Override
    public boolean createStudent(String username, String password) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            map.put("role", "student");
            map.put("marks", "0");
            boolean isAdded = jfs.createItemsInJsonFile(map);
            if (isAdded == false) {
                System.out.println("Student Addition Failed");
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Student Addition Failed. Exception occured");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

        @Override
    public List<Map<String, String>> getStudentMarks() {
        try {
            List<String> keys = new ArrayList<>();
            keys.add("username");
            keys.add("marks");
            List<Map<String, String>> marks = jfs.getAllRow(keys);
            if (!marks.isEmpty()) {
                return marks;
            }
            System.out.println("No Student marks found");
            return null;
        } catch (Exception ex) {
            System.out.println("Student marks retrieval Failed. Exception occured");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
