package com.tulu.junit.spring.service;

import com.tulu.junit.spring.dao.TicketDAO;
import com.tulu.junit.spring.dto.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Created by trankhai on 9/18/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TicketServiceImplTest {

    @Autowired
    @Mock
    private TicketDAO dao;

    @Autowired
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void Test(){
        when(dao.createTicket(any(Ticket.class))).thenReturn(1);
        int result = ticketService.buyTicket("Tulu", "0987654321");
        assertEquals(1, result);
    }
}
