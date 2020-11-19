package crud.dao;

import crud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleDao {

    public Role getById(int id);
}
