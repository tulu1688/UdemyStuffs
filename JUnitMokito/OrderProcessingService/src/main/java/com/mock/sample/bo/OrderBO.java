package com.mock.sample.bo;

import com.mock.sample.dto.Order;

/**
 * Created by trankhai on 9/14/17.
 */
public interface OrderBO {
    boolean placeOrder(Order order) throws BOException;
    boolean cancelOrder(int id) throws BOException;
    boolean deleteOrder(int id) throws BOException;
}
