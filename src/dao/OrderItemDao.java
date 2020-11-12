package dao;

import exception.BookException;
import exception.CategoryException;
import model.Book;
import model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {
    private final BookDao bookDao = new BookDao();

    public void addIem(OrderItem orderItem) {
        Connection cnx = SingletonConnection.getConnection();
        try {
            PreparedStatement st = cnx.prepareStatement("insert into orderitem(id_book,quantity,order_id) values(?,?,?)");
            st.setLong(1, orderItem.getBook().getId());
            st.setInt(2, orderItem.getQuantity());
            st.setLong(3,orderItem.getOrder().getId());
            st.executeUpdate();
            System.out.println("one record inserted success");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<OrderItem> getAllItems() {
        Connection cnx = SingletonConnection.getConnection();
        List<OrderItem> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from orderitem");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getLong(1));
                Book book = bookDao.getBookById(rs.getLong(2));
                orderItem.setBook(book);
                orderItem.setQuantity(rs.getInt(3));
                liste.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
    public List<OrderItem> getItemsByOrderId(Long orderId) {
        Connection cnx = SingletonConnection.getConnection();
        List<OrderItem> liste = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from orderitem where order_id = ?");
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getLong(1));
                Book book = bookDao.getBookById(rs.getLong(2));
                orderItem.setBook(book);
                orderItem.setQuantity(rs.getInt(3));
                liste.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public OrderItem getItemById(Long id) {
        Connection cnx = SingletonConnection.getConnection();
        OrderItem orderItem = null;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from orderitem where id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orderItem = new OrderItem();
                orderItem.setId(rs.getLong(1));
                Book book = bookDao.getBookById(rs.getLong(2));
                orderItem.setBook(book);
                orderItem.setQuantity(rs.getInt(3));
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
        return orderItem;
    }

    public void deleteItemById(Long id) throws CategoryException {
        Connection cnx = SingletonConnection.getConnection();
        OrderItem orderItem = getItemById(id);
        if (orderItem == null) {
            throw new CategoryException("OrderItem Not Found");
        }
        try {
            PreparedStatement st = cnx.prepareStatement("delete from orderitem where id=?");
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void updateItemQuantity(OrderItem orderItem) throws BookException {
        Connection cnx = SingletonConnection.getConnection();
        OrderItem oldOrderItem = getItemById(orderItem.getId());
        if (oldOrderItem == null) {
            throw new BookException("OrderItem Not Found");
        }
        if (orderItem.getQuantity() != null) {
            oldOrderItem.setQuantity(orderItem.getQuantity());
        }

        try {
            PreparedStatement st = cnx.prepareStatement("update orderitem set quantity=? where id=?");
            st.setLong(1, oldOrderItem.getId());
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
