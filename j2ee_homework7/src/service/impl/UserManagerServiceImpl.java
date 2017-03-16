package service.impl;

import dao.DaoHelper;
import dao.UserDao;
import dao.impl.DaoHelperImpl;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserManagerService;
import utility.LoginState;

/**
 * Created by user on 17/2/24.
 */
public class UserManagerServiceImpl implements UserManagerService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public LoginState validateUser(String username, String password) {
        User user = userDao.findUser(username);

        if (user == null) return LoginState.USERNOTEXIST;

        String pwd = user.getPassword();
        if (pwd.equals(password)) return LoginState.SUCCESS;
        else return LoginState.WRONGPWD;
    }
}
