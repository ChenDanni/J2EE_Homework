package service;

import utility.LoginState;

/**
 * Created by chendanni on 17/2/22.
 */
public interface UserManagerService {
    public LoginState validateUser(String username, String password);
}
