package com.mock.sample.bo;

import com.mock.sample.dao.OrderDAO;
import com.mock.sample.dto.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by trankhai on 9/14/17.
 */
public class OrderBOImplTest{
    @Mock
    OrderDAO dao;

    private OrderBOImpl bo;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        bo = new OrderBOImpl();
        bo.setOrderDAO(dao);
    }

    @Test
    public void placeOrder_Should_Create_An_Order() throws SQLException, BOException{
        Order order = new Order();
        when(dao.create(any(Order.class))).thenReturn(new Integer(1));
        boolean result = bo.placeOrder(order);

        assertTrue(result);
        verify(dao).create(order);
    }

    @Test
    public void placeOrder_Should_not_Create_An_Order() throws SQLException, BOException{
        Order order = new Order();
        when(dao.create(order)).thenReturn(new Integer(0));
        boolean result = bo.placeOrder(order);

        assertFalse(result);
        verify(dao).create(order);
    }

    @Test(expected = BOException.class)
    public void placeOrder_Should_Throw_BOException() throws SQLException, BOException{
        Order order = new Order();
        when(dao.create(order)).thenThrow(SQLException.class);
        boolean result = bo.placeOrder(order);
    }

    @Test
    public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException{
        Order order = new Order();
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenReturn(1);
        boolean result = bo.cancelOrder(123);

        assertTrue(result);

        verify(dao).read(123);
        verify(dao).update(order);
    }

    @Test
    public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException{
        Order order = new Order();
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);
        boolean result = bo.cancelOrder(123);

        assertFalse(result);

        verify(dao).read(123);
        verify(dao).update(order);
    }

    @Test(expected = BOException.class)
    public void cancelOrder_Should_ThrowABOException() throws SQLException, BOException{
        when(dao.read(anyInt())).thenThrow(SQLException.class);
        bo.cancelOrder(123);
    }

    @Test(expected = BOException.class)
    public void cancelOrder_Should_Throw_A_BOException_On_Update() throws SQLException, BOException{
        Order order = new Order();
        when(dao.read(123)).thenReturn(order);
        when(dao.update(order)).thenThrow(SQLException.class);
        bo.cancelOrder(123);
    }

    @Test
    public void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
        when(dao.delete(123)).thenReturn(1);
        boolean result = bo.deleteOrder(123);
        assertTrue(result);
        verify(dao).delete(123);
    }
}
