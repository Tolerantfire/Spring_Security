package crud.service;

import crud.dao.RoleDao;
import crud.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDAO;

    public RoleServiceImpl(RoleDao roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    public Role getById(int id) {
        return roleDAO.getById(id);
    }
}
