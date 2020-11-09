package crud.dao;

import java.util.List;

public interface UserDao {
    void saveUser(String name, int age);

    List<?> queryForUser();

    void removeUser(int id);

    void editUser(int id, String name, int age);
}
