package services;

public interface AdminService {
    public boolean getLogin(String username, String password);
    public boolean createStudent(String username, String password);
}
