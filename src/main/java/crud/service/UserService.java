package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<?> queryForUser();

    void removeUser(int id);

    void editUser(User user);

    User findByUsername(String username);

    void addUserByAdmin(User user);

    User getById(int id);
}
