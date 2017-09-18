package com.tulu.junit.spring.service;

import com.tulu.junit.spring.dao.TicketDAOImpl;
import com.tulu.junit.spring.dto.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by trankhai on 9/18/17.
 */
@Component
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketDAOImpl dao;

    public int buyTicket(String passengerName, String phone) {
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Tulu");
        ticket.setPhone("0987654321");
        return dao.createTicket(ticket);
    }
}
