package repositories;

import java.util.HashMap;
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
        return username.equals("admin") && password.equals("123456789");
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
}
