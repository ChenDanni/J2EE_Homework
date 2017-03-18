package service.impl;

import dao.DaoHelper;
import dao.UserDao;
import dao.impl.DaoHelperImpl;
import dao.impl.UserDaoImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserManagerService;
import utility.LoginState;

/**
 * Created by user on 17/2/24.
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    UserDao userDao;

    @Override
    public LoginState validateUser(String username, String password) {
        User user = userDao.findUser(username);

        if (user == null) return LoginState.USERNOTEXIST;

        String pwd = user.getPassword();
        if (pwd.equals(password)) return LoginState.SUCCESS;
        else return LoginState.WRONGPWD;
    }
}
