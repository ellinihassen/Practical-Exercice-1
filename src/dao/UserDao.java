package dao;

import exception.UserException;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void addUser(User user) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        try {
            PreparedStatement st = cnx.prepareStatement("insert into user(name,lastName,email,password,role) values(?,?,?,?,?)");
            st.setString(1, user.getName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole().toString());
            st.executeUpdate();
            System.out.println("one record inserted success");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void updateUser(User user) throws UserException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        User oldUser = getUserById(user.getId());
        if (oldUser == null) {
            throw new UserException("User Not Found");
        }
        if (user.getName() != null) {
            oldUser.setName(user.getName());
        }
        if (user.getLastName() != null) {
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            oldUser.setPassword(user.getPassword());
        }
        try {
            PreparedStatement st = cnx.prepareStatement("update user set name=?,lastName=?,email=?,password=? where id=?");
            st.setString(1, oldUser.getName());
            st.setString(2, oldUser.getLastName());
            st.setString(3, oldUser.getEmail());
            st.setString(4, oldUser.getPassword());
            st.setLong(5, oldUser.getId());
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void deleteUserById(Long id) throws UserException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        User user = getUserById(id);
        if (user == null) {
            throw new UserException("User Not Found");
        }
        try {
            PreparedStatement st = cnx.prepareStatement("delete from user where id=?");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        List<User> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setRole(rs.getString(6));
                liste.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public User getUserById(Long id) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        User user = null;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from user where id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setRole(rs.getString(6));
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return user;
    }

    public User searchUsers(String email) throws SQLException {
        for (User user : getAllUsers()) {
            if(user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;

    }

    public User authenticate(String email, String password) throws SQLException {
        User user = searchUsers(email);
        if(user!=null){
                if(user.getPassword().equals(password)){
                    return user;
                }
        }
        return null;
    }


}
