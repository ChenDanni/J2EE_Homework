package service;

import utility.LoginState;
import javax.ejb.Remote;

/**
 * Created by chendanni on 17/2/22.
 */
@Remote
public interface UserManagerService {
    public LoginState validateUser(String username,String password);
}
