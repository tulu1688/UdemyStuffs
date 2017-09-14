package com.mock.sample.dao;

import com.mock.sample.dto.Order;

import java.sql.SQLException;

/**
 * Created by trankhai on 9/14/17.
 */
public interface OrderDAO {
    int create(Order order) throws SQLException;
    Order read(int id) throws SQLException;
    int update(Order order) throws SQLException;
    int delete(int id) throws SQLException;
}
