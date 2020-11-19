package crud.service;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Вошли в метод loadUserByUsername");
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
       org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User
               (user.getUsername(), user.getPassword(), user.getAuthorities());
        System.out.println(user1);
        return  user1;
    }

}

