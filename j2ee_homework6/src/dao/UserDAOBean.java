package dao;

import dao.impl.DaoHelperImpl;
import model.User;
import utility.LoginState;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 17/2/22.
 */
@Stateless(name = "UserDAOEJB")
public class UserDAOBean implements UserDAO{
    @PersistenceContext
    protected EntityManager em;


    public UserDAOBean() {
    }

    @Override
    public User findUser(String username) {
        try {
            Query query = em.createQuery("select u from User u where u.username='" + username + "'");
            User user = (User)query.getSingleResult();
            return user;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
