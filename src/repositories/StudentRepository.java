package repositories;

import java.util.HashMap;
import java.util.Map;
import services.JsonFileService;
import services.StudentService;

public class StudentRepository implements StudentService {

    private final JsonFileService jfs;

    public StudentRepository() {
        jfs = new JsonFileRepository("resources/databases/users.json");
    }

    @Override
    public boolean getLogin(String username, String password) {
        return username.equals("student") && password.equals("3333");
    }
    
    @Override
    public boolean addStudent(String username, String password) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            map.put("role", "student");
            return true;
        } catch (Exception ex) {
            System.out.println("Student Addition Failed. Exception occured");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
