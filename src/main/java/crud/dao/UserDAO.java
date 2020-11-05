package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UserDAO implements UserDAOinterface{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(String name, int age) {
        User user = new User(name, age);
        entityManager.persist(user);
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    @Override
    public List<?> queryForUser() {
        List<?> users = entityManager.createQuery("SELECT user from User user").getResultList();
        System.out.println(users.size());
        return users;
    }

    @Override
    public void removeUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void editUser(int id, String name, int age) {
        User user = entityManager.find(User.class, id);
        entityManager.detach(user);
        user.setAge(age);
        user.setName(name);
        entityManager.merge(user);
    }
}
