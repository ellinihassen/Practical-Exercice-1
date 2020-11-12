package dao;

import com.mysql.jdbc.Statement;
import exception.OrderException;

import model.Adresse;
import model.Order;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {


    private UserDao userDao = new UserDao();
    private AdresseDao adresseDao = new AdresseDao();

    public void addOrder(Order order) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        try {
            PreparedStatement st = cnx.prepareStatement("insert into orders(date,etprice,orderstate,id_user,id_adresse) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, java.sql.Date.valueOf(order.getDate()));
            st.setDouble(2, order.getEtPrice());
            st.setString(3, order.getStatus().toString());
            st.setLong(4, order.getUser().getId());
            st.setLong(5, order.getAdresseLiv().getId());
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            System.out.println("one record inserted success");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public void updateOrder(Order order) throws OrderException {
        Connection cnx = SingletonConnection.getConnection();
        Order oldOrder = getOrderById(order.getId());

        if (oldOrder == null) {
            throw new OrderException("Order Not Found");
        }

        if(!oldOrder.getStatus().equals(order.getStatus())){
            oldOrder.setStatus(order.getStatus());
        }
        if(oldOrder.getAdresseLiv().getId()!= order.getAdresseLiv().getId()){
            oldOrder.setAdresseLiv(order.getAdresseLiv());
        }
        if(oldOrder.getEtPrice()!=order.getEtPrice()){
            oldOrder.setEtPrice(order.getEtPrice());
        }
        try {
            PreparedStatement st = cnx.prepareStatement("update orders set etprice=?,orderstate=?,id_user=?,id_adresse=? where id=?");
            st.setDouble(1,oldOrder.getEtPrice() );
            st.setString(2, oldOrder.getStatus());
            st.setLong(3, oldOrder.getUser().getId());
            st.setLong(4, oldOrder.getAdresseLiv().getId());
            st.setLong(5, oldOrder.getId());
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public Order getOrderById(Long id) {
        Connection cnx = SingletonConnection.getConnection();
        Order order = null;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from orders where id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getLong(1));
                order.setDate(rs.getDate(2).toLocalDate());
                order.setEtPrice(rs.getDouble(3));
                order.setStatus(rs.getString(4));
                User user = userDao.getUserById(rs.getLong(5));
                order.setUser(user);
                Adresse adresse = adresseDao.getAdresseById(rs.getLong(6));
                order.setAdresseLiv(adresse);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return order;
    }
    public Order getOrderByUserId(Long id) {
        Connection cnx = SingletonConnection.getConnection();
        Order order = null;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from orders where id_user=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getLong(1));
                order.setDate(rs.getDate(2).toLocalDate());
                order.setEtPrice(rs.getDouble(3));
                order.setStatus(rs.getString(4));
                User user = userDao.getUserById(rs.getLong(5));
                order.setUser(user);
                Adresse adresse = adresseDao.getAdresseById(rs.getLong(6));
                order.setAdresseLiv(adresse);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return order;
    }

    public Order getOrderByStatusAndUserId(String status,Long idUser) {
        Connection cnx = SingletonConnection.getConnection();
        Order order = null;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from orders where orderstate =? and id_user = ?");
            ps.setString(1, status);
            ps.setLong(2,idUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getLong(1));
                order.setDate(rs.getDate(2).toLocalDate());
                order.setEtPrice(rs.getDouble(3));
                order.setStatus(rs.getString(4));
                User user = userDao.getUserById(rs.getLong(5));
                order.setUser(user);
                Adresse adresse = adresseDao.getAdresseById(rs.getLong(6));
                order.setAdresseLiv(adresse);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return order;
    }
    public void deleteOrderById(Long id) throws OrderException, SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Order order = getOrderById(id);
        if (order == null) {
            throw new OrderException("Order Not Found");
        }
        try {
            PreparedStatement st = cnx.prepareStatement("delete from orders where id=?");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public List<Order> getAllOrders() throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        List<Order> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(1));
                order.setDate(rs.getDate(2).toLocalDate());
                order.setEtPrice(rs.getDouble(3));
                order.setStatus(rs.getString(4));
                User user = userDao.getUserById(rs.getLong(5));
                order.setUser(user);
                Adresse adresse = adresseDao.getAdresseById(rs.getLong(6));
                order.setAdresseLiv(adresse);
                liste.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}
