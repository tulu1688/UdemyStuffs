package com.mock.sample.dao;

import com.mock.sample.dto.Order;

import java.sql.SQLException;

/**
 * Created by trankhai on 9/14/17.
 */
public class OrderDAOImpl implements OrderDAO {
    public int create(Order order) throws SQLException {
        return 0;
    }

    public Order read(int id) throws SQLException {
        return null;
    }

    public int update(Order order) throws SQLException {
        return 0;
    }

    public int delete(int id) throws SQLException {
        return 0;
    }
}
