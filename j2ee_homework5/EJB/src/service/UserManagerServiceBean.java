package service;

import dao.UserDAO;
import model.User;
import utility.LoginState;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by user on 17/2/22.
 */
@Stateless(name = "UserManagerServiceEJB")
public class UserManagerServiceBean implements UserManagerService{
    @EJB UserDAO userDAO;

    public UserManagerServiceBean() {}

    @Override
    public LoginState validateUser(String username, String password) {
        User user = userDAO.findUser(username);

        if (user == null) return LoginState.USERNOTEXIST;

        String pwd = user.getPassword();
        if (pwd.equals(password)) return LoginState.SUCCESS;
        else return LoginState.WRONGPWD;
    }
}
