package service;

import dao.OrderDao;
import exception.OrderException;
import model.Order;
import model.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderDao orderDao = new OrderDao();

    public void save(Order order){
        try {
            orderDao.addOrder(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void update(Order order){
        try {
            orderDao.updateOrder(order);
        } catch (OrderException e) {
            e.printStackTrace();
        }
    }
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }
    public Order getOrderByUserId(Long id) {
        return orderDao.getOrderByUserId(id);
    }
    public Order getOrderByStatusAndUserId(String status,Long id) {
        return orderDao.getOrderByStatusAndUserId(status,id);
    }
    public void delete(Long id){
        try {
            orderDao.deleteOrderById(id);
        } catch (OrderException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        try {
            return orderDao.getAllOrders();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public void validateOrder(Order order) {
        order.setStatus(Order.OrderState.VALIDATED.toString());
        update(order);
    }
}
