package dao;

import model.User;

import javax.ejb.Remote;

/**
 * Created by chendanni on 17/2/22.
 */
@Remote
public interface UserDAO {

    public User findUser(String username);
}
