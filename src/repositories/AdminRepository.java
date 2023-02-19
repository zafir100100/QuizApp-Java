package repositories;

import services.AdminService;
import services.JsonFileService;

public class AdminRepository implements AdminService {

    private JsonFileService jfs;

    public AdminRepository() {
        jfs = new JsonFileRepository("resources/databases/users.json");
    }

    @Override
    public boolean getLogin(String username, String password) {
        return username.equals("admin") && password.equals("123456789");
    }

    @Override
    public boolean createStudent(String username, String password) {

        return false;
    }
}
