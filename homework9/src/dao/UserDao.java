package dao;


import model.User;

/**
 * Created by user on 17/2/24.
 */
public interface UserDao {
    public User findUser(String username);
}
