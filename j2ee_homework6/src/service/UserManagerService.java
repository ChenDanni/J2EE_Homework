package service;

import utility.LoginState;
import javax.ejb.Remote;

/**
 * Created by user on 17/2/22.
 */
@Remote
public interface UserManagerService {
    public LoginState validateUser(String username,String password);
}
