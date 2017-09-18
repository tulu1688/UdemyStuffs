package com.tulu.junit.spring.dao;

import com.tulu.junit.spring.dto.Ticket;
import org.springframework.stereotype.Component;

/**
 * Created by trankhai on 9/18/17.
 */
@Component
public class TicketDAOImpl implements TicketDAO{
    public int createTicket(Ticket ticket) {
        return 1;
    }
}
