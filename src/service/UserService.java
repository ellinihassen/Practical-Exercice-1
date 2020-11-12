package service;

import dao.UserDao;
import exception.UserException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getUserById(Long id) {
        try {
            return userDao.getUserById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void save(User user) {
        try {
            userDao.addUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            userDao.deleteUserById(id);
        } catch (UserException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(User user) {
        try {
            userDao.updateUser(user);
        } catch (UserException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User login(String email, String password){
        try {
            if (userDao.authenticate(email,password)!=null){
                return userDao.authenticate(email,password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public void afficheUsers() {
        for(User user : getAllUsers()) {
            System.out.println(user);
        }
    }
}
