package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            map.put("marks", "");
            map.put("role", "student");
            return true;
        } catch (Exception ex) {
            System.out.println("Student Addition Failed. Exception occured");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, String>> getAllStudent() {
        try {
            List<Map<String, String>> list = new ArrayList<>();
            List<String> keys = new ArrayList<>();
            keys.add("username");
            keys.add("marks");
            list = jfs.getAllRow(keys);
            if (list.isEmpty()) {
                System.out.println("No student found");
                return null;
            }
            return list;
        } catch (Exception ex) {
            System.out.println("Student Retrival Failed. Exception occured");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateStudentMarks(String username, String marks) {
        try {
            List<String> keys = new ArrayList<>();
            keys.add("username");
            keys.add("marks");
            Map<String, String> conditions = new HashMap<>();
            conditions.put("username", username);
            Map<String, String> updatedValues = new HashMap<>();
            updatedValues.put("marks", marks);
            boolean isUpdated = jfs.updateAllRowByCondition(keys, conditions, updatedValues);
            if (isUpdated) {
                System.out.println("Student's marks updated");
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Student's marks update Failed. Exception occured");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
