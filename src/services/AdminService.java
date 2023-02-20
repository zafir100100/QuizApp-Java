package services;

import java.util.List;
import java.util.Map;

public interface AdminService {

    public boolean getLogin(String username, String password);

    public boolean createStudent(String username, String password);

    public List<Map<String, String>> getStudentMarks();
}
