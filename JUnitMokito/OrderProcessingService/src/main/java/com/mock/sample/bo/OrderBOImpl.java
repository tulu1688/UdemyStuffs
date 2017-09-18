package com.mock.sample.bo;

import com.mock.sample.dao.OrderDAO;
import com.mock.sample.dto.Order;

import java.sql.SQLException;

/**
 * Created by trankhai on 9/14/17.
 */
public class OrderBOImpl implements OrderBO {
    private OrderDAO orderDAO;

    public boolean placeOrder(Order order) throws BOException {
        try {
            int result = orderDAO.create(order);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }

        return true;
    }

    public boolean cancelOrder(int id) throws BOException {
        try {
            Order order = orderDAO.read(id);
            order.setStatus("cancelled");
            int result = orderDAO.update(order);

            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }

        return true;
    }

    public boolean deleteOrder(int id) throws BOException {
        try {
            int result = orderDAO.delete(id);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
}
