package services;

import java.util.List;
import java.util.Map;

public interface StudentService {

    public boolean getLogin(String username, String password);

    public boolean addStudent(String username, String password);

    public List<Map<String, String>> getAllStudent();

    public boolean updateStudentMarks(String username, String marks);
}
