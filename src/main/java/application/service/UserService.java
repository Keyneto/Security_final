package application.service;


import application.dao.UserDao;
import application.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<MyUser> findAll() {
        return userDao.listUsers();
    }

    public MyUser findById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public void save(MyUser user) {
        userDao.add(user);
    }

    @Transactional
    public void deleteById(long id) {
        userDao.remove(id);
    }

    @Transactional
    public void udateUser(MyUser user, Long id) {
        userDao.update(user, id);
    }
}
