package service;

import dao.OrderItemDao;
import exception.BookException;
import exception.CategoryException;
import model.OrderItem;

import java.util.List;

public class OrderItemService {
    private final OrderItemDao orderItemDao = new OrderItemDao();

    public List<OrderItem> getAllItems(){
        return orderItemDao.getAllItems();
    }
    public List<OrderItem> getItemsByOrderId(Long id){
        return orderItemDao.getItemsByOrderId(id);
    }
    public OrderItem getItem(Long id){
        return orderItemDao.getItemById(id);
    }
    public void save(OrderItem orderItem){
        orderItemDao.addIem(orderItem);
    }
    public void updateQuantity(OrderItem orderItem){
        try {
            orderItemDao.updateItemQuantity(orderItem);
        } catch (BookException e) {
            e.printStackTrace();
        }
    }
    public void delete(Long id){
        try {
            orderItemDao.deleteItemById(id);
        } catch (CategoryException e) {
            e.printStackTrace();
        }
    }
}
