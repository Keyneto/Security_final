package application.dao;

import application.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao  {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void add(MyUser user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public List<MyUser> listUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<MyUser> movies = em.createQuery("FROM MyUser")
                .getResultList();
        return movies;
    }

    @Override
    public void remove(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        MyUser user = em.find(MyUser.class, id);
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    public MyUser getUserById(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        MyUser user = em.find(MyUser.class, id);
        return user;
    }

    @Override
    public void update(MyUser user, Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        MyUser userNew = em.find(MyUser.class, id);
        userNew.setUserName(user.getUserName());
        userNew.setPassword(user.getPassword());
        userNew.setEmail(user.getEmail());
        em.merge(userNew);
        em.getTransaction().commit();
    }

    @Override
    public MyUser getUserByName(String userName) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("from MyUser where userName = :userName");
        query.setParameter("userName", userName);
        MyUser user = (MyUser) query.setMaxResults(1).getSingleResult();
        return user;
    }
}