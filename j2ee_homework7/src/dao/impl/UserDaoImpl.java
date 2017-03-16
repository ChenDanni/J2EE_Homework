package dao.impl;

import dao.DaoHelper;
import dao.UserDao;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by chendanni on 17/2/24.
 */
public class UserDaoImpl implements UserDao {
//    DaoHelper daoHelper = DaoHelperImpl.getDaoHelper();

    private Configuration configuration;
    private ServiceRegistry serviceRegistry;
    private SessionFactory sessionFactory;
    private Session session;
    private static UserDaoImpl userDao = new UserDaoImpl();

    public static UserDaoImpl getInstance(){
        return userDao;
    }

    @Override
    public User findUser(String username) {

        try {
            configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            String hql = "SELECT s FROM User s WHERE username='"+ username + "'";
            Query query = session.createQuery(hql);
            List res = query.getResultList();
            tx.commit();
            session.close();
            sessionFactory.close();

            return (User) res.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
