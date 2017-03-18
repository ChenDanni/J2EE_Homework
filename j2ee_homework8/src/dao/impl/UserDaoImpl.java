package dao.impl;

import dao.BaseDAO;
import dao.DaoHelper;
import dao.UserDao;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by chendanni on 17/2/24.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {


    @Autowired
    BaseDAO baseDAO;


    @Override
    public User findUser(String username) {

        Session session = baseDAO.getSession();
        String hql = "SELECT s FROM User s WHERE username='"+ username + "'";
        return (User) session.createQuery(hql).uniqueResult();
    }
}
