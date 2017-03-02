package dao;

import dao.impl.DaoHelperImpl;
import model.User;
import utility.LoginState;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 17/2/22.
 */
@Stateless(name = "UserDAOEJB")
public class UserDAOBean implements UserDAO{
    public UserDAOBean() {
    }

    @Override
    public User findUser(String username) {
        DaoHelper daoHelper = DaoHelperImpl.getDaoHelper();
        Connection connection = daoHelper.getConnection();
        User user = null;
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM student WHERE username='"+ username + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String pwd = resultSet.getString("password");

                user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(pwd);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
