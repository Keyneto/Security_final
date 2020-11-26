package application.dao;

import application.models.MyUser;

import java.util.List;


public interface UserDao {

    void add(MyUser user);

    List<MyUser> listUsers();

    void remove(Long id);

    MyUser getUserById(Long id);

    void update(MyUser user, Long id);

    MyUser getUserByName(String userName);
}
